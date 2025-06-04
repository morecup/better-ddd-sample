package org.morecup.jimmerddd.kotlin.sample.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.morecup.jimmerddd.kotlin.sample.admin.base.R
import org.morecup.jimmerddd.kotlin.sample.domain.order.CreateOrderCmdHandle
import org.morecup.jimmerddd.kotlin.sample.domain.order.Order
import org.morecup.jimmerddd.kotlin.sample.domain.order.dto.CreateOrderCmd
import org.morecup.jimmerddd.kotlin.sample.domain.order.dto.ListOrderRequest
import org.morecup.jimmerddd.kotlin.sample.domain.order.dto.ListOrderResponse
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "订单管理")
@RequestMapping("/order")
class OrderController(
    private val createOrderCmdHandle: CreateOrderCmdHandle,
    private val sqlClient: KSqlClient
) {

    @PostMapping("/create")
    @Transactional
    @Operation(summary = "创建")
    fun createOrder(@RequestBody @Validated createOrderRequest: CreateOrderCmd): R<Long> {
        return R.success(createOrderCmdHandle.handle(createOrderRequest))
    }

    /**
     * 可以直接使用jimmer强大的查询功能，不需要写复杂的sql
     */
    @PostMapping("/listOrder")
    @Operation(summary = "查询订单相关信息")
    fun listOrder(@RequestBody @Validated listOrderRequest:ListOrderRequest): R<List<ListOrderResponse>> {
        val listOrderResponse = sqlClient.createQuery(Order::class){
            where(listOrderRequest)
            select(table.fetch(ListOrderResponse::class))
        }.execute()
        return R.success(listOrderResponse)
    }
}