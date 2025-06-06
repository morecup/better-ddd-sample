package org.morecup.jimmerddd.java.sample.domain.order;

import kotlin.reflect.KFunction;
import org.morecup.jimmerddd.core.preanalysis.MethodInfo;

public interface OrderRepository {
    /**
     * 持久化Order
     * @param order 要保存的实体（按引用传递）
     */
    Order saveOrder(Order order);

    /**
     * 查找并校验存在性
     * @param id 实体标识符
     */
    Order findByIdOrErr(long id, MethodInfo function);
}