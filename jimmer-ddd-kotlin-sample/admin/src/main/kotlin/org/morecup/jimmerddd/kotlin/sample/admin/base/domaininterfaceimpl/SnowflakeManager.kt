package org.morecup.jimmerddd.kotlin.sample.admin.base.domaininterfaceimpl

import cn.hutool.core.lang.Snowflake
import org.morecup.jimmerddd.kotlin.sample.domain.base.domaininterface.ISnowflakeManager

object SnowflakeManager:ISnowflakeManager {
    private val snowflake = Snowflake(1, 1)
    override fun nextId(): Long {
        return snowflake.nextId()
    }
}