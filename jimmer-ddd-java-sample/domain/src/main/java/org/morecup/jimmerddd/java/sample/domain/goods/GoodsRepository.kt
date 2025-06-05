package org.morecup.jimmerddd.kotlin.sample.domain.goods

import kotlin.reflect.KFunction

interface GoodsRepository {
    /**
     * 持久化Goods
     * @param goods 要保存的实体（按引用传递）
     */
    fun saveGoods(goods: Goods): Goods

    /**
     * 查找并校验存在性
     * @param id 实体标识符
     */
    fun findByIdOrErr(id: Long,function: KFunction<*>? = null): Goods
}