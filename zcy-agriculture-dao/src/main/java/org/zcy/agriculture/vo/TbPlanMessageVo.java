package org.zcy.agriculture.vo;

import org.zcy.agriculture.entity.TbPlanMessage;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 农事记录消息表 tb_plan_message
 * 
 * @author numberone
 * @date 2019-07-05
 */
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class TbPlanMessageVo extends TbPlanMessage {
	private static final long serialVersionUID = 3484408503789985528L;
	private String nickName;//聊天用户姓名
	private String headUrl;//聊天用户头像
	private String [] imgUrls;//多张图片
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public String[] getImgUrls() {
		return imgUrls;
	}
	public void setImgUrls(String[] imgUrls) {
		this.imgUrls = imgUrls;
	}
	
}
