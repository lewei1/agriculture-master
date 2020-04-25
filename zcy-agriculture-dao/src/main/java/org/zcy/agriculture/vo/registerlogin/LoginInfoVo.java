package org.zcy.agriculture.vo.registerlogin;

import org.zcy.agriculture.vo.BaseVo;

import java.util.List;

/**
 * 登录用户类型和信息类
 */
public class LoginInfoVo extends BaseVo {

    private List<LoginUserVo> loginUserVoList;

    private String type;

    public List<LoginUserVo> getLoginUserVoList() {
        return loginUserVoList;
    }

    public void setLoginUserVoList(List<LoginUserVo> loginUserVoList) {
        this.loginUserVoList = loginUserVoList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
