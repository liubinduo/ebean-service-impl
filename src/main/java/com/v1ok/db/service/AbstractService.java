package com.v1ok.db.service;

import com.v1ok.db.dao.IDao;
import com.v1ok.db.model.IEntityModel;
import com.v1ok.db.support.QueryBean;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * Created by liubinduo on 2017/6/28.
 */
@Slf4j
public abstract class AbstractService<T extends IEntityModel, ID extends Serializable>
    implements IService<T, ID> {


  @Autowired
  protected IDao<T,ID> dao;

  @Override
  public boolean exists(ID id) {
    return dao.exists(id);
  }

  @Override
  public boolean exists(T example) {
    return dao.exists(example);
  }

  @Override
  public boolean exists(String propertyName, Object value) {
    return dao.exists(propertyName, value);
  }

  @Override
  public Optional<T> findOne(ID id) {
    return dao.findOne(id);
  }

  @Override
  public Optional<T> findOne(String propertyName, Object value) {
    return dao.findOne(propertyName, value);
  }

  @Override
  public List<T> findAll() {
    return dao.findAll();
  }

  @Override
  public List<T> findAll(T example) {
    return dao.findAll(example);
  }

  @Override
  public List<T> findAll(ID... id) {
    return dao.findAll(id);
  }

  @Override
  public List<T> findAll(String propertyName, Object value) {
    return dao.findAll(propertyName, value);
  }

  @Override
  public Page<T> pageQuery(int pageNo, int pageSize) {
    return dao.pageQuery(pageNo, pageSize);
  }

  @Override
  public Page<T> pageQuery(String propertyName, Object value, int pageNo, int pageSize) {
    return dao.pageQuery(propertyName, value, pageNo, pageSize);
  }

  @Override
  public Page<T> pageQueryORExample(T example, int pageNo, int pageSize) {
    return dao.pageQueryORExample(example, pageNo, pageSize);
  }

  @Override
  public Page<T> pageQueryANDExample(T example, int pageNo, int pageSize) {
    return dao.pageQueryANDExample(example, pageNo, pageSize);
  }

  @Override
  public Page<T> pageSearch(QueryBean queryBean) {
    return dao.pageSearch(queryBean);
  }

  @Override
  public List<T> listSearch(QueryBean queryBean) {
    return dao.listSearch(queryBean);
  }

  @Override
  public T save(T entity) {
    return dao.save(entity);
  }

  @Override
  public Iterable<T> save(Iterable<T> iterable) {
    return dao.save(iterable);
  }

  @Override
  public List<T> save(List<T> entities) {
    return dao.save(entities);
  }

  @Override
  public T update(T entity) {
    return dao.update(entity);
  }

  @Override
  public List<T> update(List<T> entities) {
    return dao.update(entities);
  }

  @Override
  public boolean delete(T entity) {
    return dao.delete(entity);
  }

  @Override
  public int delete(ID... id) {
    return dao.delete(id);
  }

  @Override
  public int delete(List<T> entities) {
    return dao.delete(entities);
  }

  @Override
  public int delete(String propertyName, Object propertyValue) {
    return dao.delete(propertyName, propertyValue);
  }

  @Override
  public int remove(String propertyName, Object propertyValue) {
    return dao.remove(propertyName, propertyValue);
  }

  @Override
  public int remove(List<T> entities) {
    return dao.remove(entities);
  }

  @Override
  public boolean remove(T entity) {
    return dao.remove(entity);
  }

  @Override
  public int remove(ID id) {
    return dao.remove(id);
  }
}



