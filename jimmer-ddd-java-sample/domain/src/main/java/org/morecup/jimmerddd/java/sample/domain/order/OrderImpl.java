package org.morecup.jimmerddd.java.sample.domain.order;

import org.morecup.jimmerddd.core.aggregateproxy.AggregateProxy;
import org.morecup.jimmerddd.core.event.EventHandler;
import org.morecup.jimmerddd.java.sample.domain.goods.dto.CreateGoodsCmd;

import java.util.List;

import static org.morecup.jimmerddd.java.sample.domain.base.SpringContextUtils.goodsFactory;

/**
 * 这里主要演示聚合根关联的实体的任意修改也能追踪，并且能够避免各种DraftContext问题，达到真正的充血模型
 */
public interface OrderImpl extends OrderDraft,EventHandler {
    AggregateProxy<OrderImpl> proxy = new AggregateProxy<>(OrderImpl.class);

    default boolean removeAftermarket(String reason) {
        List<Aftermarket> list = aftermarketList();
        list.removeIf(t -> t.reason().equals(reason));
        return true;
    }

    default boolean changeAddress(String address) {
        orderDetail().setAddress(address);
        return true;
    }

    default boolean renameOrderName(String newName) {
        setName(newName);
        return true;
    }

    default boolean changeAllReason(String newReason) {
        System.out.println(aftermarketList(false).size());
//        aftermarketList(false).forEach(t -> t.setReason(newReason));
        addIntoAftermarketList(draft -> draft.setReason(newReason));
//        aftermarketList(false).getFirst().setReason(newReason);
        return true;
    }

    default boolean addAftermarket(String reason) {
        aftermarketList().add(AftermarketDraft.$.produce(draft -> draft.setReason(reason)));
        return true;
    }

    default boolean sendGoods() {
        /**
         * 这里演示了调用别的聚合的Factory也不会存在draftContext的问题
         */
        goodsFactory().createAndSave(new CreateGoodsCmd(){{
            setName("test");
            setNowAddress("某个地址");
        }});
        return true;
    }

    default void renameUserDetailMsg() {
        System.out.println(user().userDetail().createTime());
        giftList(false).forEach(t -> {
            System.out.println(t.createTime());
            if (!t.giftName().isEmpty()) {
                t.setGiftName("新的名称");
            }
        });
        user().userDetail().setMsg("test");
    }

    default void seeIdView() {
        System.out.println(goodsId());
    }

    default void setIdView() {
        setGoodsId(1924732369399582720L);
    }

    default void seeIdOnly() {
        System.out.println(goods());
    }

    default void setIdOnly() {
        goods().setId(1924732201233158144L);
    }
}