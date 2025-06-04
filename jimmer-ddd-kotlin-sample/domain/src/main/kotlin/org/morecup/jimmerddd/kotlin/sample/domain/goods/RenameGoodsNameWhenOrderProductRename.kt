package org.morecup.jimmerddd.kotlin.sample.domain.goods

import org.morecup.jimmerddd.kotlin.sample.domain.order.RenameEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class RenameGoodsNameWhenOrderProductRename(
    val goodsRepository: GoodsRepository
) {
    @EventListener
    fun onEvent(event: RenameEvent) {
        val goods: Goods = goodsRepository.findByIdOrErr(event.goodsId,GoodsImpl::rename)
        goodsAggregateProxy.execAndSave(goods) { draft ->
            GoodsImpl(draft).rename(event.newName)
        }
    }
}