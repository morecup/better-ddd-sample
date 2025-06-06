package org.morecup.jimmerddd.java.sample.domain.user;

import org.morecup.jimmerddd.java.sample.domain.base.DomainEvent;

public class UserRenameEvent extends DomainEvent {
    private final String newName;

    public UserRenameEvent(Object source, Long id, String newName) {
        super(source);
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }
}
