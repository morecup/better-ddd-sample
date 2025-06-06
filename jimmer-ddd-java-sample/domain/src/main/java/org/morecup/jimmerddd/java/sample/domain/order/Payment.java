package org.morecup.jimmerddd.java.sample.domain.order;

import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.OneToOne;
import org.morecup.jimmerddd.java.sample.domain.base.BaseEntity;
import java.math.BigDecimal;

@Entity
public interface Payment extends BaseEntity {
    BigDecimal amount();
    String paymentMethod();

    @OneToOne
    Order order();
}