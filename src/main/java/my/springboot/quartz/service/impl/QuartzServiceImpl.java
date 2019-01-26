package my.springboot.quartz.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import my.springboot.quartz.dao.IQuartzTaskInfoDao;
import my.springboot.quartz.enumerate.DataStatusEnum;
import my.springboot.quartz.po.QuartzTaskInfoPO;
import my.springboot.quartz.service.IQuartzService;
import my.springboot.quartz.utils.InnerSystemService;
import org.quartz.Scheduler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhangs
 * @Description
 * @createDate 2019/1/24
 */
@Service
@Slf4j
public class QuartzServiceImpl implements IQuartzService, InitializingBean {
    @Autowired
    private IQuartzTaskInfoDao iQuartzTaskInfoDao;

    @Autowired
    private SchedulerFactoryBean schedulerBean;

    @Autowired
    private InnerSystemService innerSystemService;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("{}...系统初始化加载任务...", Thread.currentThread().getStackTrace()[1].getMethodName());
        this.initLoadTasks();
    }

    /**
     * 初始化加载定时任务
     */
    @Override
    public void initLoadTasks() {
        log.info("初始化加载定时任务---{}---", Thread.currentThread().getStackTrace()[1].getMethodName());
        List<QuartzTaskInfoPO> quartzTaskInfoPOS = iQuartzTaskInfoDao.getTasks(DataStatusEnum.DATA_STATUS_ENABLE.getStatusCode());
        log.info("初始化加载定时任务---{}---From DB:{}", Thread.currentThread().getStackTrace()[1].getMethodName(),
                JSON.toJSONString(quartzTaskInfoPOS));
        if (CollectionUtils.isEmpty(quartzTaskInfoPOS)) {
            log.info("初始化加载定时任务---{}---没有需要初始化时加载的任务",
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            return;
        }

        Scheduler scheduler = schedulerBean.getScheduler();
        for (QuartzTaskInfoPO quartzTaskInfo:quartzTaskInfoPOS) {
            try {
                innerSystemService.startSchedule(quartzTaskInfo, scheduler);
            } catch (Exception e) {
                log.error("初始化加载定时任务---{}---失败！ :taskNo={},taskName={} 失败原因exception={}",
                        Thread.currentThread().getStackTrace()[1].getMethodName(), quartzTaskInfo.getTaskNo(),
                        quartzTaskInfo.getTaskName(), JSON.toJSONString(e));
            }
        }
    }

    /**
     * 开始任务
     * @param taskNo
     * @return
     */
    @Override
    public boolean startTask(String taskNo) {
        log.info("开始执行定时任务---{}---入参:{}", Thread.currentThread().getStackTrace()[1].getMethodName(), taskNo);
        QuartzTaskInfoPO quartzTaskInfo = iQuartzTaskInfoDao.getById(taskNo);
        if (quartzTaskInfo == null) {
            return false;
        }
        if (DataStatusEnum.DATA_STATUS_DISABLE.getStatusCode().equals(quartzTaskInfo.getTaskState())) {
            return false;
        }
        if (DataStatusEnum.DATA_STATUS_DISABLE.getStatusCode().equals(quartzTaskInfo.getStatus())) {
            return false;
        }
        Scheduler scheduler = schedulerBean.getScheduler();
        try {
            innerSystemService.startSchedule(quartzTaskInfo, scheduler);
            return true;
        } catch (Exception e) {
            log.error("初始化加载定时任务---{}---失败！ :taskNo={},taskName={} 失败原因exception={}",
                    Thread.currentThread().getStackTrace()[1].getMethodName(), quartzTaskInfo.getTaskNo(),
                    quartzTaskInfo.getTaskName(), JSON.toJSONString(e));
            return false;
        }
    }

    /**
     * 结束任务
     * @param taskNo
     * @return
     */
    @Override
    public boolean stopTask(String taskNo) {
        log.info("结束执行定时任务---{}---入参:{}", Thread.currentThread().getStackTrace()[1].getMethodName(), taskNo);
        QuartzTaskInfoPO quartzTaskInfo = iQuartzTaskInfoDao.getById(taskNo);
        if (quartzTaskInfo == null) {
            return false;
        }
        if (DataStatusEnum.DATA_STATUS_DISABLE.getStatusCode().equals(quartzTaskInfo.getTaskState())) {
            return false;
        }
        if (DataStatusEnum.DATA_STATUS_DISABLE.getStatusCode().equals(quartzTaskInfo.getStatus())) {
            return false;
        }
        Scheduler scheduler = schedulerBean.getScheduler();
        try {
            innerSystemService.stopSchedule(quartzTaskInfo, scheduler);
            return true;
        } catch (Exception e) {
            log.error("结束执行定时任务---{}---失败！ :taskNo={},taskName={} 失败原因exception={}",
                    Thread.currentThread().getStackTrace()[1].getMethodName(), quartzTaskInfo.getTaskNo(),
                    quartzTaskInfo.getTaskName(), JSON.toJSONString(e));
            return false;
        }
    }
}
