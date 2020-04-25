package org.zcy.agriculture.service.impl.irrigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbIrrigationFixQuantity;
import org.zcy.agriculture.mapper.irrigation.TbIrrigationFixQuantityMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.irrigation.IrrigationFixQuantityDetailParam;
import org.zcy.agriculture.service.irrigation.ITbIrrigationFixQuantityService;
import org.zcy.agriculture.util.ValidationUtil;

import java.util.List;

/**
 * 灌溉分组定量 服务层实现
 *
 * @author numberone
 * @date 2019-07-01
 */
@Service
public class TbIrrigationFixQuantityServiceImpl implements ITbIrrigationFixQuantityService {
    @Autowired
    private TbIrrigationFixQuantityMapper tbIrrigationFixQuantityMapper;

    /**
     * 查询灌溉分组定量信息
     *
     * @param fixQuantityId 灌溉分组定量ID
     * @return 灌溉分组定量信息
     */
    @Override
    public TbIrrigationFixQuantity selectTbIrrigationFixQuantityById(Long fixQuantityId) {
        return tbIrrigationFixQuantityMapper.selectTbIrrigationFixQuantityById(fixQuantityId);
    }

    /**
     * 查询灌溉分组定量列表
     *
     * @param tbIrrigationFixQuantity 灌溉分组定量信息
     * @return 灌溉分组定量集合
     */
    @Override
    public List<TbIrrigationFixQuantity> selectTbIrrigationFixQuantityList(TbIrrigationFixQuantity tbIrrigationFixQuantity) {
        return tbIrrigationFixQuantityMapper.selectTbIrrigationFixQuantityList(tbIrrigationFixQuantity);
    }

    /**
     * 新增灌溉分组定量
     *
     * @param tbIrrigationFixQuantity 灌溉分组定量信息
     * @return 结果
     */
    @Override
    public int insertTbIrrigationFixQuantity(TbIrrigationFixQuantity tbIrrigationFixQuantity) {
        return tbIrrigationFixQuantityMapper.insertTbIrrigationFixQuantity(tbIrrigationFixQuantity);
    }

    /**
     * 修改灌溉分组定量
     *
     * @param tbIrrigationFixQuantity 灌溉分组定量信息
     * @return 结果
     */
    @Override
    public int updateTbIrrigationFixQuantity(TbIrrigationFixQuantity tbIrrigationFixQuantity) {
        return tbIrrigationFixQuantityMapper.updateTbIrrigationFixQuantity(tbIrrigationFixQuantity);
    }

    /**
     * 删除灌溉分组定量对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbIrrigationFixQuantityByIds(String ids) {
        return tbIrrigationFixQuantityMapper.deleteTbIrrigationFixQuantityByIds(Convert.toStrArray(ids));
    }

    @Override
    public int insertIrrigationFixQuantityDetail(IrrigationFixQuantityDetailParam param) {
        int result = -1;
        List<TbIrrigationFixQuantity> fixQuantityList = param.getFixQuantityList();
        if(!ValidationUtil.isEmpty(fixQuantityList)) {
            processBean(param);
            result = tbIrrigationFixQuantityMapper.insertIrrigationFixQuantityList(fixQuantityList);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateIrrigationFixQuantityDetail(IrrigationFixQuantityDetailParam param) throws Exception {
        int result ;

        try {
            //删除原有的数据
            result = tbIrrigationFixQuantityMapper.deleteIrrigationFixQuantityByGroupId(param.getGroupId());
            if(result < 0)
                return result;

            //重新插入数据
            result = insertIrrigationFixQuantityDetail(param);
        } catch (Exception e) {
            throw new Exception();
        }
        return result;
    }

    @Override
    public int updateIrrigationFixQuantityByGroupId(Long groupId, Integer fixQuantityStatus) {
        return tbIrrigationFixQuantityMapper.updateIrrigationFixQuantityByGroupId(groupId, fixQuantityStatus);
    }


    /**
     * 填充分组id
     * @param param
     */
    private void processBean(IrrigationFixQuantityDetailParam param) {
        //填充id
        for(TbIrrigationFixQuantity quantity : param.getFixQuantityList()) {
            quantity.setGroupId(param.getGroupId());
        }
    }

}
