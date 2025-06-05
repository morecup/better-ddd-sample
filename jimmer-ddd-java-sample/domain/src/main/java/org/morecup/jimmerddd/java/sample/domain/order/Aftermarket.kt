package org.morecup.jimmerddd.kotlin.sample.domain.order

import org.babyfish.jimmer.sql.DissociateAction
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.ManyToOne
import org.babyfish.jimmer.sql.OnDissociate
import org.morecup.jimmerddd.kotlin.sample.domain.base.BaseEntity

@Entity
interface Aftermarket: BaseEntity {
    val reason: String

    @ManyToOne
    @OnDissociate(DissociateAction.DELETE)
    val order: Order
}