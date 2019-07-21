package com.v1ok.db.exception;

import com.v1ok.commons.HeadCode;
import com.v1ok.commons.IRestResponse;
import com.v1ok.commons.impl.RestResponse;
import io.ebean.DuplicateKeyException;
import javax.persistence.OptimisticLockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalDBExceptionHandler {

//  @ExceptionHandler(Exception.class)
//  @ResponseBody
//  IRestResponse handleException(Exception e) {
//
//    log.error("服务器运行时出错！", e);
//
//    Throwable cause = e.getCause();
//    if (cause instanceof OptimisticLockException) {
//
//      return RestResponse.builder().error().message("数据可能已经被其它人修改，请重新获取最新数据后重试。");
//    }
//
//    if (cause instanceof DuplicateKeyException) {
//
//      return RestResponse.builder().error().message("键值冲突，请修改数据后重试。");
//    }
//
//    return RestResponse.builder().error(HeadCode.ERROR);
//  }


}
