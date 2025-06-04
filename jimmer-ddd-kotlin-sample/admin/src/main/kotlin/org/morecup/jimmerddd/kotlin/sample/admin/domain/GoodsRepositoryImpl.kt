package org.morecup.jimmerddd.kotlin.sample.admin.domain

import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.ast.mutation.AssociatedSaveMode
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.morecup.jimmerddd.kotlin.sample.domain.base.DomainException
import org.morecup.jimmerddd.kotlin.sample.domain.goods.Goods
import org.morecup.jimmerddd.kotlin.sample.domain.goods.GoodsRepository
import org.morecup.jimmerddd.kotlin.spring.preanalysis.findById
import org.morecup.jimmerddd.kotlin.spring.preanalysis.saveAggregate
import org.springframework.stereotype.Repository
import kotlin.reflect.KFunction

@Repository
open class GoodsRepositoryImpl(
    sql: KSqlClient
) : AbstractKotlinRepository<Goods, Long>(sql), GoodsRepository {

    override fun saveGoods(goods: Goods): Goods {
//        注意，如果需要保存聚合根，建议使用saveAggregate方法，而不是jimmer自带的save方法
        return saveAggregate(goods).modifiedEntity
    }

    override fun findByIdOrErr(id: Long,function: KFunction<*>?): Goods {
//        能够根据提供的函数，自动分析需要查找哪些字段，如果function为null，则查找所有聚合字段（如何判断聚合字段可查看AggregatedField注解说明）
        return findById(id,function) ?: throw DomainException("找不到该Goods，id: $id")
    }

}