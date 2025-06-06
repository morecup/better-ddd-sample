package org.morecup.jimmerddd.java.sample.domain.order;

import org.morecup.jimmerddd.java.sample.domain.base.DomainEvent;

public class RenameEvent extends DomainEvent {
    private final String newName;
    private final long goodsId;

    public RenameEvent(Object source, String newName, long goodsId) {
        super(source);
        this.newName = newName;
        this.goodsId = goodsId;
    }

    public String getNewName() {
        return newName;
    }

    public long getGoodsId() {
        return goodsId;
    }
}
