package org.morecup.jimmerddd.java.sample.domain.order;

import org.babyfish.jimmer.sql.DissociateAction;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.ManyToOne;
import org.babyfish.jimmer.sql.OnDissociate;
import org.morecup.jimmerddd.java.sample.domain.base.BaseEntity;

@Entity
public interface Aftermarket extends BaseEntity {
    String reason();

    @ManyToOne
    @OnDissociate(DissociateAction.DELETE)
    Order order();
}