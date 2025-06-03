package org.morecup.jimmerddd.kotlin.sample.domain.goods

import org.babyfish.jimmer.sql.Entity
import org.morecup.jimmerddd.core.event.publishEvent
import org.morecup.jimmerddd.kotlin.aggregateproxy.KAggregateProxy
import org.morecup.jimmerddd.kotlin.sample.domain.base.BaseEntity

@Entity
interface Goods : BaseEntity {
    val name: String
    val nowAddress: String
}
val goodsAggregateProxy = KAggregateProxy(GoodsDraft::class)
class GoodsImpl(val goods: GoodsDraft) : GoodsDraft by goods {
    fun rename(newName: String) {
        goods.publishEvent(1)
        this.name = newName
    }
}