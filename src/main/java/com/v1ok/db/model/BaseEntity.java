package com.v1ok.db.model;


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
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Created by liubinduo on 2017/6/28.
 */
@MappedSuperclass
@Data
@EqualsAndHashCode
public abstract class BaseEntity implements IEntityModel,
    ICreateByModel, IUpdateByModel, ISoftDeleteModel,
    IVersionModel, IExtendsModel {

  @Basic
  @Column(name = "create_by", updatable = false)
  @WhoCreated
  protected Long createBy;

  @Basic
  @Column(name = "update_by")
  @WhoModified
  protected Long updateBy;

  @Basic
  @Column(name = "create_time", updatable = false)
  @WhenCreated
  protected Date createTime;

  @Basic
  @Column(name = "update_time")
  @WhenModified
  protected Date updateTime;

  @Basic
  @Column(name = "deleted")
  @SoftDelete
  protected Boolean deleted;

  @Basic
  @Column(name = "revision")
  @Version
  protected Integer revision;

  @Column(name = "ext")
  @DbJsonB(length = 1000)
  protected Map<String, Object> ext;

  public BaseEntity() {
    this.deleted = false;
  }

}
