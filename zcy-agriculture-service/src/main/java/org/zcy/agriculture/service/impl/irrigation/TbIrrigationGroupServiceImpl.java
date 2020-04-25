package org.zcy.agriculture.service.impl.irrigation;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.constants.ThingsboardUrlConstants;
import org.zcy.agriculture.entity.TbIrrigationDevice;
import org.zcy.agriculture.entity.TbIrrigationDeviceImg;
import org.zcy.agriculture.entity.TbIrrigationGroup;
import org.zcy.agriculture.entity.TbResDevice;
import org.zcy.agriculture.enums.DevStatusEnum;
import org.zcy.agriculture.enums.IrrigationGroupStatusEnum;
import org.zcy.agriculture.mapper.irrigation.TbIrrigationDeviceImgMapper;
import org.zcy.agriculture.mapper.irrigation.TbIrrigationDeviceMapper;
import org.zcy.agriculture.mapper.irrigation.TbIrrigationGroupMapper;
import org.zcy.agriculture.mapper.TbResDeviceMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.irrigation.IrrigationDetailParam;
import org.zcy.agriculture.param.irrigation.IrrigationDeviceDetailParam;
import org.zcy.agriculture.param.irrigation.IrrigationIOTParam;
import org.zcy.agriculture.service.impl.BaseServiceImpl;
import org.zcy.agriculture.service.irrigation.ITbIrrigationGroupService;
import org.zcy.agriculture.util.BeanUtils;
import org.zcy.agriculture.util.ValidationUtil;
import org.zcy.agriculture.vo.irrigation.IrrigationDeviceDetailVo;
import org.zcy.agriculture.vo.irrigation.IrrigationGroupDetailVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 灌溉中心分组 服务层实现
 *
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbIrrigationGroupServiceImpl extends BaseServiceImpl implements ITbIrrigationGroupService {
    @Autowired
    private TbIrrigationGroupMapper tbIrrigationGroupMapper;

    @Autowired
    private TbIrrigationDeviceMapper tbIrrigationDeviceMapper;

    @Autowired
    private TbResDeviceMapper tbResDeviceMapper;

    @Autowired
    private TbIrrigationDeviceImgMapper tbIrrigationDeviceImgMapper;

    /**
     * 查询灌溉中心分组信息
     *
     * @param groupId 灌溉中心分组ID
     * @return 灌溉中心分组信息
     */
    @Override
    public TbIrrigationGroup selectTbIrrigationGroupById(Long groupId) {
        return tbIrrigationGroupMapper.selectTbIrrigationGroupById(groupId);
    }

    /**
     * 查询灌溉中心分组列表
     *
     * @param tbIrrigationGroup 灌溉中心分组信息
     * @return 灌溉中心分组集合
     */
    @Override
    public List<TbIrrigationGroup> selectTbIrrigationGroupList(TbIrrigationGroup tbIrrigationGroup) {
        return tbIrrigationGroupMapper.selectTbIrrigationGroupList(tbIrrigationGroup);
    }

    /**
     * 新增灌溉中心分组
     *
     * @param tbIrrigationGroup 灌溉中心分组信息
     * @return 结果
     */
    @Override
    public int insertTbIrrigationGroup(TbIrrigationGroup tbIrrigationGroup) {
        return tbIrrigationGroupMapper.insertTbIrrigationGroup(tbIrrigationGroup);
    }

    /**
     * 修改灌溉中心分组
     *
     * @param irrigationDetailParam 灌溉中心分组信息
     * @return 结果
     */
    @Override
    public int updateTbIrrigationGroup(IrrigationDetailParam irrigationDetailParam) {

        int index = tbIrrigationGroupMapper.updateTbIrrigationGroup(irrigationDetailParam);
        if(index > 0) {

            TbIrrigationDevice serarchDevice = new TbIrrigationDevice();
            serarchDevice.setGroupId(irrigationDetailParam.getGroupId());
            List<TbIrrigationDevice> list = tbIrrigationDeviceMapper.selectTbIrrigationDeviceList(serarchDevice);
            List<TbIrrigationDevice> updateDeviceList = new ArrayList<>();

            if(!list.isEmpty() && !irrigationDetailParam.getDeviceList().isEmpty())  {
                boolean isUpdate;
                for(TbIrrigationDevice device : irrigationDetailParam.getDeviceList()) {
                    isUpdate = true;
                    for(TbIrrigationDevice device1 : list) {
                        if(device.getDeviceId() == device1.getDeviceId()) {
                            list.remove(device1);
                            isUpdate = false;
                            break;
                        }
                    }

                    if(isUpdate) {
                        device.setGroupId(irrigationDetailParam.getGroupId());
                        updateDeviceList.add(device);
                    }
                }
            }

            if (!ValidationUtil.isEmpty(updateDeviceList)) {
                tbIrrigationDeviceMapper.insertTbIrrigationDeviceList(updateDeviceList);
            }

            if (!ValidationUtil.isEmpty(list)) {
                for(TbIrrigationDevice device  : list) {
                    tbIrrigationDeviceMapper.deleteTbIrrigationDevice(device);
                }
            }
        }
        return index;
    }

    /**
     * 删除灌溉中心分组对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbIrrigationGroupByIds(String ids) {
        return tbIrrigationGroupMapper.deleteTbIrrigationGroupByIds(Convert.toStrArray(ids));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertTbIrrigationGroupDetail(IrrigationDetailParam irrigationDetailParam) throws Exception {
        int result;
        try {
            result = tbIrrigationGroupMapper.insertTbIrrigationGroup(irrigationDetailParam);
            if (result < 0)
                return result;

            List<TbIrrigationDevice> deviceList = irrigationDetailParam.getDeviceList();
            if (!ValidationUtil.isEmpty(deviceList)) {
                //填充分组id
                for (TbIrrigationDevice irrigationDevice : deviceList) {
                    irrigationDevice.setGroupId(irrigationDetailParam.getGroupId());
                }
                result = tbIrrigationDeviceMapper.insertTbIrrigationDeviceList(deviceList);
            }
        } catch (Exception e) {
            throw new Exception();
        }
        return result;
    }

    @Override
    public int deleteTbIrrigationDevice(TbIrrigationDevice irrigationDevice) {
        return tbIrrigationDeviceMapper.deleteTbIrrigationDevice(irrigationDevice);
    }

    @Override
    public List<IrrigationGroupDetailVo> selectIrrigationGroupDetailList(String farmId) {
        return tbIrrigationGroupMapper.selectIrrigationGroupDetailList(farmId);
    }

    @Override
    public int updateIrrigationGroupStatus(TbIrrigationGroup group) {

        int result = -1;
        TbIrrigationDevice device = new TbIrrigationDevice();
        device.setGroupId(group.getGroupId());

        //状态转换成物联网适配的状态
        Integer groupStatus = group.getGroupStatus();
        if (IrrigationGroupStatusEnum.GROUP_DELETE.getCode().equals(groupStatus)) {

            return tbIrrigationGroupMapper.updateTbIrrigationGroup(group);
        } else if (IrrigationGroupStatusEnum.GROUP_OPEN.getCode().equals(groupStatus)) {

            groupStatus = DevStatusEnum.RUNING.getCode();
        } else if (IrrigationGroupStatusEnum.GROUP_CLOSE.getCode().equals(groupStatus)) {

            groupStatus = DevStatusEnum.READY.getCode();
        }

        List<TbIrrigationDevice> irrigationDeviceList = tbIrrigationDeviceMapper.selectTbIrrigationDeviceList(device);
        if (ValidationUtil.isEmpty(irrigationDeviceList))
            return result;

        IrrigationIOTParam param = new IrrigationIOTParam();
        param.setMethod(ThingsboardUrlConstants.DEVICE_CONTROL_METHOD);
        param.setParams(new Object());

        List<TbResDevice> deviceList = Lists.newArrayList();
        for (TbIrrigationDevice d : irrigationDeviceList) {
            TbResDevice resDevice = new TbResDevice();

            TbResDevice tbResDevice = tbResDeviceMapper.selectTbResDeviceById(d.getDeviceId());
            if (!ValidationUtil.isEmpty(tbResDevice) && !ValidationUtil.isEmpty(tbResDevice.getDevNum()))
                resDevice.setDevNum(tbResDevice.getDevNum());
            resDevice.setStatus(groupStatus);
            deviceList.add(resDevice);
        }
        param.setDeviceList(deviceList);

        //分组设备发到物联网
        List<TbResDevice> resDeviceList = processIOTDevice(param);
        for (TbResDevice d : resDeviceList) {
            if (d.getStatus() != groupStatus) {
                return result;
            }
        }

        //更新设备表的设备状态
        result = tbResDeviceMapper.updateResDeviceListStatus(resDeviceList);
        if (result < 0)
            return result;

        return tbIrrigationGroupMapper.updateTbIrrigationGroup(group);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertIrrigationDevice(IrrigationDeviceDetailParam param) throws Exception {

        int result ;
        try {
            result = tbResDeviceMapper.insertTbResDevice(param);
            if(result < 0)
                return result;

            TbIrrigationDeviceImg irrigationDeviceImg = new TbIrrigationDeviceImg();
            irrigationDeviceImg.setImgUrl(param.getImgUrl());
            //tb_res_device中的devId
            irrigationDeviceImg.setDeviceId(param.getDevId());
            //user_code
            irrigationDeviceImg.setCreateBy(param.getCreateBy());
            irrigationDeviceImg.setFarmId(param.getFarmId());
            result = tbIrrigationDeviceImgMapper.insertTbIrrigationDeviceImg(irrigationDeviceImg);
        } catch (Exception e) {
            throw new Exception();
        }

        return result;
    }

    @Override
    public int updateIrrigationDevice(IrrigationDeviceDetailParam param) throws Exception {
        int result ;
        try {
            result = tbResDeviceMapper.updateTbResDevice(param);
            if(result < 0)
                return result;


            //先删除tb_res_device和tb_irrigation_device_img的关联
            tbIrrigationDeviceImgMapper.deleteIrrigationDeviceImgByDevId(param.getDevId());

            TbIrrigationDeviceImg irrigationDeviceImg = new TbIrrigationDeviceImg();
            irrigationDeviceImg.setImgUrl(param.getImgUrl());
            //tb_res_device中的devId
            irrigationDeviceImg.setDeviceId(param.getDevId());
            //user_code
            irrigationDeviceImg.setCreateBy(param.getCreateBy());
            irrigationDeviceImg.setFarmId(param.getFarmId());
            result = tbIrrigationDeviceImgMapper.insertTbIrrigationDeviceImg(irrigationDeviceImg);
        } catch (Exception e) {
            throw new Exception();
        }

        return result;
    }

    @Override
    public List<TbIrrigationDeviceImg> selectDefaultDeviceImgList() {
        return tbIrrigationDeviceImgMapper.selectDefaultDeviceImgList();
    }

    @Override
    public List<IrrigationDeviceDetailVo> selectDeviceDetailList(TbResDevice resDevice) {

        List<IrrigationDeviceDetailVo> voList = Lists.newArrayList();
        List<TbResDevice> deviceList = tbResDeviceMapper.selectTbResDeviceList(resDevice);
        if(!ValidationUtil.isEmpty(deviceList)) {

            for (TbResDevice device : deviceList) {

                IrrigationDeviceDetailVo deviceDetailVo = new IrrigationDeviceDetailVo();
                BeanUtils.copyBeanProp(deviceDetailVo, device);
                TbIrrigationDeviceImg deviceImg = tbIrrigationDeviceImgMapper.selectIrrigationDeviceImgByDevId(device.getDevId());
                if(!ValidationUtil.isEmpty(deviceImg)) {
                    deviceDetailVo.setImgUrl(deviceImg.getImgUrl());
                }
                voList.add(deviceDetailVo);
            }
        }
        return voList;
    }


}
