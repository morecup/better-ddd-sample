package org.morecup.jimmerddd.java.sample.domain.order;

import org.babyfish.jimmer.Formula;
import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;
import org.morecup.jimmerddd.core.annotation.AggregatedField;
import org.morecup.jimmerddd.core.annotation.AggregationType;
import org.morecup.jimmerddd.java.sample.domain.base.BaseEntity;
import org.morecup.jimmerddd.java.sample.domain.goods.Goods;
import org.morecup.jimmerddd.java.sample.domain.user.User;

import java.util.List;

@Entity
@Table(name = "`order`")
public interface Order extends BaseEntity {

    String name();

    @ManyToOne
    @AggregatedField(type = AggregationType.ID_ONLY)
    User user();

    @ManyToMany
    List<Product> productList();

    @ManyToMany(mappedBy = "orderList")
    List<Gift> giftList();

    // 一对一：订单与支付单（mappedBy 指向 Payment.order）
    @OneToOne(mappedBy = "order")
    @Nullable
    Payment payment();

    @OneToMany(mappedBy = "order")
    List<Aftermarket> aftermarketList();

    // Transient属性自动不是聚合根的一部分
    @Transient
    boolean isAftermarketNOSave();

    // Formula属性自动不是聚合根的一部分
    @Formula(dependencies = {"aftermarketList"})
    default int getAftermarketCount() {
        return aftermarketList().size();
    }

    @OneToOne
    OrderDetail orderDetail();

    @OneToOne
    @AggregatedField(type = AggregationType.ID_ONLY)
    @Nullable
    Goods goods();

    @IdView("goods")
    @Nullable
    Long goodsId();
}



