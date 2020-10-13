package com.veeker.log.domain;

import com.veeker.core.utils.DateUtils;
import com.veeker.core.utils.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ：qiaoliang
 */
public class Log {

    private String currUserId = "-";
    /**功能**/
    private String businessType;
    /**操作人类别**/
    private String operatorType;
    /**返回参数**/
    private Object outParam;
    /**请求参数**/
    private String inputParam;
    /**请求方法**/
    private String methodName;
    /**方法描述**/
    private String discription;
    /**请求方式**/
    private String method;
    /**请求Url**/
    private String url;
    /**耗时**/
    private long consumingTime;
    /**开始时间**/
    private String startTime;
    /**结束时间**/
    private String endTime;
    /**开始时间**/
    private long startTimeMillis = 0;
    /**结束时间**/
    private long endTimeMillis = 0;
    /**异常时间**/
    private String errorTime;
    /**异常内容**/
    private String errorCountent;


    /**
     * 解析请求参数
     *
     * @author ：qiaoliang
     * @param request : request
     * @param joinPoint : 请求
     */
    public void beforeCalling(HttpServletRequest request, JoinPoint joinPoint){
        if (Objects.nonNull(request)) {
            this.setUrl(request.getRequestURL().toString());
            this.setMethod(request.getMethod());
        }
        this.setStartTimeMillis(System.currentTimeMillis());
        this.setStartTime(DateUtils.formatCurrentDateTime());
        //获取传入目标方法的参数
        Map<String, Object> map = new HashMap<String, Object>();
        //参数名称
        String[] names=((MethodSignature) joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] objects=joinPoint.getArgs();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], objects[i]);
        }
        this.setInputParam(JsonUtils.toJson(map));
    }


    public void doAfterReturning(Object ret){
        this.setEndTime(DateUtils.formatCurrentDateTime());
        this.setEndTimeMillis(System.currentTimeMillis());
        this.setOutParam(JsonUtils.toJson(ret));
    }


    public void doAfterThrowing(Exception exception){
        this.setErrorTime(DateUtils.formatCurrentDateTime());
        this.setErrorCountent(exception.getMessage());
    }

    public String getCurrUserId() {
        return currUserId;
    }


    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public void setCurrUserId(String currUserId) {
        this.currUserId = currUserId;
    }

    public Object getOutParam() {
        return outParam;
    }

    public void setOutParam(Object outParam) {
        this.outParam = outParam;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getConsumingTime() {
        return endTimeMillis-startTimeMillis;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(String errorTime) {
        this.errorTime = errorTime;
    }

    public String getErrorCountent() {
        return errorCountent;
    }

    public void setErrorCountent(String errorCountent) {
        this.errorCountent = errorCountent;
    }
}
