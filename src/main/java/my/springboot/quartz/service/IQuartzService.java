package my.springboot.quartz.service;

/**
 * @author zhangs
 * @Description
 * @createDate 2019/1/24
 */
public interface IQuartzService {
    /**
     * 初始化加载定时任务
     */
    void initLoadTasks();

    /**
     * 开始任务
     * @param taskNo
     * @return
     */
    boolean startTask(String taskNo);

    /**
     * 结束任务
     * @param taskNo
     * @return
     */
    boolean stopTask(String taskNo);
}
