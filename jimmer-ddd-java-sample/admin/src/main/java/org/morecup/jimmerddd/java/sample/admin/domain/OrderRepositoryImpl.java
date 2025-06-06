package org.morecup.jimmerddd.java.sample.admin.domain;

import org.morecup.jimmerddd.core.preanalysis.MethodInfo;
import org.morecup.jimmerddd.java.sample.domain.base.DomainException;
import org.morecup.jimmerddd.java.sample.domain.order.Order;
import org.morecup.jimmerddd.java.sample.domain.order.OrderRepository;
import org.morecup.jimmerddd.java.spring.preanalysis.JAggregateSqlClient;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final JAggregateSqlClient aggregateSqlClient;

    public OrderRepositoryImpl(JAggregateSqlClient aggregateSqlClient) {
        this.aggregateSqlClient = aggregateSqlClient;
    }

    @Override
    public Order saveOrder(Order order) {
        return aggregateSqlClient.saveAggregate(order).getModifiedEntity();
    }

    @Override
    public Order findByIdOrErr(long id, MethodInfo function) {
        Order result = aggregateSqlClient.findById(Order.class,function,id);
        if (result == null) {
            throw new DomainException("找不到，id: " + id);
        }
        return result;
    }
}
