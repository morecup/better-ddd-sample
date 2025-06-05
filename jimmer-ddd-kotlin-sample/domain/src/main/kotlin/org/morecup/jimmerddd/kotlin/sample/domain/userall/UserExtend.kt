package org.morecup.jimmerddd.kotlin.sample.domain.userall

import org.babyfish.jimmer.sql.Entity
import org.morecup.jimmerddd.kotlin.sample.domain.base.BaseEntity

@Entity
interface UserExtend: BaseEntity {
    val msg: String
}