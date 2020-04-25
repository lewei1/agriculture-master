package org.zcy.agriculture.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zcy.agriculture.entity.TbAgriculturalMachine;

import java.util.List;

/**
 * 农机 数据层
 *
 * @author linlq
 * @date 2019-06-21
 */
@Mapper
public interface TbAgriculturalMachineMapper {
    /**
     * 查询农机信息
     *
     * @return 农机信息
     */
    public TbAgriculturalMachine selectTbAgriculturalMachineByClass(TbAgriculturalMachine tbAgriculturalMachine);

    /**
     * 查询农机信息
     *
     * @param machineId 农机ID
     * @return 农机信息
     */
    public TbAgriculturalMachine selectTbAgriculturalMachineById(Long machineId);

    /**
     * 查询默认图标
     */
    public List<TbAgriculturalMachine> selectTbAgriculturalMachineImg();

    /**
     * 搜索农机
     *
     * @return 农机信息
     */
    public List<TbAgriculturalMachine> selectTbAgriculturalMachineByName(TbAgriculturalMachine tbAgriculturalMachine);

    /**
     * 查询农机列表
     *
     * @param tbAgriculturalMachine 农机信息
     * @return 农机集合
     */
    public List<TbAgriculturalMachine> selectTbAgriculturalMachineList(TbAgriculturalMachine tbAgriculturalMachine);

    /**
     * 新增农机
     *
     * @param tbAgriculturalMachine 农机信息
     * @return 结果
     */
    public int insertTbAgriculturalMachine(TbAgriculturalMachine tbAgriculturalMachine);

    /**
     * 修改农机
     *
     * @param tbAgriculturalMachine 农机信息
     * @return 结果
     */
    public int updateTbAgriculturalMachine(TbAgriculturalMachine tbAgriculturalMachine);

    /**
     * 删除农机
     *
     * @return 结果
     */
    public int deleteTbAgriculturalMachineById(TbAgriculturalMachine tbAgriculturalMachine);

    /**
     * 批量删除农机
     *
     * @param machineIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbAgriculturalMachineByIds(String[] machineIds);

}