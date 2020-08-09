package com.v1ok.db.exception;

import com.v1ok.commons.IRestResponse;
import com.v1ok.commons.impl.RestResponse;
import io.ebean.DuplicateKeyException;
import javax.persistence.OptimisticLockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalDBExceptionHandler {

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(OptimisticLockException.class)
  public IRestResponse<?> optimisticLockExceptionHandle(OptimisticLockException exception) {
    log.error("数据库报错", exception);
    return RestResponse.builder().error().message("数据可能已经被其它人修改，请重新获取最新数据后重试。");
  }

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(DuplicateKeyException.class)
  public IRestResponse<?> duplicateKeyExceptionHandle(DuplicateKeyException exception) {
    log.error("数据库报错", exception);
    return RestResponse.builder().error().message("键值冲突，请修改数据后重试。");
  }


}
