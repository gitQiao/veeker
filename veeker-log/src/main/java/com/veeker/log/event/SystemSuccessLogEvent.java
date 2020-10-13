package com.veeker.log.event;

import java.util.Date;

/**
 * 日志事件模型
 *
 * @author ：qiaoliang
 */
public class SystemSuccessLogEvent extends SystemLogEvent {
    /**耗时**/
    private Long consumingTime;
    /**开始时间**/
    private Date startTime;
    /**结束时间**/
    private Date endTime;
    /**开始时间**/
    private long startTimeMillis = 0;
    /**结束时间**/
    private long endTimeMillis = 0;

    public SystemSuccessLogEvent() {
    }

    public SystemSuccessLogEvent(Long consumingTime, Date startTime, Date endTime, long startTimeMillis, long endTimeMillis) {
        this.consumingTime = consumingTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startTimeMillis = startTimeMillis;
        this.endTimeMillis = endTimeMillis;
    }

    public SystemSuccessLogEvent(String currUserId, String inputParam, String outParam, String methodName,
                                 String discription, String method, String url, String businessType, String operatorType,
                                 Long consumingTime, Date startTime, Date endTime, long startTimeMillis, long endTimeMillis) {
        super(currUserId, inputParam, outParam, methodName, discription, method, url, businessType, operatorType);
        this.consumingTime = consumingTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startTimeMillis = startTimeMillis;
        this.endTimeMillis = endTimeMillis;
    }

    public Long getConsumingTime() {
        return consumingTime;
    }

    public void setConsumingTime(Long consumingTime) {
        this.consumingTime = consumingTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getStartTimeMillis() {
        return startTimeMillis;
    }

    public void setStartTimeMillis(long startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }

    public long getEndTimeMillis() {
        return endTimeMillis;
    }

    public void setEndTimeMillis(long endTimeMillis) {
        this.endTimeMillis = endTimeMillis;
    }
}
