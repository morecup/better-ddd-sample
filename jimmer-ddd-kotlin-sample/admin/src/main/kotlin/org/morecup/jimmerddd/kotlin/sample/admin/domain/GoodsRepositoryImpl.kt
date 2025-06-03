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
        return saveAggregate(goods).modifiedEntity
    }

    override fun findByIdOrErr(id: Long,function: KFunction<*>?): Goods {
        return findById(id,function) ?: throw DomainException("找不到该Goods，id: $id")
    }

}