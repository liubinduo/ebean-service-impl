package com.v1ok.db.util;

import com.v1ok.uuid.IDGenerate;
import io.ebean.config.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UUIDGenerator implements IdGenerator {

  final
  IDGenerate generate;

  public UUIDGenerator(IDGenerate generate) {
    this.generate = generate;
  }

  @Override
  public Object nextValue() {

    if (generate == null) {
      throw new IllegalArgumentException(IDGenerate.class.getCanonicalName() + " is not found.");
    }

    return generate.nextId();
  }

  @Override
  public String getName() {
    return "uuid";
  }
}
