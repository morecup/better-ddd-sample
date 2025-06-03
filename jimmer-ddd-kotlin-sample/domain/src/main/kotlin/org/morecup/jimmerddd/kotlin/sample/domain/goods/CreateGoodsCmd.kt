package org.morecup.jimmerddd.kotlin.sample.domain.goods

import org.morecup.jimmerddd.core.event.EventManager
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

    fun handle1(command: CreateGoodsCmd): Long {
        val (modifiedGoods,lazyEventList) = goodsFactory.createAndSave2(command)
        EventManager.publish(lazyEventList)
        return modifiedGoods.id
    }

    fun handle2(command: CreateGoodsCmd): Long {
        val goods = goodsFactory.create(command)
        val modifiedGoods = goodsRepository.saveGoods(goods)
        return modifiedGoods.id
    }

    fun handle3(command: CreateGoodsCmd): Long {
        val (goods,lazyEventList) = goodsFactory.create2(command)
        val modifiedGoods = goodsRepository.saveGoods(goods)
        EventManager.publish(lazyEventList)
        return modifiedGoods.id
    }
}