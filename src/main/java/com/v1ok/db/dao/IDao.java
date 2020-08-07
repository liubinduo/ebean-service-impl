package com.v1ok.db.dao;

import com.v1ok.db.model.IEntityModel;
import com.v1ok.db.support.QueryBean;
import io.ebean.Database;
import io.ebean.ExpressionList;
import io.ebean.Query;
import io.ebean.UpdateQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface IDao<Entity extends IEntityModel, PID extends Serializable> {

  boolean exists(PID id);

  boolean exists(Entity example);

  boolean exists(String propertyName, Object value);

  Optional<Entity> findOne(PID id);

  Optional<Entity> findOne(String propertyName, Object value);

  List<Entity> findAll();

  List<Entity> findAll(Entity example);

  List<Entity> findAll(PID... id);

  List<Entity> findAll(String propertyName, Object value);

  Page<Entity> pageQuery(int pageNo, int pageSize);

  Page<Entity> pageQuery(String propertyName, Object value, int pageNo, int pageSize);

  Page<Entity> pageQueryORExample(Entity example, int pageNo, int pageSize);

  Page<Entity> pageQueryANDExample(Entity example, int pageNo, int pageSize);

  Page<Entity> pageSearch(QueryBean queryBean);

  List<Entity> listSearch(QueryBean queryBean);

  Entity save(Entity entity);

  Iterable<Entity> save(Iterable<Entity> iterable);

  List<Entity> save(List<Entity> entities);

  Entity update(Entity entity);

  List<Entity> update(List<Entity> entities);

  Entity saveOrUpdate(Entity entity);

  Iterable<Entity> saveOrUpdate(Iterable<Entity> iterable);

  List<Entity> saveOrUpdate(List<Entity> entities);

  boolean delete(Entity entity);

  int delete(PID... id);

  int delete(List<Entity> entities);

  int delete(String propertyName, Object propertyValue);

  int remove(String propertyName, Object propertyValue);

  int remove(List<Entity> entities);

  boolean remove(Entity entity);

  int remove(PID id);

  Query<Entity> getQuery();

  UpdateQuery<Entity> getUpdate();

  Page<Entity> getPage(int pageNo, int pageSize, ExpressionList<Entity> where);

  Database getServer();

}
