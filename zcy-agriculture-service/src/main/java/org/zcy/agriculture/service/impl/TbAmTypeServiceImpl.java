package org.zcy.agriculture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbAmType;
import org.zcy.agriculture.mapper.TbAmTypeMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbAmTypeService;

/**
 * 农机类型 服务层实现
 *
 * @author linlq
 * @date 2019-06-21
 */
@Service
public class TbAmTypeServiceImpl implements ITbAmTypeService {
    @Autowired
    private TbAmTypeMapper tbAmTypeMapper;

    /**
     * 查询农机类型信息
     *
     * @param machineTypeId 农机类型ID
     * @return 农机类型信息
     */
    @Override
    public TbAmType selectTbAmTypeById(Long machineTypeId) {
        return tbAmTypeMapper.selectTbAmTypeById(machineTypeId);
    }

    /**
     * 查询农机类型信息
     *
     * @return 农机类型信息
     */
    @Override
    public TbAmType selectTbAmTypeByClass(TbAmType tbAmType) {
        return tbAmTypeMapper.selectTbAmTypeByClass(tbAmType);
    }

    /**
     * 查询农机类型列表
     *
     * @param tbAmType 农机类型信息
     * @return 农机类型集合
     */
    @Override
    public List<TbAmType> selectTbAmTypeList(TbAmType tbAmType) {
        return tbAmTypeMapper.selectTbAmTypeList(tbAmType);
    }

    /**
     * 新增农机类型
     *
     * @param tbAmType 农机类型信息
     * @return 结果
     */
    @Override
    public int insertTbAmType(TbAmType tbAmType) {
        return tbAmTypeMapper.insertTbAmType(tbAmType);
    }

    /**
     * 修改农机类型
     *
     * @param tbAmType 农机类型信息
     * @return 结果
     */
    @Override
    public int updateTbAmType(TbAmType tbAmType) {
        return tbAmTypeMapper.updateTbAmType(tbAmType);
    }

    /**
     * 删除农机类型对象
     *
     * @return 结果
     */
    @Override
    public int deleteTbAmTypeById(TbAmType tbAmType) {
        return tbAmTypeMapper.deleteTbAmTypeById(tbAmType);
    }

}
