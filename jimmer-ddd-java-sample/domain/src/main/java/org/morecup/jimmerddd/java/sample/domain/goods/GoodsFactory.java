package org.morecup.jimmerddd.java.sample.domain.goods;

import org.morecup.jimmerddd.java.factory.FactoryEventHandler;
import org.morecup.jimmerddd.java.factory.WithFactoryContext;
import org.morecup.jimmerddd.java.sample.domain.Immutables;
import org.morecup.jimmerddd.java.sample.domain.goods.dto.CreateGoodsCmd;
import org.springframework.stereotype.Service;

import static org.morecup.jimmerddd.java.sample.domain.base.SpringContextUtils.snowflakeManager;


@Service
public class GoodsFactory implements FactoryEventHandler {
    private final GoodsRepository goodsRepository;

    public GoodsFactory(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    /**
     * 新建商品并自动保存并发布事件
     * @param cmd 新建商品命令
     * @return 保存后的modifiedEntity
     */
    @WithFactoryContext
    public Goods createAndSave(CreateGoodsCmd cmd) {
        publishEvent(new GoodsCreatedEvent(this));
        doSomething();
        Goods goods = Immutables.createGoods(draft -> {
            // 可以提前设置id，但需要设置idPreLoaded为true，否则会被认为是update
            draft.setId(snowflakeManager().nextId());
            draft.setIdPreLoaded(true);
            draft.setName(cmd.getName());
            draft.setNowAddress(cmd.getNowAddress());
        });
        return goods;

    }


    /**
     * 新建商品
     * @param cmd 新建商品命令
     * @return 保存后的modifiedEntity
     */
    @WithFactoryContext(autoSave = false, autoPublishLazyEventAfterSave = false)
    public Goods create(CreateGoodsCmd cmd) {
        doSomething();
        Goods goods = Immutables.createGoods(draft -> {
            draft.setName(cmd.getName());
            draft.setNowAddress(cmd.getNowAddress());
        });
        return goods;

    }

    public void doSomething() {
        // 这里发送的事件会和调用方公用一个上下文
        publishEvent(new GoodsCreatedOtherEvent(this));
        System.out.println("doSomeThing");
    }
}

