package org.zcy.agriculture.merchant.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.zcy.agriculture.entity.*;
import org.zcy.agriculture.mapper.TbInventoryMaterialMapper;
import org.zcy.agriculture.mapper.TbMessageRecordMapper;
import org.zcy.agriculture.mapper.farm.TbFarmMapper;
import java.util.Date;
import java.util.List;

public class WarehouseCheckJob implements Job {
    public static Logger logger = LogManager.getLogger(WarehouseCheckJob.class);

    @Autowired
    private TbInventoryMaterialMapper tbInventoryMaterialMapper;

    @Autowired
    private TbMessageRecordMapper messageRecordMapper;

    @Autowired
    private TbFarmMapper farmMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {

            TbInventoryMaterial tbInventoryMaterial = new TbInventoryMaterial();
            List<TbInventoryMaterial> tbInventoryMaterialList = tbInventoryMaterialMapper.selectTbInventoryMaterialListForQuartz(tbInventoryMaterial);
            for (TbInventoryMaterial tbIM : tbInventoryMaterialList) {
                TbFarm farm = farmMapper.selectTbFarmById(tbIM.getFarmId());
                TbMessageRecord messageRecord = new TbMessageRecord();
                messageRecord.setCreateTime(new Date());
                messageRecord.setMessageType(0);
                messageRecord.setFarmId(tbIM.getFarmId());
                messageRecord.setMessageStatus(0);
                messageRecord.setMessageSubject(tbIM.getMaterialName() + "库存过低报警");//主题
                messageRecord.setMessageContent("农场【" + farm.getFarmName() + "】的【" + tbIM.getWarehouseName() + "】仓库的【" + tbIM.getMaterialName() + "】" + "库存为" +
                        tbIM.getQuantity() + tbIM.getUnitName() + "低于阈值" + tbIM.getAlarmStock() + tbIM.getUnitName());
                messageRecordMapper.insertTbMessageRecord(messageRecord); //插入报警消息到消息表
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
