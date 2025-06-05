package org.morecup.jimmerddd.kotlin.sample.admin.domain

import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.ast.mutation.AssociatedSaveMode
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.morecup.jimmerddd.kotlin.sample.domain.base.DomainException
import org.morecup.jimmerddd.kotlin.sample.domain.order.Order
import org.morecup.jimmerddd.kotlin.sample.domain.order.OrderRepository
import org.morecup.jimmerddd.kotlin.spring.preanalysis.findById
import org.morecup.jimmerddd.kotlin.spring.preanalysis.saveAggregate
import org.springframework.stereotype.Component
import kotlin.reflect.KFunction

@Component
class OrderRepositoryImpl(
    sql: KSqlClient
) : AbstractKotlinRepository<Order, Long>(sql), OrderRepository {

    override fun saveOrder(order: Order): Order {
        return saveAggregate(order).modifiedEntity
    }

    override fun findByIdOrErr(id: Long,function: KFunction<*>?): Order {
        return findById(id,function) ?: throw DomainException("找不到，id: $id")
    }

}