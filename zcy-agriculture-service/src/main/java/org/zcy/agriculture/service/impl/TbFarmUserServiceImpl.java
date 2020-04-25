package org.zcy.agriculture.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbFarmUser;
import org.zcy.agriculture.mapper.TbFarmUserMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbFarmUserService;

import java.util.List;
import java.util.Map;

/**
 * 农场和用户中间 服务层实现
 *
 * @author numberone
 * @date 2019-07-11
 */
@Service
public class TbFarmUserServiceImpl implements ITbFarmUserService {
    @Autowired
    private TbFarmUserMapper tbFarmUserMapper;

    /**
     * 查询农场和用户中间信息
     *
     * @param farmUserId 农场和用户中间ID
     * @return 农场和用户中间信息
     */
    @Override
    public TbFarmUser selectTbFarmUserById(Long farmUserId) {
        return tbFarmUserMapper.selectTbFarmUserById(farmUserId);
    }

    /**
     * 查询农场和用户中间列表
     *
     * @param tbFarmUser 农场和用户中间信息
     * @return 农场和用户中间集合
     */
    @Override
    public List<TbFarmUser> selectTbFarmUserList(TbFarmUser tbFarmUser) {
        return tbFarmUserMapper.selectTbFarmUserList(tbFarmUser);
    }

    /**
     * 新增农场和用户中间
     *
     * @param tbFarmUser 农场和用户中间信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertTbFarmUser(TbFarmUser tbFarmUser) {
        return tbFarmUserMapper.insertTbFarmUser(tbFarmUser);
    }

    /**
     * 修改农场和用户中间
     *
     * @param tbFarmUser 农场和用户中间信息
     * @return 结果
     */
    @Override
    public int updateTbFarmUser(TbFarmUser tbFarmUser) {
        return tbFarmUserMapper.updateTbFarmUser(tbFarmUser);
    }

    /**
     * 删除农场和用户中间对象
     *
     * @param map userId,farmId
     * @return 结果
     */
    @Override
    public int deleteTbFarmUserByUserId(Map map) {
        return tbFarmUserMapper.deleteTbFarmUserByUserId(map);
    }

}
