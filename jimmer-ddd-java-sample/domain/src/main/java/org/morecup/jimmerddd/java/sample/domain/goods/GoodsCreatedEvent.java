package org.morecup.jimmerddd.java.sample.domain.goods;

import org.morecup.jimmerddd.java.sample.domain.base.DomainEvent;

public class GoodsCreatedEvent extends DomainEvent {
    public GoodsCreatedEvent(Object source) {
        super(source);
    }
}
