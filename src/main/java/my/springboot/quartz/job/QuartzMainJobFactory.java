package my.springboot.quartz.job;

import lombok.extern.slf4j.Slf4j;
import my.springboot.quartz.service.IQuartzService;
import my.springboot.quartz.utils.ApplicationContextHolder;
import org.quartz.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangs
 * @Description 定时任务的主要执行逻辑，实现Job接口
 * @createDate 2019/1/25
 */
@DisallowConcurrentExecution
@Slf4j
public class QuartzMainJobFactory implements Job {
    private AtomicInteger atomicInteger;

    /**
     * 定时任务的主要执行逻辑
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("定时任务的主要执行逻辑---{}---", Thread.currentThread().getStackTrace()[1].getMethodName());
        atomicInteger = new AtomicInteger(0);

        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String id = jobDataMap.getString("id");
        String taskNo = jobDataMap.getString("taskNo");
        log.info("定时任务的主要执行逻辑---{}---TaskNo:{} 定时任务被执行",
                Thread.currentThread().getStackTrace()[1].getMethodName(), taskNo);

        IQuartzService iQuartzService = (IQuartzService) ApplicationContextHolder.getBean("quartzServiceImpl");

        //TODO 其他业务，如保存执行记录
    }
}
