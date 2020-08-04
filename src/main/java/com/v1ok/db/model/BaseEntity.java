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
public class BaseEntity implements IEntityModel,
    ICreateByModel, IUpdateByModel, ISoftDeleteModel,
    IVersionModel, IExtendsModel {

  @Basic
  @Column(name = "CREATE_BY", updatable = false)
  @WhoCreated
  protected String createBy;

  @Basic
  @Column(name = "UPDATE_BY")
  @WhoModified
  protected String updateBy;

  @Basic
  @Column(name = "CREATE_TIME", updatable = false)
  @WhenCreated
  protected Date createTime;

  @Basic
  @Column(name = "UPDATE_TIME")
  @WhenModified
  protected Date updateTime;

  @Basic
  @Column(name = "DELETED")
  @SoftDelete
  protected Boolean deleted;

  @Basic
  @Column(name = "REVISION")
  @Version
  protected Integer version;

  @Column(name = "EXT")
  @DbJsonB(length = 5000)
  protected Map<String, Object> ext;

  public BaseEntity() {
    this.deleted = false;
  }

}
