package org.morecup.jimmerddd.kotlin.sample.domain.goods

import org.morecup.jimmerddd.core.event.EventHandler
import org.morecup.jimmerddd.core.factory.FactoryModifiedResult
import org.morecup.jimmerddd.core.factory.FactoryResult
import org.morecup.jimmerddd.core.factory.autoContext
import org.morecup.jimmerddd.core.factory.eventAutoContext
import org.morecup.jimmerddd.core.factory.eventContext
import org.morecup.jimmerddd.core.factory.sameContext
import org.morecup.jimmerddd.core.factory.saveAutoContext
import org.morecup.jimmerddd.kotlin.sample.domain.base.DomainEvent
import org.morecup.jimmerddd.kotlin.sample.domain.goods.dto.CreateGoodsCmd
import org.springframework.stereotype.Service

@Service
class GoodsFactory(
    private val goodsRepository: GoodsRepository
) {
    /**
     * 新建商品并自动保存
     * @param cmd 新建商品命令
     * @return 保存后的modifiedEntity
     */
    fun createAndSave(cmd: CreateGoodsCmd): Goods = autoContext {
        lazyPublishEvent(GoodsCreatedEvent(this))
        doSomething(this)
        Goods {
            name = cmd.name
            nowAddress = cmd.nowAddress
        }
    }

    /**
     * 新建商品并自动保存
     * @param cmd 新建商品命令
     * @return FactoryModifiedResult 含保存后的modifiedEntity
     */
    fun createAndSave2(cmd: CreateGoodsCmd): FactoryModifiedResult<Goods> = saveAutoContext {
        lazyPublishEvent(GoodsCreatedEvent(this))
        Goods {
            name = cmd.name
            nowAddress = cmd.nowAddress
        }
    }

    /**
     * 新建商品
     * @param cmd 新建商品命令
     * @return modifiedEntity
     */
    fun create(cmd: CreateGoodsCmd): Goods = eventAutoContext {
        lazyPublishEvent(GoodsCreatedEvent(this))
        Goods {
            name = cmd.name
            nowAddress = cmd.nowAddress
        }
    }

    /**
     * 新建商品
     * @param cmd 新建商品命令
     * @return FactoryResult 包含实体和延迟事件，需要手动处理
     */
    fun create2(cmd: CreateGoodsCmd): FactoryResult<Goods> = eventContext {
        lazyPublishEvent(GoodsCreatedEvent(this))
        Goods {
            name = cmd.name
            nowAddress = cmd.nowAddress
        }
    }


    fun doSomething(eventHandler: EventHandler) = sameContext(eventHandler){
        //这里发送的事件会和调用方公用一个上下文
        lazyPublishEvent(GoodsCreatedOtherEvent(this))
        println("doSomeThing")
    }
}



class GoodsCreatedEvent(
    source: Any,
) : DomainEvent(source)

class GoodsCreatedOtherEvent(
    source: Any,
) : DomainEvent(source)