package my.springboot.quartz.po;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangs
 * @Description
 * @createDate 2019/1/25
 */
@Data
public class QuartzTaskInfoPO {
    /**
     * 数据Id
     */
    private Long id;

    /**
     * 版本号：需要乐观锁控制
     */
    private Integer version;

    /**
     * 任务编号
     */
    private String taskNo;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务规则
     */
    private String taskRule;

    /**
     * 任务状态
     * 0:冻结|1:解冻
     */
    private Integer taskState;

    /**
     * 描述
     */
    private String description;

    /**
     * 冻结时间
     */
    private Date frozenTime;

    /**
     * 解冻时间
     */
    private Date unfrozenTime;

    /**
     * 数据状态
     * 0 删除|1 正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
