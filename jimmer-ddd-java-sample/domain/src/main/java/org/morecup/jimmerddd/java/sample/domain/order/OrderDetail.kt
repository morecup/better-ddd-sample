package org.morecup.jimmerddd.kotlin.sample.domain.order

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.OneToOne
import org.morecup.jimmerddd.kotlin.sample.domain.base.BaseEntity


@Entity
interface OrderDetail: BaseEntity {

    val address: String

    @OneToOne(mappedBy = "orderDetail")
    val order: Order?
}