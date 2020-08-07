package com.v1ok.db;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class EbeanFactoryBean implements FactoryBean<Database> {


  final
  DatabaseConfig config;

  public EbeanFactoryBean(DatabaseConfig config) {
    this.config = config;
  }

  @Override
  public Database getObject() {
    return DatabaseFactory.create(config);
  }

  @Override
  public Class<?> getObjectType() {
    return Database.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

}
