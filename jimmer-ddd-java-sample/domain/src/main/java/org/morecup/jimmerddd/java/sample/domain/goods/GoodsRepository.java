package org.morecup.jimmerddd.java.sample.domain.goods;

import org.morecup.jimmerddd.core.preanalysis.MethodInfo;

public interface GoodsRepository {
    /**
     * 持久化Goods
     * @param goods 要保存的实体（按引用传递）
     */
    Goods saveGoods(Goods goods);

    /**
     * 查找并校验存在性
     * @param id 实体标识符
     * @param function 可选函数参数
     */
    Goods findByIdOrErr(long id, MethodInfo function);
}