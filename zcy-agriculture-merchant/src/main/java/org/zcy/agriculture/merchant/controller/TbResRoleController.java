package org.zcy.agriculture.merchant.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.zcy.agriculture.entity.TbDicRolePower;
import org.zcy.agriculture.entity.TbResPower;
import org.zcy.agriculture.entity.TbResRole;
import org.zcy.agriculture.entity.TbUser;
import org.zcy.agriculture.enums.PowerStatusEnum;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.param.RolePowerParam;
import org.zcy.agriculture.service.ITbDicRolePowerService;
import org.zcy.agriculture.service.ITbResPowerService;
import org.zcy.agriculture.service.ITbResRoleService;
import org.zcy.agriculture.service.ITbUserService;
import org.zcy.agriculture.util.ValidationUtil;


/**
 * 角色 信息操作处理
 * 
 * @author zenghao
 * @date 2019-06-20
 */
@Controller
@RequestMapping("/api/role")
public class TbResRoleController extends BaseController
{
    private String prefix = "api/role";
	
	@Autowired
	private ITbResRoleService tbResRoleService;

	@Autowired
	private ITbUserService tbUserService;

	@Autowired
	private ITbDicRolePowerService tbDicRolePowerService;

	@Autowired
	private ITbResPowerService iTbResPowerService;

	@GetMapping()
	public String tbResRole()
	{
	    return prefix + "/tbResRole";
	}
	
	/**
	 * 查询角色列表
	 */
	@GetMapping("/list")
	@ResponseBody
	public AjaxResult list()
	{

		/**只有status为0的才是有效的（为1的已做了软删除处理）*/
		if(StringUtils.isEmpty(getFarmUUID())){
			return AjaxResult.error("农场ID不能为空");
		}
		TbResRole role = new TbResRole();
		role.setStatus(0);
		role.setFarmId(getFarmUUID());
        List<TbResRole> list = tbResRoleService.selectTbResRoleList(role);
        return AjaxResult.success(list);
	}
	
	
//	/**
//	 * 导出角色列表
//	 */
//	@RequiresPermissions("system:tbResRole:export")
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(TbResRole tbResRole)
//    {
//    	List<TbResRole> list = tbResRoleService.selectTbResRoleList(tbResRole);
//        ExcelUtil<TbResRole> util = new ExcelUtil<TbResRole>(TbResRole.class);
//        return util.exportExcel(list, "tbResRole");
//    }
	
	/**
	 * 新增角色
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增角色
	 */
	@PostMapping("/add")
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public AjaxResult addSave(@RequestBody RolePowerParam rolePowerParam)
	{
		if(ValidationUtil.isEmpty(rolePowerParam.getName())) {
			return error("请输入角色名");
		} else if (tbResRoleService.isExistWithRoleName(rolePowerParam.getName(),getFarmUUID())) {
			return error("角色名称已存在");
		}
		TbResRole role = new TbResRole();
		role.setStatus(0);
		role.setFarmId(getFarmUUID());
		Date d = new Date();
		role.setCreateTime(d);
		role.setUpdateTime(d);
		role.setName(rolePowerParam.getName());

		/**创建角色并添加相应权限到角色权限关联表*/
		int Result = tbResRoleService.insertTbResRole(role);
		List<TbDicRolePower> list = new ArrayList<>();
		if (Result == 1 && rolePowerParam.getPowerIdList()!= null) {
			for(Long powerID : rolePowerParam.getPowerIdList()) {
				TbDicRolePower rPower = new TbDicRolePower();
				rPower.setRoleId(role.getRoleId());
				rPower.setPowerId(powerID);
				list.add(rPower);
			}
			/**level=2的按钮权限下的子权限*/
			List<TbResPower> powerChildsBylevel = iTbResPowerService.getPowerChildsBylevel(4);//查询所有的level=4的接口权限
			List<Long> powerIdList = rolePowerParam.getPowerIdList();
			//遍历是否level=4的权限父类在集合powerIdList中有,如果有就保存和当前角色关联
			if(!CollectionUtils.isEmpty(powerChildsBylevel)){
				powerChildsBylevel.forEach(tbResPower -> {
					powerIdList.forEach(powerId -> {
						if(powerId == tbResPower.getParentId()){
							TbDicRolePower rPower = new TbDicRolePower();
							rPower.setRoleId(role.getRoleId());
							rPower.setPowerId(tbResPower.getPowerId());
							list.add(rPower);
						}
					});
				});
			}
			tbDicRolePowerService.insertTbDicRolePowerList(list);
		}

		return AjaxResult.success();
	}

