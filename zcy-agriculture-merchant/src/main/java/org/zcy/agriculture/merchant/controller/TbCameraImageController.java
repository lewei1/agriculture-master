package org.zcy.agriculture.merchant.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.zcy.agriculture.constants.RequestStatus;
import org.zcy.agriculture.entity.TbCameraImage;
import org.zcy.agriculture.merchant.util.ExcelUtil;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.page.TableDataInfo;
import org.zcy.agriculture.service.ITbCameraImageService;
import org.zcy.agriculture.util.StringUtils;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 摄像头拍照记录 信息操作处理
 * 
 * @author zh
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/api/camera/image")
public class TbCameraImageController extends BaseController
{
    private String prefix = "system/tbCameraImage";
	
	@Autowired
	private ITbCameraImageService tbCameraImageService;

	@GetMapping()
	public String tbCameraImage()
	{
	    return prefix + "/tbCameraImage";
	}
	
	/**
	 * 查询摄像头拍照记录列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody TbCameraImage tbCameraImage)
	{
		if (ValidationUtil.isEmpty(tbCameraImage.getDevId())) {
			return getDataTable(RequestStatus.PARAM_REQUIRED.getStatus(), RequestStatus.PARAM_REQUIRED.getMessage());
		}

		startPage();
        List<TbCameraImage> list = tbCameraImageService.selectTbCameraImageList(tbCameraImage);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出摄像头拍照记录列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbCameraImage tbCameraImage)
    {
    	List<TbCameraImage> list = tbCameraImageService.selectTbCameraImageList(tbCameraImage);
        ExcelUtil<TbCameraImage> util = new ExcelUtil<TbCameraImage>(TbCameraImage.class);
        return util.exportExcel(list, "tbCameraImage");
    }
	
	/**
	 * 新增摄像头拍照记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存摄像头拍照记录
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TbCameraImage tbCameraImage)
	{		
		return toAjax(tbCameraImageService.insertTbCameraImage(tbCameraImage));
	}

	/**
	 * 修改摄像头拍照记录
	 */
	@GetMapping("/edit/{picId}")
	public String edit(@PathVariable("picId") Long picId, ModelMap mmap)
	{
		TbCameraImage tbCameraImage = tbCameraImageService.selectTbCameraImageById(picId);
		mmap.put("tbCameraImage", tbCameraImage);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存摄像头拍照记录
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TbCameraImage tbCameraImage)
	{		
		return toAjax(tbCameraImageService.updateTbCameraImage(tbCameraImage));
	}
	
	/**
	 * 删除摄像头拍照记录
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tbCameraImageService.deleteTbCameraImageByIds(ids));
	}
	
}
