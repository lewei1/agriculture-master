package org.zcy.agriculture.service;

import org.zcy.agriculture.entity.TbAmType;
import org.zcy.agriculture.util.HttpCodeMsg;

import java.util.List;

/**
 * 农机类型 服务层
 *
 * @author zh
 * @date 2019-06-21
 */
public interface IThingsboardService {

    public HttpCodeMsg getDeviceAttributesFromThingsboard(String devNum);

    public HttpCodeMsg getLiveStreamingFromThingsboard(String devNum);

}
