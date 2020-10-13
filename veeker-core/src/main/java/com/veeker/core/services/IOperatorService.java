package com.veeker.core.services;

/**
 * @author ：qiaoliang
 * @date ：2020-08-27
 */
public interface IOperatorService {

    /***
     *  获取操作者
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
     default String operator(){
         return "-";
     }

    /***
     *  获取操作者类型
     *
     * @author ：qiaoliang
     * @return java.lang.String
     */
    default String operatorType(){
        return "-";
    }

}
