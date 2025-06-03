package org.morecup.jimmerddd.kotlin.sample.admin.domain

import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.ast.mutation.AssociatedSaveMode
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.morecup.jimmerddd.kotlin.sample.domain.order.Order
import org.morecup.jimmerddd.kotlin.sample.domain.order.OrderRepository
import org.springframework.stereotype.Component

@Component
class OrderRepositoryImpl(
    sql: KSqlClient
) : AbstractKotlinRepository<Order, Long>(sql), OrderRepository {

    override fun saveOrder(order: Order): Order {
        return save(order, SaveMode.NON_IDEMPOTENT_UPSERT, AssociatedSaveMode.REPLACE).modifiedEntity
    }

    override fun findByIdOrErr(id: Long): Order {
        return findById(id) ?: throw RuntimeException("找不到，id: $id")
    }

}