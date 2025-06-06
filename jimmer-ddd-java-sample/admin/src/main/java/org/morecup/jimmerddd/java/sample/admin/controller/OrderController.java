package org.morecup.jimmerddd.java.sample.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.babyfish.jimmer.sql.JSqlClient;
import org.morecup.jimmerddd.java.sample.admin.base.R;
import org.morecup.jimmerddd.java.sample.domain.Tables;
import org.morecup.jimmerddd.java.sample.domain.order.CreateOrderCmdHandle;
import org.morecup.jimmerddd.java.sample.domain.order.Order;
import org.morecup.jimmerddd.java.sample.domain.order.OrderTable;
import org.morecup.jimmerddd.java.sample.domain.order.dto.CreateOrderCmd;
import org.morecup.jimmerddd.java.sample.domain.order.dto.ListOrderRequest;
import org.morecup.jimmerddd.java.sample.domain.order.dto.ListOrderResponse;
import org.morecup.jimmerddd.java.spring.preanalysis.JAggregateSqlClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Tag(name = "订单管理")
@RequestMapping("/order")
public class OrderController {
    
    private final CreateOrderCmdHandle createOrderCmdHandle;
    private final JSqlClient sqlClient;

    public OrderController(CreateOrderCmdHandle createOrderCmdHandle, JSqlClient sqlClient) {
        this.createOrderCmdHandle = createOrderCmdHandle;
        this.sqlClient = sqlClient;
    }

    @PostMapping("/create")
    @Transactional
    @Operation(summary = "创建")
    public R<Long> createOrder(@RequestBody @Validated CreateOrderCmd createOrderRequest) {
        return R.success(createOrderCmdHandle.handle(createOrderRequest));
    }

    /**
     * 可以直接使用jimmer强大的查询功能，不需要写复杂的sql
     */
    @PostMapping("/listOrder")
    @Operation(summary = "查询订单相关信息")
    public R<List<ListOrderResponse>> listOrder(@RequestBody @Validated ListOrderRequest listOrderRequest) {
        OrderTable orderTable = Tables.ORDER_TABLE;
        List<ListOrderResponse> listOrderResponse = sqlClient.createQuery(orderTable)
                .where(listOrderRequest)
                .select(orderTable.fetch(ListOrderResponse.class))
                .execute();
        return R.success(listOrderResponse);
    }
}