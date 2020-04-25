package org.zcy.agriculture.service.farm;

import org.zcy.agriculture.entity.TbFarm;
import org.zcy.agriculture.vo.FarmUserRelationVo;
import org.zcy.agriculture.vo.farm.FarmDetailVo;
import org.zcy.agriculture.vo.registerlogin.LoginInfoVo;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

import java.util.HashMap;
import java.util.List;


/**
 * 农场(基础) 服务层
 *
 * @author numberone
 * @date 2019-06-25
 */
public interface ITbFarmService {
    /**
     * 查询农场(基础)信息
     *
     * @param farmId 农场(基础)ID
     * @return 农场(基础)信息
     */
    TbFarm selectTbFarmById(String farmId);

    /**
     * 查询农场(基础)列表
     *
     * @param tbFarm 农场(基础)信息
     * @return 农场(基础)集合
     */
    List<TbFarm> selectTbFarmList(TbFarm tbFarm);

    /**
     * 新增农场(基础)
     *
     * @param tbFarm 农场(基础)信息
     * @return 结果
     */
    int insertTbFarm(TbFarm tbFarm);

    /**
     * 修改农场(基础)
     *
     * @param tbFarm 农场(基础)信息
     * @return 结果
     */
    int updateTbFarm(TbFarm tbFarm);

    /**
     * 删除农场(基础)信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTbFarmByIds(String ids);


    List<FarmUserRelationVo> selectAllFarmsByPhone(String phone);

    /**
     * 创建农场以及农场和用户的关联信息
     *
     * @param tbFarm
     * @return
     */
    int insertFarmDetail(TbFarm tbFarm, LoginUserVo userVo) throws Exception;

    /**
     * 查询当前登录用户的所有农场（当前报警，我的农事，角色）
     * @param infoVo
     * @return
     */
    List<FarmDetailVo> selectFarmDetailList(LoginInfoVo infoVo);
    /**
	 * 根据农场ID查询农场所在地
	 * @param farmId
	 * @return
	 */
	public HashMap<String,Object> selectTbFarmByCity(String farmId);
}
