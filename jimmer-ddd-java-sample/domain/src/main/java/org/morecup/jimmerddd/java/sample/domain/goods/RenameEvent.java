package org.morecup.jimmerddd.java.sample.domain.goods;

import org.morecup.jimmerddd.java.sample.domain.base.DomainEvent;

public class RenameEvent extends DomainEvent {
    private final String newName;
    private final Long goodsId;

    public RenameEvent(Object source, Long goodsId, String newName) {
        super(source);
        this.newName = newName;
        this.goodsId = goodsId;
    }

    public String getNewName() {
        return newName;
    }

    public Long getGoodsId() {
        return goodsId;
    }
}