	/**
	 * 修改角色
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TbResRole tbResRole = tbResRoleService.selectTbResRoleById(id);
		mmap.put("tbResRole", tbResRole);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存角色（包括相应的权限）
	 */
	@PostMapping("/edit")
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public AjaxResult editSave(@RequestBody RolePowerParam rolePowerParam)
	{
		if(ValidationUtil.isEmpty(rolePowerParam.getName())) {
			return error("请输入角色名");
		}else{
            TbResRole tbResRole = tbResRoleService.selectTbResRoleById(rolePowerParam.getRoleId());
            List<TbResRole> tbResRoles = tbResRoleService.selectTbResRoleList(null);
            for(TbResRole role : tbResRoles){
                if(tbResRole.getRoleId()==rolePowerParam.getRoleId()){
                    continue;
                }else if(role.getName().equals(rolePowerParam.getName())){
                    return error("角色名称已存在");
                }
            }

        }

		if(ValidationUtil.isEmpty(rolePowerParam.getRoleId())) {
			return error("请输入角色ID");
		}

		Long roleId = rolePowerParam.getRoleId();

		/**这段代码是根据传入的角色ID先删除数据库表中已存在权限列表*/
		tbDicRolePowerService.deleteTbDicRolePowerById(roleId);

		TbResRole role = new TbResRole();
		role.setRoleId(roleId);
		if (!tbResRoleService.isExistWithRoleID(roleId)){
			return error("角色不存在");
		}

		role.setName(rolePowerParam.getName()); //更新角色名
		role.setUpdateTime(new Date());         //更新时间字段
		role.setStatus(PowerStatusEnum.VALID.getCode()); //设置为有效（0）
		int Result = tbResRoleService.updateTbResRole(role);
		/**继续更新相应的权限吧*/
		List<TbDicRolePower> list = new ArrayList<>();
		if (Result == 1 && rolePowerParam.getPowerIdList()!= null) {
			/**根据传入的ID列表批量更新权限*/
			for(Long powerID : rolePowerParam.getPowerIdList()) { //获取传入的权限ID
				TbDicRolePower rPower = new TbDicRolePower();
				rPower.setPowerId(powerID);
				rPower.setRoleId(roleId);
				list.add(rPower);
			}
			/**level=3的按钮权限下的子权限*/
			List<TbResPower> powerChildsBylevel = iTbResPowerService.getPowerChildsBylevel(4);//查询所有的level=4的接口权限
			List<Long> powerIdList = rolePowerParam.getPowerIdList();
			//遍历是否level=4的权限父类在集合powerIdList中有,如果有就保存和当前角色关联
			if(!CollectionUtils.isEmpty(powerChildsBylevel)){
				for(TbResPower tbResPower : powerChildsBylevel){
					for(Long powerId : powerIdList){
						if(powerId == tbResPower.getParentId()){
							TbDicRolePower rolePower = new TbDicRolePower();
							rolePower.setRoleId(role.getRoleId());
							rolePower.setPowerId(tbResPower.getPowerId());
							list.add(rolePower);
						}
					}
				}
			}
			tbDicRolePowerService.insertTbDicRolePowerList(list);
		}
		return AjaxResult.success();
	}

	/**
	 * 删除角色
	 */
	@PostMapping( "/delete")
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public AjaxResult delete(@RequestBody Map<String,String> map)
	{
		if(ValidationUtil.isEmpty(map.get("roleId"))) {
			return error("roleId不能为空");
		}
		/**该角色下包含用户时候不能删除*/
		if (tbUserService.getUserCountWithRoleId(Long.parseLong(map.get("roleId"))) > 0) {
			return error("该角色下包含用户，无法删除");
		}
		//删除该角色对应的权限
		tbDicRolePowerService.deleteTbDicRolePowerById(Long.parseLong(map.get("roleId")));
		tbResRoleService.deleteRoleById(Long.parseLong(map.get("roleId")));
		return AjaxResult.success();
	}

	/**
	 * 删除角色
	 */
	/*@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tbResRoleService.deleteTbResRoleByIds(ids));
	}*/
	
}
