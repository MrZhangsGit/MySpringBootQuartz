package my.springboot.quartz.enumerate;

/**
 * <desc>
 *      数据状态码定义。
 * </desc>
 * @createDate 2018/5/8
 * @author zhangs
 */
public enum DataStatusEnum {
    DATA_STATUS_DISABLE("删除", 0),
    DATA_STATUS_ENABLE("正常", 1),

    ;
    private String statusName;
    private Integer statusCode;

    DataStatusEnum() {
    }

    DataStatusEnum(String statusName, Integer statusCode) {
        this.statusName = statusName;
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
