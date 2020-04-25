package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbAgriculturalMachine;

import java.util.List;

/**
 * 农机 服务层
 * 
 * @author linlq
 * @date 2019-06-21
 */
public interface ITbAgriculturalMachineService 
{


	/**
	 * 查询农机信息
	 *
	 * @return 农机信息
	 */
	public TbAgriculturalMachine selectTbAgriculturalMachineByClass(TbAgriculturalMachine tbAgriculturalMachine);



	/**
     * 查询农机信息
     * 
     * @return 农机信息
     */
	public TbAgriculturalMachine selectTbAgriculturalMachineById(Long machineId);

	/**
	 * 查询农机信息
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

	public List<TbAgriculturalMachine> selectTbAgriculturalMachineImg();
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
     * 删除农机信息
     * 
     * @return 结果
     */
	public int deleteTbAgriculturalMachineById(TbAgriculturalMachine tbAgriculturalMachine);
	
}
