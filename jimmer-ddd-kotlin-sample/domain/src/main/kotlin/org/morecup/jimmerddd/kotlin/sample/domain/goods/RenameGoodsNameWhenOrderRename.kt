package org.morecup.jimmerddd.kotlin.sample.domain.goods

import org.morecup.jimmerddd.kotlin.sample.domain.order.RenameEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class RenameGoodsNameWhenOrderRename(
    val goodsRepository: GoodsRepository
) {
    @EventListener
    fun onEvent(event: RenameEvent) {
        val goods: Goods = goodsRepository.findByIdOrErr(event.goodsId)
        goodsAggregateProxy.exec(goods) { draft ->
            GoodsImpl(draft).rename(event.newName)
        }
        goodsRepository.saveGoods(goods)
    }
}