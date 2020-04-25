package org.zcy.agriculture.service.impl.farm;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.constants.Constants;
import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.mapper.*;
import org.zcy.agriculture.mapper.farm.TbFarmMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.farm.ITbFarmService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.FarmUserRelationVo;
import org.zcy.agriculture.vo.farm.FarmDetailVo;
import org.zcy.agriculture.vo.registerlogin.LoginInfoVo;
import org.zcy.agriculture.vo.registerlogin.LoginUserVo;

import java.util.HashMap;
import java.util.List;

/**
 * 农场(基础) 服务层实现
 *
 * @author numberone
 * @date 2019-06-25
 */
@Service
public class TbFarmServiceImpl implements ITbFarmService {

    @Autowired
    private TbFarmMapper tbFarmMapper;

    @Autowired
    private TbFarmMerchantMapper tbFarmMerchantMapper;

    @Autowired
    private TbFarmUserMapper tbFarmUserMapper;

    @Autowired
    private TbFarmingPlanMapper tbFarmingPlanMapper;

    @Autowired
    private TbMessageRecordMapper tbMessageRecordMapper;



    /**
     * 查询农场(基础)信息
     *
     * @param farmId 农场(基础)ID
     * @return 农场(基础)信息
     */
    @Override
    public TbFarm selectTbFarmById(String farmId) {
        return tbFarmMapper.selectTbFarmById(farmId);
    }

    /**
     * 查询农场(基础)列表
     *
     * @param tbFarm 农场(基础)信息
     * @return 农场(基础)集合
     */
    @Override
    public List<TbFarm> selectTbFarmList(TbFarm tbFarm) {
        return tbFarmMapper.selectTbFarmList(tbFarm);
    }

    /**
     * 新增农场(基础)
     *
     * @param tbFarm 农场(基础)信息
     * @return 结果
     */
    @Override
    public int insertTbFarm(TbFarm tbFarm) {
        return tbFarmMapper.insertTbFarm(tbFarm);
    }

    /**
     * 修改农场(基础)
     *
     * @param tbFarm 农场(基础)信息
     * @return 结果
     */
    @Override
    public int updateTbFarm(TbFarm tbFarm) {
        return tbFarmMapper.updateTbFarm(tbFarm);
    }

    /**
     * 删除农场(基础)对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbFarmByIds(String ids) {
        return tbFarmMapper.deleteTbFarmByIds(Convert.toStrArray(ids));
    }


    @Override
    public List<FarmUserRelationVo> selectAllFarmsByPhone(String phone) {
        List<FarmUserRelationVo> farmList;
        //情形一：我是超管，所有农场都是我创建的
        farmList = tbFarmMapper.selectMerchantAllFarmList(phone);

        if (ValidationUtil.isEmpty(farmList)) {
            //情形二：我是普通成员，只能查询出加入的多个农场列表
            farmList = tbFarmMapper.selectUserAllFarmList(phone);
        }else {
            for(FarmUserRelationVo relationVo : farmList) {
                relationVo.setRoleId(1L);
                relationVo.setRoleName("超级管理员");
            }
        }
        return farmList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertFarmDetail(TbFarm tbFarm, LoginUserVo userVo) throws Exception {
        int result ;

        try {
            result = tbFarmMapper.insertTbFarm(tbFarm);
            if (result < 0) {
                return result;
            }
            //保存tb_farm_merchant
            TbFarmMerchant farmMerchant = new TbFarmMerchant();
            farmMerchant.setFarmId(tbFarm.getFarmId());
            //保存merchant_id
            farmMerchant.setMerchantId(userVo.getId());
            result = tbFarmMerchantMapper.insertTbFarmMerchant(farmMerchant);
        } catch (Exception e) {
            throw new Exception();
        }

        return result;
    }

    @Override
    public List<FarmDetailVo> selectFarmDetailList(LoginInfoVo infoVo) {

        List<FarmDetailVo> detailVoList = Lists.newArrayList();
        List<LoginUserVo> userVoList = infoVo.getLoginUserVoList();
        if(!ValidationUtil.isEmpty(userVoList) && userVoList.size() == 1) {
            LoginUserVo userVo = userVoList.get(0);

            if(Constants.MERCHANT.equals(infoVo.getType())) {

                //查询商户农场关联表tb_farm_merchant
                List<FarmUserRelationVo> farmList = tbFarmMapper.selectMerchantAllFarmList(userVo.getPhone());
                if(!ValidationUtil.isEmpty(farmList)) {

                    for(FarmUserRelationVo farm : farmList) {
                        FarmDetailVo detailVo = new FarmDetailVo();

                        BeanUtils.copyBeanProp(detailVo, farm);
                        detailVo.setRoleId(1L);
                        detailVo.setName("超级管理员");
                        //我的农事，当前报警
                        handleAlarmAndFarming(detailVo, farm, userVo);

                        detailVoList.add(detailVo);
                    }
                }
            }else if(Constants.USER.equals(infoVo.getType())) {

                //查询用户农场关联表tb_farm_user
                List<FarmUserRelationVo> farmList = tbFarmMapper.selectUserAllFarmList(userVo.getPhone());
                if(!ValidationUtil.isEmpty(farmList)) {
                    for(FarmUserRelationVo farm : farmList) {
                        FarmDetailVo detailVo = new FarmDetailVo();

                        BeanUtils.copyBeanProp(detailVo, farm);
                        detailVo.setRoleId(farm.getRoleId());
                        detailVo.setName(farm.getRoleName());

                        handleAlarmAndFarming(detailVo, farm, userVo);

                        detailVoList.add(detailVo);
                    }
                }

            }

        }

        //通过用户信息
        return detailVoList;
    }

    /**
     * 处理我的农事，当前报警
     * @param detailVo
     * @param farm
     * @param userVo
     */
    private void handleAlarmAndFarming(FarmDetailVo detailVo, FarmUserRelationVo farm, LoginUserVo userVo) {
        //当前报警
        List<HashMap<String, Object>> mapList = tbMessageRecordMapper.selectBystatisticsType(null, farm.getFarmId());
        if(!ValidationUtil.isEmpty(mapList)) {
            detailVo.setCurrentAlarm(Integer.parseInt(mapList.get(0).get("count").toString())-Integer.parseInt(mapList.get(0).get("msgTotal").toString()));
        }else {
            detailVo.setCurrentAlarm(0);
        }
        //我的农事
        detailVo.setMyFarming(tbFarmingPlanMapper.selectByFarmTask(farm.getFarmId(), userVo.getCode()));
    }

    /**
	 * 根据农场ID查询农场所在地
	 * @param farmId
	 * @return
	 */
	public HashMap<String,Object> selectTbFarmByCity(String farmId){
		return tbFarmMapper.selectTbFarmByCity(farmId);
	}
}
