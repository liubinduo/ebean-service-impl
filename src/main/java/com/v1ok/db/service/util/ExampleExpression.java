package com.v1ok.db.service.util;

import io.ebean.ExpressionList;
import io.ebean.bean.EntityBean;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public class ExampleExpression {


  public static void andExpression(ExpressionList expressionList, Object bean) {
    Validate.notNull(expressionList, "The expressionList is not null");
    Validate.notNull(bean, "The bean is not null");

    expression(expressionList.and(), null, bean);
    expressionList.endAnd();
  }

  public static void orExpression(ExpressionList expressionList, Object bean) {
    Validate.notNull(expressionList, "The expressionList is not null");
    Validate.notNull(bean, "The bean is not null");

    expression(expressionList.or(), null, bean);
    expressionList.endOr();
  }

  public static void expression(ExpressionList expressionList, Object bean) {
    Validate.notNull(expressionList, "The expressionList is not null");
    Validate.notNull(bean, "The bean is not null");

    expression(expressionList, null, bean);
  }

  private static void expression(ExpressionList expressionList, String parentProName, Object bean) {

    Validate.notNull(expressionList, "The expressionList is not null");
    Validate.notNull(bean, "The bean is not null");

    if (checkNullorEmpty(bean)) {
      throw new IllegalArgumentException("The bean is must not null or empty ");
    }

    EntityBean entityBean = checkEntityBean(bean);
    if (entityBean == null) {
      throw new IllegalArgumentException(
          "Was expecting an EntityBean but got a " + bean.getClass());
    }

    int proSize = entityBean._ebean_getPropertyNames().length;

    for (int i = 0; i < proSize; i++) {

      Object value = entityBean._ebean_getField(i);
      String propertyName = entityBean._ebean_getPropertyName(i);
      propertyName = parentProName == null ? propertyName : parentProName + "." + propertyName;

      if (value == null) {
        continue;
      }

      EntityBean subEntityBean = checkEntityBeanByCollection(value);

      if (subEntityBean != null) {
        expression(expressionList, propertyName, subEntityBean);
        continue;
      }

      Map map = checkIsMap(value);
      if (map != null) {
        entityBean._ebean_setField(i, null);
        final String pName = propertyName;
        Set<Entry> set = map.entrySet();
        Iterator<Entry> iterator = set.iterator();
        while (iterator.hasNext()) {
          Map.Entry entry = iterator.next();
          Object key = entry.getKey();
          Object v = entry.getValue();
          expressionList.jsonEqualTo(pName, key.toString(), v);
        }
        continue;
      }

      Collection collection = checkIsCollection(value);
      if (collection != null) {
        expressionList.arrayContains(propertyName, collection.toArray());
        continue;
      }
      if (value instanceof String) {
        String valueStr = (String) value;
        if (valueStr.startsWith("%") || valueStr.endsWith("%")) {
          expressionList.like(propertyName, valueStr);
          continue;
        }
        expressionList.eq(propertyName, value);
      }
      expressionList.eq(propertyName, value);

    }
  }

  static Map checkIsMap(Object bean) {
    Validate.notNull(bean, "The bean is not null");

    if (bean instanceof Map) {
      return (Map) bean;
    }

    return null;
  }

  static Collection checkIsCollection(Object bean) {
    Validate.notNull(bean, "The bean is not null");
    if (bean instanceof Collection) {
      return (Collection) bean;
    }
    return null;
  }

  static boolean checkNullorEmpty(Object bean) {
    if (bean == null) {
      return true;
    }

    if (bean instanceof Collection) {
      return CollectionUtils.isEmpty((Collection) bean);
    }

    if (bean.getClass().isArray()) {
      return ArrayUtils.isEmpty((Object[]) bean);
    }
    return false;
  }

  static EntityBean checkEntityBean(Object bean) {
    Validate.notNull(bean, "The bean is not null");
    if (bean instanceof EntityBean) {
      return (EntityBean) bean;
    }
    return null;
  }


  static EntityBean checkEntityBeanByCollection(Object o) {

    if (o == null) {
      throw new IllegalArgumentException("The object is not null!");
    }

    if (o instanceof EntityBean) {
      return (EntityBean) o;
    }

    if (o instanceof Map) {
      return null;
    }

    if (o instanceof Collection) {
      Collection collection = (Collection) o;
      if (CollectionUtils.isEmpty(collection)) {
        throw new IllegalArgumentException("The object is not empty!");
      }
      Object next = collection.iterator().next();
      if (next instanceof EntityBean) {
        return (EntityBean) next;
      }
    }

    return null;

  }

}
