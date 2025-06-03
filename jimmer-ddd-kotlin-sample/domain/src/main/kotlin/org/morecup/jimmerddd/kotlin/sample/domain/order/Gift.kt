package org.morecup.jimmerddd.kotlin.sample.domain.order

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.ManyToMany
import org.morecup.jimmerddd.kotlin.sample.domain.base.BaseEntity

@Entity
interface Gift: BaseEntity {

    val giftName: String


    @ManyToMany
    val orderList:List<Order>
}