package my.springboot.quartz.dto;

import lombok.Data;

/**
 * @author zhangs
 * @Description
 * @createDate 2019/1/25
 */
@Data
public class ConditionForQuartzTaskInfoDTO {
    /**
     * 数据Id
     */
    private Long id;

    /**
     * 任务编号
     */
    private String taskNo;
}
