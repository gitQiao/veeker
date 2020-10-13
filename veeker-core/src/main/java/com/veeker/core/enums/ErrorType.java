package com.veeker.core.enums;

import cn.hutool.http.HttpStatus;

public interface ErrorType {

  /**
   * 返回HttpStatus
   *
   * @author ：qiaoliang

   * @return int
   */
  default int getStatus(){
    return HttpStatus.HTTP_CONFLICT;
  }

  /**
   * 返回message
   *
   * @author ：qiaoliang

   * @return java.lang.String
   */
  String getMessage();

}
