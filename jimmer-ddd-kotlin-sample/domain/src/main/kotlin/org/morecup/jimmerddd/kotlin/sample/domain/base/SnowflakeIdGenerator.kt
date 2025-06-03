package org.morecup.jimmerddd.kotlin.sample.domain.base

import org.babyfish.jimmer.sql.meta.UserIdGenerator
import org.morecup.jimmerddd.kotlin.sample.domain.base.DomainRegistry.snowflakeManager

class SnowflakeIdGenerator : UserIdGenerator<Long> {
    override fun generate(entityType: Class<*>): Long? {
        return snowflakeManager().nextId()
    }
}