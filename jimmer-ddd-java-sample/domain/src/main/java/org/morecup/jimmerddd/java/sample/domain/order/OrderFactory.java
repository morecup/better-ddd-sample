package org.morecup.jimmerddd.java.sample.domain.order;

import org.morecup.jimmerddd.java.sample.domain.order.dto.CreateOrderCmd;
import org.springframework.stereotype.Service;

@Service
public class OrderFactory{
    private final OrderRepository orderRepository;

    public OrderFactory(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(CreateOrderCmd cmd) {
        return cmd.toEntity();
    }
}