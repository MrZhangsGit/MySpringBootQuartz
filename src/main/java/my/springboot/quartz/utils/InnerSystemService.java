package my.springboot.quartz.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import my.springboot.quartz.job.QuartzMainJobFactory;
import my.springboot.quartz.po.QuartzTaskInfoPO;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @author zhangs
 * @Description
 * @createDate 2019/1/25
 */
@Component
@Slf4j
public class InnerSystemService {

    /**
     * 表达式合法性校验
     * 判空、表达式的位数、每位的取值以及通配符的判断
     * @param cron
     * @return
     */
    public boolean cronCheck(String cron) {
        //TODO

        return true;
    }

    /**
     * 执行任务
     * @param quartzTaskInfo
     * @param scheduler
     * @throws SchedulerException
     */
    public void startSchedule(QuartzTaskInfoPO quartzTaskInfo, Scheduler scheduler) throws SchedulerException {
        log.info("执行任务---{}---入参:{}", Thread.currentThread().getStackTrace()[1].getMethodName(),
                JSON.toJSONString(quartzTaskInfo));

        /**
         * 触发器
         */
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzTaskInfo.getTaskNo(), Scheduler.DEFAULT_GROUP);

        JobDetail jobDetail = JobBuilder.newJob(QuartzMainJobFactory.class)
                .withDescription(quartzTaskInfo.getTaskName())
                .withIdentity(quartzTaskInfo.getTaskNo(), Scheduler.DEFAULT_GROUP)
                .build();
        /**
         * JobDataMap用于传递数据
         */
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("id", quartzTaskInfo.getId().toString());
        jobDataMap.put("taskNo", quartzTaskInfo.getTaskNo());

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzTaskInfo.getTaskRule());

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withDescription(quartzTaskInfo.getTaskName())
                .withIdentity(triggerKey)
                .withSchedule(cronScheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        log.info("执行任务---{}---taskNo={},taskName={},taskRule={} load to quartz Success!",
                Thread.currentThread().getStackTrace()[1].getMethodName(), quartzTaskInfo.getTaskNo(),
                quartzTaskInfo.getTaskName(), quartzTaskInfo.getTaskRule());
    }

    /**
     * 停止任务
     * @param quartzTaskInfo
     * @param scheduler
     */
    public void stopSchedule(QuartzTaskInfoPO quartzTaskInfo, Scheduler scheduler) throws SchedulerException {
        log.info("停止任务---{}---入参:{}", Thread.currentThread().getStackTrace()[1].getMethodName(),
                JSON.toJSONString(quartzTaskInfo));
        scheduler.deleteJob(new JobKey(quartzTaskInfo.getTaskNo()));
        log.info("停止任务---{}---taskNo={},taskName={},taskRule={} stop to quartz Success!",
                Thread.currentThread().getStackTrace()[1].getMethodName(), quartzTaskInfo.getTaskNo(),
                quartzTaskInfo.getTaskName(), quartzTaskInfo.getTaskRule());
    }
}
