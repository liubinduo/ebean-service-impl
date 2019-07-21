package com.v1ok.db.dao;

import com.v1ok.db.model.IEntityModel;
import com.v1ok.db.support.QueryBean;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface IDao<T extends IEntityModel<ID>, ID extends Serializable> {

  boolean exists(ID id);

  boolean exists(T example);

  boolean exists(String propertyName, Object value);

  Optional<T> findOne(ID id);

  Optional<T> findOne(String propertyName, Object value);

  List<T> findAll();

  List<T> findAll(T example);

  List<T> findAll(ID... id);

  List<T> findAll(String propertyName, Object value);

  Page<T> pageQuery(int pageNo, int pageSize);

  Page<T> pageQuery(String propertyName, Object value, int pageNo, int pageSize);

  Page<T> pageQueryORExample(T example, int pageNo, int pageSize);

  Page<T> pageQueryANDExample(T example, int pageNo, int pageSize);

  Page<T> pageSearch(QueryBean queryBean);

  List<T> listSearch(QueryBean queryBean);

  T save(T entity);

  Iterable<T> save(Iterable<T> iterable);

  List<T> save(List<T> entities);

  T update(T entity);

  List<T> update(List<T> entities);

  boolean delete(T entity);

  int delete(ID... id);

  int delete(List<T> entities);

  int delete(String propertyName, Object propertyValue);

  int remove(String propertyName, Object propertyValue);

  int remove(List<T> entities);

  boolean remove(T entity);

  int remove(ID id);

}
