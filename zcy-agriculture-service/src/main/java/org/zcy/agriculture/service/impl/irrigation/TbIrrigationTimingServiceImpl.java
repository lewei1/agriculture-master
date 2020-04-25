package org.zcy.agriculture.service.impl.irrigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbIrrigationTiming;
import org.zcy.agriculture.mapper.irrigation.TbIrrigationTimingMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.irrigation.IrrigationTimingDetailParam;
import org.zcy.agriculture.service.irrigation.ITbIrrigationTimingService;
import org.zcy.agriculture.util.ValidationUtil;

import java.util.List;

/**
 * 灌溉分组定时 服务层实现
 *
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbIrrigationTimingServiceImpl implements ITbIrrigationTimingService {
    @Autowired
    private TbIrrigationTimingMapper tbIrrigationTimingMapper;

    /**
     * 查询灌溉分组定时信息
     *
     * @param timingId 灌溉分组定时ID
     * @return 灌溉分组定时信息
     */
    @Override
    public TbIrrigationTiming selectTbIrrigationTimingById(Long timingId) {
        return tbIrrigationTimingMapper.selectTbIrrigationTimingById(timingId);
    }

    /**
     * 查询灌溉分组定时列表
     *
     * @param tbIrrigationTiming 灌溉分组定时信息
     * @return 灌溉分组定时集合
     */
    @Override
    public List<TbIrrigationTiming> selectTbIrrigationTimingList(TbIrrigationTiming tbIrrigationTiming) {
        return tbIrrigationTimingMapper.selectTbIrrigationTimingList(tbIrrigationTiming);
    }

    /**
     * 新增灌溉分组定时
     *
     * @param tbIrrigationTiming 灌溉分组定时信息
     * @return 结果
     */
    @Override
    public int insertTbIrrigationTiming(TbIrrigationTiming tbIrrigationTiming) {
        return tbIrrigationTimingMapper.insertTbIrrigationTiming(tbIrrigationTiming);
    }

    /**
     * 修改灌溉分组定时
     *
     * @param tbIrrigationTiming 灌溉分组定时信息
     * @return 结果
     */
    @Override
    public int updateTbIrrigationTiming(TbIrrigationTiming tbIrrigationTiming) {

        return tbIrrigationTimingMapper.updateTbIrrigationTiming(tbIrrigationTiming);
    }

    /**
     * 删除灌溉分组定时对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbIrrigationTimingByIds(String ids) {
        return tbIrrigationTimingMapper.deleteTbIrrigationTimingByIds(Convert.toStrArray(ids));
    }

    @Override
    public int insertTbIrrigationTimingDetail(IrrigationTimingDetailParam irrigationTimingDetailParam) {
        int result = -1;
        List<TbIrrigationTiming> timingList = irrigationTimingDetailParam.getTimingList();

        if(!ValidationUtil.isEmpty(timingList)) {
            processBean(irrigationTimingDetailParam);
            result = tbIrrigationTimingMapper.insertTbIrrigationTimingList(timingList);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateTbIrrigationTimingDetail(IrrigationTimingDetailParam irrigationTimingDetailParam) throws Exception {
        int result ;

        try {
            //删除原有的数据
            result = tbIrrigationTimingMapper.deleteTbIrrigationTimingByGroupId(irrigationTimingDetailParam.getGroupId());
            if(result < 0)
                return result;

            //重新插入数据
            result = insertTbIrrigationTimingDetail(irrigationTimingDetailParam);
        } catch (Exception e) {
            throw new Exception();
        }
        return result;
    }

    @Override
    public int updateTbIrrigationTimingByGroupId(Long groupId, Integer timingStatus) {
        return tbIrrigationTimingMapper.updateTbIrrigationTimingByGroupId(groupId, timingStatus);
    }

    /**
     * 填充分组id
     * @param irrigationTimingDetailParam
     */
    private void processBean(IrrigationTimingDetailParam irrigationTimingDetailParam) {
        //填充id
        for(TbIrrigationTiming timing : irrigationTimingDetailParam.getTimingList()) {
            timing.setGroupId(irrigationTimingDetailParam.getGroupId());
        }
    }

}
