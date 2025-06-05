package org.morecup.jimmerddd.kotlin.sample.domain.order

import kotlin.reflect.KFunction

interface OrderRepository {
    /**
     * 持久化Order
     * @param order 要保存的实体（按引用传递）
     */
    fun saveOrder(order: Order): Order

    /**
     * 查找并校验存在性
     * @param id 实体标识符
     */
    fun findByIdOrErr(id: Long,function: KFunction<*>?=null): Order
}