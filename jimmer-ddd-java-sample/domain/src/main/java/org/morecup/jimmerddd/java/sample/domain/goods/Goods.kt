package org.morecup.jimmerddd.kotlin.sample.domain.goods

import org.babyfish.jimmer.sql.Entity
import org.morecup.jimmerddd.core.event.EventHandler
import org.morecup.jimmerddd.kotlin.aggregateproxy.KAggregateProxy
import org.morecup.jimmerddd.kotlin.sample.domain.base.BaseEntity
import org.morecup.jimmerddd.kotlin.sample.domain.base.DomainEvent

@Entity
interface Goods : BaseEntity {
    val name: String
    val nowAddress: String
}

/**
 * goodsAggregateProxy的作用是代理Goods实体，通过代理对象可以随意的调用Goods的方法，和修改Goods的属性，使得jimmer能够支持充血模型
 */
val goodsAggregateProxy = KAggregateProxy(GoodsDraft::class)

/**
 * 委派EventHandler，这样才能发送延迟事件和空draftContext，另一种方式参考User.kt
 */
class GoodsImpl(goods: GoodsDraft) : GoodsDraft by goods, EventHandler by goods as EventHandler {
    fun rename(newName: String) {
//        事件并不会立刻发布
        lazyPublishEvent(GoodsRenameEvent(this, id, newName))
        this.name = newName
    }

    fun changeAddress(nowAddress: String) {
//        事件并不会立刻发布
        lazyPublishEvent(AddressChangedEvent(this, id, nowAddress))
        this.nowAddress = nowAddress
    }

}

class GoodsRenameEvent(
    source: Any,
    id: Long,
    val newName: String
) : DomainEvent(source)

class AddressChangedEvent(
    source: Any,
    id: Long,
    val newAddress: String
) : DomainEvent(source)