package org.morecup.jimmerddd.kotlin.sample.domain.goods

import org.morecup.jimmerddd.core.factory.autoContext
import org.morecup.jimmerddd.kotlin.sample.domain.goods.dto.CreateGoodsCmd
import org.springframework.stereotype.Service

@Service
class GoodsFactory(
    private val goodsRepository: GoodsRepository
) {
    fun createAndSave(cmd: CreateGoodsCmd): Goods = autoContext {
        Goods {
            name = cmd.name
            nowAddress = cmd.nowAddress
        }
    }
}