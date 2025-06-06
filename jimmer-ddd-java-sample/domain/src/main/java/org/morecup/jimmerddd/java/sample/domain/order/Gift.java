package org.morecup.jimmerddd.java.sample.domain.order;

import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.ManyToMany;
import org.morecup.jimmerddd.java.sample.domain.base.BaseEntity;
import java.util.List;

@Entity
public interface Gift extends BaseEntity {
    
    String giftName();

    @ManyToMany
    List<Order> orderList();
}