package org.morecup.jimmerddd.kotlin.sample.domain.base

import org.babyfish.jimmer.sql.meta.UserIdGenerator

class SnowflakeIdGenerator : UserIdGenerator<Long> {
    override fun generate(entityType: Class<*>): Long? {
        return snowflakeManager().nextId()
    }
}