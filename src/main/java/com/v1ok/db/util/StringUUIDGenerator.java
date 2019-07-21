package com.v1ok.db.util;

import com.v1ok.uuid.IDGenerate;
import io.ebean.config.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class StringUUIDGenerator implements IdGenerator {

  @Autowired(required = false)
  IDGenerate generate;


  @Override
  public Object nextValue() {

    if(generate == null){
      throw new IllegalArgumentException(IDGenerate.class.getCanonicalName()+" is not found.");
    }

    try {
      return generate.nextIdToString();
    } catch (InterruptedException e) {
      log.error("获取uuid时出错。", e);
    }
    return null;
  }

  @Override
  public String getName() {
    return "uuid-string";
  }
}
