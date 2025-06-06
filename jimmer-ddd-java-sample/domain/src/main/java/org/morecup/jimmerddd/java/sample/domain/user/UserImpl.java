package org.morecup.jimmerddd.java.sample.domain.user;

import org.morecup.jimmerddd.core.event.EventHandler;

public interface UserImpl extends UserDraft, EventHandler {

    default void rename(String newName) {
        lazyPublishEvent(new UserRenameEvent(this, id(), newName));
        // 注意！如果没有委托EventHandler，直接publishEvent是无效的
        // lazyPublishEvent(new UserRenameEvent(this, getId(), newName));
        // 这里会抛出异常
        this.setName(newName);
    }
}