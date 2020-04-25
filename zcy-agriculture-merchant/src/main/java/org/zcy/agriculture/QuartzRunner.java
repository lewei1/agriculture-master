package org.zcy.agriculture;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.zcy.agriculture.merchant.quartz.CameraPhotoJob;
import org.zcy.agriculture.merchant.quartz.DeviceCheckJob;
import org.zcy.agriculture.merchant.quartz.TbPayLogJob;
import org.zcy.agriculture.merchant.quartz.WarehouseCheckJob;
import org.zcy.agriculture.util.QuartzUtil;

/**
 * 配置启动项目同时启动quartz任务
 */
@Component
public class QuartzRunner implements ApplicationRunner {

    @Autowired
    private QuartzUtil quartzUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==============配置quartz启动==================");
        boolean quartz = quartzUtil.addSimpleJob("TestQuartz", 5, TimeUnit.MINUTES, DeviceCheckJob.class);
        quartzUtil.addSimpleJob("CameraPhotoQuartz", 30, TimeUnit.MINUTES, CameraPhotoJob.class);
        quartzUtil.addSimpleJob("WarehouseCheckJob", 30, TimeUnit.MINUTES, WarehouseCheckJob.class);
        quartzUtil.addSimpleJob("TbPayLogJob", 1, TimeUnit.DAYS, TbPayLogJob.class);
        System.out.println("==============状态：" + quartz);
    }
}
