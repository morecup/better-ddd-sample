package org.morecup.jimmerddd.java.sample.domain.order;

import org.morecup.jimmerddd.java.sample.domain.order.dto.CreateOrderCmd;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderCmdHandle {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    public CreateOrderCmdHandle(OrderRepository orderRepository, OrderFactory orderFactory) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
    }

    public Long handle(CreateOrderCmd command) {
        Order order = orderFactory.create(command);
        Order updatedOrder = orderRepository.saveOrder(order);
        return updatedOrder.id();
    }
}