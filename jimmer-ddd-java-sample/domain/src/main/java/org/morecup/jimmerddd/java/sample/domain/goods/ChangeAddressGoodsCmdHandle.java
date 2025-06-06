package org.morecup.jimmerddd.java.sample.domain.goods;

import org.morecup.jimmerddd.core.aggregateproxy.ProxyResult;
import org.morecup.jimmerddd.core.aggregateproxy.ProxyResultRM;
import org.morecup.jimmerddd.core.event.EventManager;
import org.morecup.jimmerddd.java.function.MI;
import org.morecup.jimmerddd.java.sample.domain.goods.dto.ChangeAddressGoodsCmd;
import org.springframework.stereotype.Service;

@Service
public class ChangeAddressGoodsCmdHandle {
    private final GoodsRepository goodsRepository;

    public ChangeAddressGoodsCmdHandle(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public boolean handle(ChangeAddressGoodsCmd command) {
        // 也可以直接调用findByIdOrErr，不传递changeAddress方法，这样会查出所有的聚合根属性
        // Goods goods = goodsRepository.findByIdOrErr(command.getId());
        Goods goods = goodsRepository.findByIdOrErr(command.getId(), MI.of(GoodsImpl::changeAddress));
        // 执行lambda并返回lambda执行结果（会自动保存和在保存后自动发布lazyEvent）
        Boolean result = GoodsImpl.proxy.execAndSave(goods, (impl) -> {
            impl.changeAddress(command.getNowAddress());
            return true;
        });
        return result;
    }

    public boolean handle2(ChangeAddressGoodsCmd command) {
        Goods goods = goodsRepository.findByIdOrErr(command.getId(), MI.of(GoodsImpl::changeAddress));
        // 执行lambda并返回需要保存的实体和lazyPublishEvent的事件列表
        ProxyResult<Goods, Boolean> result = GoodsImpl.proxy.exec(goods, (impl) -> {
            impl.changeAddress(command.getNowAddress());
            return true;
        });
        goodsRepository.saveGoods(result.getChanged());
        EventManager.publish(result.getLazyPublishEventList());
        return result.getResult();
    }

    public boolean handle3(ChangeAddressGoodsCmd command) {
        Goods goods = goodsRepository.findByIdOrErr(command.getId(), MI.of(GoodsImpl::changeAddress));
        // 执行lambda并返回lambda执行结果（会自动保存和在保存后自动发布lazyEvent）
        ProxyResultRM<Goods, Boolean> result = GoodsImpl.proxy.execAndSaveRM(goods, (impl) -> {
            impl.changeAddress(command.getNowAddress());
            return true;
        });
        // 会返回保存后的modifiedEntity，可以做一些特殊操作，比如说拿到updateTime等
        System.out.println(result.getModifiedEntity().updateTime());
        return result.getResult();
    }
}