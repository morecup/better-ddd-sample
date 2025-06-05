package org.morecup.jimmerddd.kotlin.sample.domain.order

import org.morecup.jimmerddd.kotlin.sample.domain.order.dto.CreateOrderCmd
import org.springframework.stereotype.Component

@Component
class CreateOrderCmdHandle(
    private val orderRepository: OrderRepository,
    private val orderFactory: OrderFactory,
) {
    fun handle(command: CreateOrderCmd): Long {
        val order = orderFactory.create(command)
        val updatedOrder = orderRepository.saveOrder(order)
        return updatedOrder.id
    }
}