package org.zcy.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.entity.TbAgriculturalMachine;
import org.zcy.agriculture.mapper.TbAgriculturalMachineMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.service.ITbAgriculturalMachineService;

/**
 * 农机 服务层实现
 * 
 * @author linlq
 * @date 2019-06-21
 */
@Service
public class TbAgriculturalMachineServiceImpl implements ITbAgriculturalMachineService
{
	@Autowired
	private TbAgriculturalMachineMapper tbAgriculturalMachineMapper;

	/**
     * 查询农机信息
     * 
     * @return 农机信息
     */
    @Override
	public TbAgriculturalMachine selectTbAgriculturalMachineByClass(TbAgriculturalMachine tbAgriculturalMachine)
	{
	    return tbAgriculturalMachineMapper.selectTbAgriculturalMachineByClass(tbAgriculturalMachine);
	}

	/**
	 * 查询农机信息
	 *
	 * @return 农机信息
	 */
	@Override
	public TbAgriculturalMachine selectTbAgriculturalMachineById(Long machineId)
	{
		return tbAgriculturalMachineMapper.selectTbAgriculturalMachineById(machineId);
	}

	@Override
	public List<TbAgriculturalMachine> selectTbAgriculturalMachineByName(TbAgriculturalMachine tbAgriculturalMachine) {
		return tbAgriculturalMachineMapper.selectTbAgriculturalMachineByName(tbAgriculturalMachine);
	}

	/**
     * 查询农机列表
     * 
     * @param tbAgriculturalMachine 农机信息
     * @return 农机集合
     */
	@Override
	public List<TbAgriculturalMachine> selectTbAgriculturalMachineList(TbAgriculturalMachine tbAgriculturalMachine)
	{
	    return tbAgriculturalMachineMapper.selectTbAgriculturalMachineList(tbAgriculturalMachine);
	}

	@Override
	public List<TbAgriculturalMachine> selectTbAgriculturalMachineImg() {
		return tbAgriculturalMachineMapper.selectTbAgriculturalMachineImg();
	}

	/**
     * 新增农机
     * 
     * @param tbAgriculturalMachine 农机信息
     * @return 结果
     */
	@Override
	public int insertTbAgriculturalMachine(TbAgriculturalMachine tbAgriculturalMachine)
	{
	    return tbAgriculturalMachineMapper.insertTbAgriculturalMachine(tbAgriculturalMachine);
	}
	
	/**
     * 修改农机
     * 
     * @param tbAgriculturalMachine 农机信息
     * @return 结果
     */
	@Override
	public int updateTbAgriculturalMachine(TbAgriculturalMachine tbAgriculturalMachine)
	{
	    return tbAgriculturalMachineMapper.updateTbAgriculturalMachine(tbAgriculturalMachine);
	}

	/**
     * 删除农机对象
     * 
     * @return 结果
     */
	@Override
	public int deleteTbAgriculturalMachineById(TbAgriculturalMachine tbAgriculturalMachine)
	{
		return tbAgriculturalMachineMapper.deleteTbAgriculturalMachineById(tbAgriculturalMachine);
	}
	
}
