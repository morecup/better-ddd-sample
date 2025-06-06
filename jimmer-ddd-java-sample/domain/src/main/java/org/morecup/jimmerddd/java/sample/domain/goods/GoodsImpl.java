package org.morecup.jimmerddd.java.sample.domain.goods;

import org.morecup.jimmerddd.core.aggregateproxy.AggregateProxy;
import org.morecup.jimmerddd.core.event.EventHandler;

public interface GoodsImpl extends GoodsDraft, EventHandler {
    AggregateProxy<GoodsImpl> proxy = new AggregateProxy<>(GoodsImpl.class);

    default void rename(String newName) {
        // 事件并不会立刻发布
        lazyPublishEvent(new RenameEvent(this, id(), newName));
        // 这里需要根据实际情况实现属性修改
        setName(newName);
    }

    default void changeAddress(String nowAddress) {
        // 事件并不会立刻发布
        lazyPublishEvent(new AddressChangedEvent(this, id(), nowAddress));
        // 这里需要根据实际情况实现属性修改
        setNowAddress(nowAddress);
    }
}
