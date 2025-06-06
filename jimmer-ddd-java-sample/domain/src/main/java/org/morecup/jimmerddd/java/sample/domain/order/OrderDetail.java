package org.morecup.jimmerddd.java.sample.domain.order;

import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.OneToOne;
import org.jetbrains.annotations.Nullable;
import org.morecup.jimmerddd.java.sample.domain.base.BaseEntity;

@Entity
public interface OrderDetail extends BaseEntity {
    
    String address();

    @OneToOne(mappedBy = "orderDetail")
    @Nullable
    Order order();
}