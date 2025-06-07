package org.morecup.jimmerddd.java.sample.domain.order;


import org.morecup.jimmerddd.java.function.MI;
import org.springframework.stereotype.Component;

@Component
public class ChangeAllReasonOrderCmdHandle {
    private final OrderRepository orderRepository;


    public ChangeAllReasonOrderCmdHandle(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Boolean handle(long id) {
        Order order = orderRepository.findByIdOrErr(id, MI.of(OrderImpl::changeAllReason));
        Boolean result = OrderImpl.proxy.execAndSave(order, (impl) -> impl.changeAllReason("changeAllReason"));
        return result;
    }
}
