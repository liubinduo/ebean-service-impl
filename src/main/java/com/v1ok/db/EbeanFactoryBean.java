package com.v1ok.db;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EbeanFactoryBean implements FactoryBean<EbeanServer> {


  @Autowired
  ServerConfig config;

  @Override
  public EbeanServer getObject() throws Exception {
    EbeanServer ebeanServer = EbeanServerFactory.create(config);
    return ebeanServer;
  }

  @Override
  public Class<?> getObjectType() {
    return EbeanServer.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

}
