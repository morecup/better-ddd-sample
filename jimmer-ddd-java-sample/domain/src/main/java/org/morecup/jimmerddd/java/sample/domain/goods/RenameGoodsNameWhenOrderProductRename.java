package org.morecup.jimmerddd.java.sample.domain.goods;

import org.morecup.jimmerddd.java.function.MI;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RenameGoodsNameWhenOrderProductRename {
    private final GoodsRepository goodsRepository;

    public RenameGoodsNameWhenOrderProductRename(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @EventListener
    public void onEvent(RenameEvent event) {
        Goods goods = goodsRepository.findByIdOrErr(event.getGoodsId(), MI.of(GoodsImpl::rename));
        GoodsImpl.proxy.execAndSave(goods, impl -> {
            impl.rename(event.getNewName());
            return null;
        });
    }
}