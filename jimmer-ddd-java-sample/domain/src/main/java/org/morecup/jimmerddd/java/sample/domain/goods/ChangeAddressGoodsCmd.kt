package org.morecup.jimmerddd.kotlin.sample.domain.goods

import org.morecup.jimmerddd.core.event.EventManager
import org.morecup.jimmerddd.kotlin.sample.domain.goods.dto.ChangeAddressGoodsCmd
import org.springframework.stereotype.Service


@Service
class ChangeAddressGoodsCmdHandle(
    private val goodsRepository: GoodsRepository,
) {
    fun handle(command: ChangeAddressGoodsCmd): Boolean {
//        也可以直接调用findByIdOrErr，不传递changeAddress方法，这样会查出所有的聚合根属性
//        val goods = goodsRepository.findByIdOrErr(command.id)
        val goods = goodsRepository.findByIdOrErr(command.id, GoodsImpl::changeAddress)
//        执行lambda并返回lambda执行结果（会自动保存和在保存后自动发布lazyEvent）
        val result = goodsAggregateProxy.execAndSave(goods) {
            GoodsImpl(it).changeAddress(command.nowAddress)
            true
        }
        return result
    }

    fun handle2(command: ChangeAddressGoodsCmd): Boolean {
        val goods = goodsRepository.findByIdOrErr(command.id, GoodsImpl::changeAddress)
//        执行lambda并返回需要保存的实体和lazyPublishEvent的事件列表
        val result = goodsAggregateProxy.exec(goods) {
            GoodsImpl(it).changeAddress(command.nowAddress)
            true
        }
        goodsRepository.saveGoods(result.changed)
        EventManager.publish(result.lazyPublishEventList)
        return result.result
    }

    fun handle3(command: ChangeAddressGoodsCmd): Boolean {
        val goods = goodsRepository.findByIdOrErr(command.id, GoodsImpl::changeAddress)
//        执行lambda并返回lambda执行结果（会自动保存和在保存后自动发布lazyEvent）
        val result = goodsAggregateProxy.execAndSaveRM(goods) {
            GoodsImpl(it).changeAddress(command.nowAddress)
            true
        }
//        会返回保存后的modifiedEntity，可以做一些特殊操作，比如说拿到updateTime等
        println(result.modifiedEntity.updateTime)
        return result.result
    }
}