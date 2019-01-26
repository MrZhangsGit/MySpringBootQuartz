package my.springboot.quartz.dao;

import my.springboot.quartz.po.QuartzTaskInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangs
 * @Description
 * @createDate 2019/1/25
 */
@Mapper
@Repository
public interface IQuartzTaskInfoDao {
    /**
     * 获取数据正常状态下的任务信息
     * @param status
     * @return
     */
    List<QuartzTaskInfoPO> getTasks(Integer status);

    /**
     * 根据Id获取任务详细
     * @param taskNo
     * @return
     */
    QuartzTaskInfoPO getById(String taskNo);
}
