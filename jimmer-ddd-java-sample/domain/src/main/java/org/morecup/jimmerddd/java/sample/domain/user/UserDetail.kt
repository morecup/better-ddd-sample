package org.morecup.jimmerddd.kotlin.sample.domain.user

import org.babyfish.jimmer.sql.Entity
import org.morecup.jimmerddd.kotlin.sample.domain.base.BaseEntity

@Entity
interface UserDetail: BaseEntity {
    val msg: String
}