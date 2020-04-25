package org.zcy.agriculture.merchant.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.constants.ValidationConstants;
import org.zcy.agriculture.entity.TbIrrigationGroup;
import org.zcy.agriculture.entity.TbIrrigationLog;
import org.zcy.agriculture.mapper.irrigation.TbIrrigationGroupMapper;
import org.zcy.agriculture.mapper.irrigation.TbIrrigationLogMapper;
import org.zcy.agriculture.page.AjaxResult;
import org.zcy.agriculture.util.ValidationUtil;

import java.util.Date;

@Aspect
@Component
@Order(1)
public class IrrigationOperationLogAspect {

    private static Logger logger = LogManager.getLogger(IrrigationOperationLogAspect.class);

    private static final String DEVICE_METHOD = "updateDeviceStatus";

    private static final String GROUP_DEVICE_METHOD = "updateStatus";

    private static final String CLASS_NAME = "TbIrrigationGroupController";

    @Autowired
    private TbIrrigationLogMapper tbIrrigationLogMapper;

    @Autowired
    private TbIrrigationGroupMapper tbIrrigationGroupMapper;


    @Pointcut("@annotation(org.zcy.agriculture.merchant.annotation.IrrigationOperationLog)")
    public void around() {

    }

    @Transactional(rollbackFor = Exception.class)
    @Around("around()")
    public Object aroundGroup(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        // 请求的类名
        String strClassName = joinPoint.getTarget().getClass().getSimpleName();
        // 请求的方法名
        String methodName = joinPoint.getSignature().getName();

        //状态
        Integer status = -1;

        TbIrrigationLog irrigationLog = new TbIrrigationLog();

        //请求的参数
        Object[] objArray = joinPoint.getArgs();
        if (!ValidationUtil.isEmpty(objArray)) {
            for (Object obj : objArray) {
                
                if (obj instanceof Long) {
                    //灌溉分组id是Long类型
                    Long id = (Long) obj;
                    if (CLASS_NAME.equalsIgnoreCase(strClassName)) {
                        if (DEVICE_METHOD.equalsIgnoreCase(methodName)) {
                            //设备
                        } else {
                            //分组
                            TbIrrigationGroup irrigationGroup = tbIrrigationGroupMapper.selectTbIrrigationGroupById(id);
                            if(!ValidationUtil.isEmpty(irrigationGroup))
                                irrigationLog.setGroupName(irrigationGroup.getGroupName());
                            else {
                                logger.error("分组名称"+ ValidationConstants.SUFFIX_NOT_EMPTY);
                            }
                        }
                    }
                }

                if (obj instanceof Integer) {
                    //状态是Integer类型
                    status = (Integer) obj;
                    if (CLASS_NAME.equalsIgnoreCase(strClassName)) {
                        if (DEVICE_METHOD.equalsIgnoreCase(methodName)) {
                            //更新单个设备状态
                            String actionContent = "";
                            if (status == 1)
                                actionContent = "手动打开设施";
                            else if (status == 3)
                                actionContent = "手动关闭设施";

                            irrigationLog.setActionContent(actionContent);
                        } else {
                            //更新分组状态，0-关闭全部，1-打开全部
                            irrigationLog.setActionContent(status == 0 ? "手动关闭分组" : "手动打开分组");
                        }
                    }

                }
            }
        }

        result = joinPoint.proceed();
        if (result instanceof AjaxResult) {
            AjaxResult ar = (AjaxResult) result;
            //农场id
            Object farmId = ar.get("farmId");
            Object actionBy = ar.get("actionBy");
            if(!ValidationUtil.isEmpty(farmId)) {
                irrigationLog.setFarmId((String)farmId);
            }else {
                logger.error("农场id"+ ValidationConstants.SUFFIX_NOT_EMPTY);
            }

            Integer rs = AjaxResult.getResultStatus(ar);
            //操作成功并且不是分组的删除操作
            if (rs == 0 && !(methodName.equals(GROUP_DEVICE_METHOD) &&  status == 2)) {
                irrigationLog.setActionTime(new Date());
                irrigationLog.setActionBy((Long)actionBy);
                //插入日志表
                tbIrrigationLogMapper.insertTbIrrigationLog(irrigationLog);
            }
        }

        return result;
    }
}
