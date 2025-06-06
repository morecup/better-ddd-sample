package org.morecup.jimmerddd.java.sample.domain.goods;

import org.morecup.jimmerddd.java.sample.domain.base.DomainEvent;

public class AddressChangedEvent extends DomainEvent {
    private final String newAddress;

    public AddressChangedEvent(Object source, Long id, String newAddress) {
        super(source);
        this.newAddress = newAddress;
    }

    public String getNewAddress() {
        return newAddress;
    }
}
