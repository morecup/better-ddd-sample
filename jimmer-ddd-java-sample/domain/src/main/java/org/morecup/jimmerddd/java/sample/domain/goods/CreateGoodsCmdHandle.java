package org.morecup.jimmerddd.java.sample.domain.goods;

import org.morecup.jimmerddd.core.event.EventManager;
import org.morecup.jimmerddd.java.sample.domain.goods.dto.CreateGoodsCmd;
import org.springframework.stereotype.Component;

@Component
public class CreateGoodsCmdHandle {
    private final GoodsRepository goodsRepository;
    private final GoodsFactory goodsFactory;

    public CreateGoodsCmdHandle(GoodsRepository goodsRepository, GoodsFactory goodsFactory) {
        this.goodsRepository = goodsRepository;
        this.goodsFactory = goodsFactory;
    }

    public long handle(CreateGoodsCmd command) {
        Goods modifiedGoods = goodsFactory.createAndSave(command);
        return modifiedGoods.id();
    }

    public long handle2(CreateGoodsCmd command) {
        Goods goods = goodsFactory.create(command);
        Goods modifiedGoods = goodsRepository.saveGoods(goods);
        return modifiedGoods.id();
    }
}