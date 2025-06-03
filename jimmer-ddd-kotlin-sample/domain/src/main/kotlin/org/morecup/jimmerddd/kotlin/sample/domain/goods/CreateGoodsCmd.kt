package org.morecup.jimmerddd.kotlin.sample.domain.goods

import org.morecup.jimmerddd.kotlin.sample.domain.goods.dto.CreateGoodsCmd
import org.springframework.stereotype.Service

@Service
class CreateGoodsCmdHandle(
    private val goodsRepository: GoodsRepository,
    private val goodsFactory: GoodsFactory,
) {
    fun handle(command: CreateGoodsCmd): Long {
        val modifiedGoods = goodsFactory.createAndSave(command)
        return modifiedGoods.id
    }
}