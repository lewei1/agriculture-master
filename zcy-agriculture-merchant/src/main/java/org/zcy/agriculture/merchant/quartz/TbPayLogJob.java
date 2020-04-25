package org.zcy.agriculture.merchant.quartz;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.zcy.agriculture.service.TbPayLogService;
import org.zcy.agriculture.util.DateUtils;

public class TbPayLogJob implements Job {
	public static Logger logger = LogManager.getLogger(TbPayLogJob.class);

	@Autowired
	private TbPayLogService tbPayLogService;

	/**
	 * 每天产生的PayLog 数据过多， 删除30天以前的数据
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			String date = DateUtils.parseDateToStr("yyyy-MM-dd", new Date());
			tbPayLogService.deleteByDate(date);
			logger.info("成功删除PayLog 数据异常：" + date);
		} catch (Exception e) {
			logger.error("删除PayLog 数据异常", e);
		}
	}
}
