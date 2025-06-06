package org.morecup.jimmerddd.java.sample.domain.goods;

import org.babyfish.jimmer.sql.Entity;
import org.morecup.jimmerddd.core.aggregateproxy.AggregateProxy;
import org.morecup.jimmerddd.core.event.EventHandler;
import org.morecup.jimmerddd.java.sample.domain.base.BaseEntity;
import org.morecup.jimmerddd.java.sample.domain.base.DomainEvent;


@Entity
public interface Goods extends BaseEntity {
    String name();
    String nowAddress();
}

