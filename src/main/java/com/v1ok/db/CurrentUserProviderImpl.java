package com.v1ok.db;

import com.v1ok.commons.ContextHolder;
import io.ebean.config.CurrentUserProvider;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProviderImpl implements CurrentUserProvider {

  @Override
  public Object currentUser() {
    return ContextHolder.getHolder().get().currentUser().getUserId();
  }
}
