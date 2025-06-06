package org.morecup.jimmerddd.java.sample.domain.goods;

import org.morecup.jimmerddd.java.sample.domain.base.DomainEvent;

public class GoodsCreatedOtherEvent extends DomainEvent {
    public GoodsCreatedOtherEvent(Object source) {
        super(source);
    }
}
