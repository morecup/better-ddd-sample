package org.morecup.jimmerddd.kotlin.sample.domain.order

import org.babyfish.jimmer.Formula
import org.babyfish.jimmer.sql.*
import org.morecup.jimmerddd.core.annotation.AggregatedField
import org.morecup.jimmerddd.core.annotation.AggregationType
import org.morecup.jimmerddd.core.event.EventHandler
import org.morecup.jimmerddd.kotlin.aggregateproxy.KAggregateProxy
import org.morecup.jimmerddd.kotlin.sample.domain.base.BaseEntity
import org.morecup.jimmerddd.kotlin.sample.domain.base.DomainEvent
import org.morecup.jimmerddd.kotlin.sample.domain.base.goodsFactory
import org.morecup.jimmerddd.kotlin.sample.domain.goods.Goods
import org.morecup.jimmerddd.kotlin.sample.domain.goods.dto.CreateGoodsCmd
import org.morecup.jimmerddd.kotlin.sample.domain.user.User

@Entity
@Table(name = "`order`")
interface Order : BaseEntity {

    val name: String

    @ManyToOne
    //声明只有id是聚合根的属性，其他属性都不参与聚合根，这里的关联只是为了查询的方便
    @AggregatedField(AggregationType.ID_ONLY)
    val user: User

    @ManyToMany
    val productList: List<Product>

    @ManyToMany(mappedBy = "orderList")
    val giftList:List<Gift>

//    @IdView("giftList")
//    val giftListIds:List<Long>

    // 一对一：订单与支付单（mappedBy 指向 Payment.order）
    @OneToOne(mappedBy = "order")
    val payment: Payment?

    @OneToMany(mappedBy = "order")
    val aftermarketList: List<Aftermarket>

//    Transient属性自动不是聚合根的一部分
    @Transient
    val aftermarketNOSave: Boolean

//    Formula属性自动不是聚合根的一部分
    @Formula(dependencies = ["aftermarketList"])
    val aftermarketCount: Int
        get() = aftermarketList.size

    @OneToOne
    val orderDetail: OrderDetail

    @OneToOne
    //声明只有id是聚合根的属性，其他属性都不参与聚合根，这里的关联只是为了查询的方便
    @AggregatedField(AggregationType.ID_ONLY)
    val goods: Goods?

    @IdView
    val goodsId: Long?
}

val orderAggregateProxy = KAggregateProxy(OrderDraft::class)

/**
 * 这里主要演示聚合根关联的实体的任意修改也能追踪，并且能够避免各种DraftContext问题，达到真正的充血模型
 */
class OrderImpl(order: OrderDraft) : OrderDraft by order, EventHandler by order as EventHandler {

    fun removeAftermarket(reason: String):Boolean {
        val list = aftermarketList()
        list.removeAll { t -> t.reason == reason }
        return true
    }

    fun changeAddress(address: String):Boolean {
        orderDetail().address = address
        return true
    }

    fun renameOrderName(newName: String):Boolean {
        name = newName
        return true
    }

    fun changeAllReason(newReason: String):Boolean {
        aftermarketList().forEach { t ->
            t.reason = newReason
        }
        return true
    }

    fun addAftermarket(reason: String):Boolean {
        aftermarketList().addBy {
            this.reason = reason
        }
        return true
    }

    fun sendGoods():Boolean {
        /**
         * 这里演示了调用别的聚合的Factory也不会存在draftContext的问题
         */
        goodsFactory().createAndSave(CreateGoodsCmd("test", "某个地址"))
        return true
    }

    fun renameUserDetailMsg(){
        println(user().userDetail().createTime)
        println(giftList.forEach { t ->
            println(t.createTime)
        })
        giftList().filter{it.giftName.isNotEmpty()}.forEach{
            it.giftName = "新的名称"
        }
        user().userDetail().msg = "test"
    }

    fun seeIdView(){
        println(goodsId)
    }

    fun setIdView(){
        goodsId = 1924732369399582720L
    }

    fun seeIdOnly(){
        println(goods)
    }

    fun setIdOnly(){
        goods().id = 1924732201233158144L
    }
}
class RenameEvent(
    source: Any,
    val newName: String,
    val goodsId:Long
) : DomainEvent(source)