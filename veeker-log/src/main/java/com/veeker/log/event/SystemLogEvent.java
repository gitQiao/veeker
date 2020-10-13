package com.veeker.log.event;

import com.veeker.core.bus.event.DomainEvent;

/**
 * 日志事件模型
 *
 * @author ：qiaoliang
 */
public class SystemLogEvent extends DomainEvent {
    /**请求用户**/
    private String currUserId;
    /**请求参数**/
    private String inputParam;
    /**返回参数**/
    private String outParam;
    /**请求方法**/
    private String methodName;
    /**方法描述**/
    private String discription;
    /**请求方式**/
    private String method;
    /**请求Url**/
    private String url;
    /**功能模块**/
    private String businessType;
    /**操作人类别**/
    private String operatorType;

    /**
     * 获取body内容
     *
     * @author ：qiaoliang

     * @return java.lang.String
     * @date 2020-09-08 14:01
     */
    public String onlyParameterBody(){
        try{
            String substring = this.getInputParam().substring(this.getInputParam().indexOf(":") + 1);
            return substring.substring(0, substring.length() -1);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public SystemLogEvent() {
    }

    public SystemLogEvent(String currUserId, String inputParam, String outParam, String methodName, String discription,
                          String method, String url, String businessType, String operatorType) {
        this.currUserId = currUserId;
        this.inputParam = inputParam;
        this.outParam = outParam;
        this.methodName = methodName;
        this.discription = discription;
        this.method = method;
        this.url = url;
        this.businessType = businessType;
        this.operatorType = operatorType;
    }

    public String getCurrUserId() {
        return currUserId;
    }

    public void setCurrUserId(String currUserId) {
        this.currUserId = currUserId;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    public String getOutParam() {
        return outParam;
    }

    public void setOutParam(String outParam) {
        this.outParam = outParam;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
