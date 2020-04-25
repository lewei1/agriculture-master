package org.zcy.agriculture.operate.framework.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zcy.agriculture.service.system.ISysConfigService;

/**
 * numberone首创 html调用 thymeleaf 实现参数管理
 * 
 * @author numberone
 */
@Service("config")
public class ConfigService
{
    @Autowired
    @Qualifier("sysConfigServiceImpl")
    private ISysConfigService configService;

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configName 参数名称
     * @return 参数键值
     */
    public String getKey(String configKey)
    {
        return configService.selectConfigByKey(configKey);
    }
}
