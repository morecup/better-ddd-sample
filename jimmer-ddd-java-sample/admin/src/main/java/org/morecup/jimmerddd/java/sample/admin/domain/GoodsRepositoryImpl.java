package org.morecup.jimmerddd.java.sample.admin.domain;

import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.morecup.jimmerddd.core.preanalysis.MethodInfo;
import org.morecup.jimmerddd.java.preanalysis.FunctionFetcher;
import org.morecup.jimmerddd.java.sample.domain.base.DomainException;
import org.morecup.jimmerddd.java.sample.domain.goods.Goods;
import org.morecup.jimmerddd.java.sample.domain.goods.GoodsFetcher;
import org.morecup.jimmerddd.java.sample.domain.goods.GoodsRepository;
import org.morecup.jimmerddd.java.sample.domain.goods.GoodsTable;
import org.morecup.jimmerddd.java.spring.preanalysis.JAggregateSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsRepositoryImpl implements GoodsRepository {
    private final JAggregateSqlClient aggregateSqlClient;

    public GoodsRepositoryImpl(JAggregateSqlClient aggregateSqlClient) {
        this.aggregateSqlClient = aggregateSqlClient;
    }

    @Override
    public Goods saveGoods(Goods goods) {
        return aggregateSqlClient.saveAggregate(goods).getModifiedEntity();
    }

    @Override
    public Goods findByIdOrErr(long id, MethodInfo methodInfo) {
        Goods result = aggregateSqlClient.findById(Goods.class, methodInfo,id);
        if (result == null) {
            throw new DomainException("找不到该Goods，id: " + id);
        }
        return result;
    }

    public Goods findNameByIdOrErr(Long id, MethodInfo methodInfo) {
        Fetcher<Goods> goodsFetcher = FunctionFetcher.of(Goods.class, methodInfo);
        Goods result = aggregateSqlClient.sqlClient.findById(
                GoodsFetcher.$from(goodsFetcher).name(),
                id
        );
        if (result == null) {
            throw new DomainException("找不到该Goods，id: " + id);
        }
        return result;
    }

    public Goods findByNameOrErr(String name, MethodInfo methodInfo) {
        Fetcher<Goods> goodsFetcher = FunctionFetcher.of(Goods.class, methodInfo);
        Goods result = aggregateSqlClient.sqlClient
                .createQuery(GoodsTable.$)
                .where(GoodsTable.$.name().eq(name))
                .select(GoodsTable.$.fetch(goodsFetcher))
                .fetchFirstOrNull();
        if (result == null) {
            throw new DomainException("找不到该Goods，name: " + name);
        }
        return result;
    }
}
