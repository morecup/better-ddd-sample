package org.morecup.jimmerddd.java.sample.domain.user;

import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.OneToMany;
import org.babyfish.jimmer.sql.OneToOne;
import org.jetbrains.annotations.Nullable;
import org.morecup.jimmerddd.java.sample.domain.base.BaseEntity;
import org.morecup.jimmerddd.java.sample.domain.order.Order;

import java.util.List;

@Entity
public interface User extends BaseEntity {
    String name();
    @Nullable
    String email();

    // 一对多：用户视角的反向关联
    @OneToMany(mappedBy = "user")
//    @AggregatedField(AggregationType.NON_AGGREGATED)
    List<Order> orders();

    @OneToOne
    UserDetail userDetail();
}


