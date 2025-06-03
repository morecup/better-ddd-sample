package org.morecup.jimmerddd.kotlin.sample.domain.goods

import org.babyfish.jimmer.sql.Entity
import org.morecup.jimmerddd.core.event.EventHandler
import org.morecup.jimmerddd.core.event.publishEvent
import org.morecup.jimmerddd.kotlin.aggregateproxy.KAggregateProxy
import org.morecup.jimmerddd.kotlin.sample.domain.base.BaseEntity
import org.morecup.jimmerddd.kotlin.sample.domain.base.DomainEvent

@Entity
interface Goods : BaseEntity {
    val name: String
    val nowAddress: String
}
val goodsAggregateProxy = KAggregateProxy(GoodsDraft::class)

/**
 * 委派EventHandler，这样才能发送延迟事件和空draftContext
 */
class GoodsImpl(goods: GoodsDraft) : GoodsDraft by goods, EventHandler by goods as EventHandler {
    fun rename(newName: String) {
        lazyPublishEvent(GoodsRenameEvent(this, id, newName))
        this.name = newName
    }
}

class GoodsRenameEvent(
    source: Any,
    id: Long,
    val newName: String
) : DomainEvent(source)