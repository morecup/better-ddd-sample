package org.morecup.jimmerddd.java.sample.domain.order;

import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.ManyToMany;
import org.morecup.jimmerddd.java.sample.domain.base.BaseEntity;
import java.math.BigDecimal;
import java.util.List;

@Entity
public interface Product extends BaseEntity {
    String name();
    BigDecimal originalPrice();

    @ManyToMany(mappedBy = "productList")
    List<Order> orderList();
}