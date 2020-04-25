package org.zcy.agriculture.mapper;

import org.zcy.agriculture.entity.TbFarmUser;

import java.util.List;
import java.util.Map;

/**
 * 农场和用户中间 数据层
 *
 * @author numberone
 * @date 2019-07-11
 */
public interface TbFarmUserMapper {
    /**
     * 查询农场和用户中间信息
     *
     * @param farmUserId 农场和用户中间ID
     * @return 农场和用户中间信息
     */
    public TbFarmUser selectTbFarmUserById(Long farmUserId);

    /**
     * 查询农场和用户中间列表
     *
     * @param tbFarmUser 农场和用户中间信息
     * @return 农场和用户中间集合
     */
    public List<TbFarmUser> selectTbFarmUserList(TbFarmUser tbFarmUser);

    /**
     * 新增农场和用户中间
     *
     * @param tbFarmUser 农场和用户中间信息
     * @return 结果
     */
    public int insertTbFarmUser(TbFarmUser tbFarmUser);

    /**
     * 修改农场和用户中间
     *
     * @param tbFarmUser 农场和用户中间信息
     * @return 结果
     */
    public int updateTbFarmUser(TbFarmUser tbFarmUser);

    /**
     * 删除农场和用户中间
     *
     * @param map userId,farmId
     * @return 结果
     */
    public int deleteTbFarmUserByUserId(Map map);


}