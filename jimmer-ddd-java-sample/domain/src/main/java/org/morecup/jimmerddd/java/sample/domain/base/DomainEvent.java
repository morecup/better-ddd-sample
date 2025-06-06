package org.morecup.jimmerddd.java.sample.domain.base;

import org.springframework.context.ApplicationEvent;

public class DomainEvent extends ApplicationEvent {
    public DomainEvent(Object source) {
        super(source);
    }
}