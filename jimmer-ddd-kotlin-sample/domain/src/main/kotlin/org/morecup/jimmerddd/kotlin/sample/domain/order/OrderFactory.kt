package org.morecup.jimmerddd.kotlin.sample.domain.order

import org.morecup.jimmerddd.core.domain.order.dto.CreateOrderCmd
import org.morecup.jimmerddd.core.factory.autoContext
import org.springframework.stereotype.Service

@Service
class OrderFactory(
    private val orderRepository: OrderRepository
) {
    fun create(cmd: CreateOrderCmd): Order = autoContext{
        cmd.toEntity()
    }
}
