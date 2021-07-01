package com.v1ok;

import com.v1ok.auth.IContext;
import com.v1ok.auth.IUserContext;
import com.v1ok.commons.ContextHolder;
import io.ebean.config.CurrentTenantProvider;
import io.ebean.config.CurrentUserProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.v1ok.db")
public class AutoConfig {

  @Bean
  public CurrentUserProvider userProvider() {
    return () -> {
      IContext context = ContextHolder.getHolder().get();

      if (context == null) {
        return null;
      }
      IUserContext userContext = context.currentUser();

      return userContext.getPositions();
    };
  }

  @Bean
  public CurrentTenantProvider tenantProvider() {
    return () -> {
      IContext context = ContextHolder.getHolder().get();
      if (context == null) {
        return null;
      }
      IUserContext userContext = context.currentUser();
      if (userContext != null) {
        return userContext.getTenantId();
      }
      return "DEFAULT";
    };
  }
}
