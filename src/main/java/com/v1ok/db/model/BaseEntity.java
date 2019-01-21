package com.v1ok.db.model;

import com.v1ok.db.service.ICreateBy;
import com.v1ok.db.service.IEntityModel;
import com.v1ok.db.service.IExtends;
import com.v1ok.db.service.ISoftDelete;
import com.v1ok.db.service.IUpdateBy;
import com.v1ok.db.service.IVersion;
import io.ebean.annotation.DbJsonB;
import io.ebean.annotation.SoftDelete;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import io.ebean.annotation.WhoCreated;
import io.ebean.annotation.WhoModified;
import java.util.Date;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * Created by liubinduo on 2017/6/28.
 */
@MappedSuperclass
public abstract class BaseEntity implements IEntityModel, ICreateBy, IUpdateBy, ISoftDelete,
    IVersion, IExtends {


  protected Long pid;
  protected String name;
  protected Long createBy;
  protected Long updateBy;
  protected Date createTime;
  protected Date updateTime;
  @SoftDelete
  protected Boolean deleted;

  protected Integer version;

  @DbJsonB(length = 1000)
  protected Map<String, Object> ext;

  public BaseEntity() {
    this.deleted = false;
  }


  @Id
  @GeneratedValue(generator = "uuid")
  @Column(name = "pid")
  @Override
  public Long getPid() {
    return pid;
  }

  @Override
  public void setPid(Long id) {
    this.pid = id;
  }


  @Basic
  @Column(name = "name")
  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  @Basic
  @Column(name = "create_by", updatable = false)
  @WhoCreated
  public Long getCreateBy() {
    return createBy;
  }

  @Override
  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  @Override
  @Basic
  @Column(name = "update_by")
  @WhoModified
  public Long getUpdateBy() {
    return updateBy;
  }

  @Override
  public void setUpdateBy(Long updateBy) {
    this.updateBy = updateBy;
  }

  @Override
  @Basic
  @Column(name = "create_time", updatable = false)
  @WhenCreated
  public Date getCreateTime() {
    return createTime;
  }

  @Override
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  @Basic
  @Column(name = "update_time")
  @WhenModified
  public Date getUpdateTime() {
    return updateTime;
  }

  @Override
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  @Basic
  @Column(name = "deleted")
  public Boolean isDeleted() {
    return deleted;
  }

  @Override
  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  @Version
  @Column(name = "version")
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }


  @Override
  @Column(name = "ext")
  public Map<String, Object> getExt() {
    return ext;
  }

  @Override
  public void setExt(Map<String, Object> ext) {
    this.ext = ext;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, false);
  }
}