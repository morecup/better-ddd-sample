package org.morecup.jimmerddd.java.sample.domain.user;

import org.babyfish.jimmer.sql.Entity;
import org.morecup.jimmerddd.java.sample.domain.base.BaseEntity;

@Entity
public interface UserDetail extends BaseEntity {
    String msg();
}