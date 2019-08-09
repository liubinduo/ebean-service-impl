package com.v1ok;

import com.v1ok.db.util.UUIDGenerator;
import io.ebean.config.CurrentUserProvider;
import io.ebean.config.ServerConfig;
import io.ebean.spring.txn.SpringJdbcTransactionManager;
import javax.sql.DataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.v1ok.db")
public class AutoConfig implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Bean
  public ServerConfig serverConfig(@Autowired(required = false) DataSource dataSource,
      @Autowired UUIDGenerator uuidGenerator,
      @Autowired CurrentUserProvider currentUser) {
    ServerConfig config = new ServerConfig();

    config.setName("db");
    config.setCurrentUserProvider(currentUser);

//    // set the spring's datasource and transaction manager.
    config.setDataSource(dataSource);
    config.setExternalTransactionManager(new SpringJdbcTransactionManager());

    config.loadFromProperties();
    config.add(uuidGenerator);
    // set as default and register so that Model can be
    // used if desired for save() and update() etc
    config.setDefaultServer(true);
    config.setRegister(true);

    return config;
  }

  @Bean
  public UUIDGenerator generator() {
    return new UUIDGenerator();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
