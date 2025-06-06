package org.morecup.jimmerddd.java.sample.domain.base;

import org.babyfish.jimmer.sql.meta.UserIdGenerator;

import java.lang.Class;

import static org.morecup.jimmerddd.java.sample.domain.base.SpringContextUtils.snowflakeManager;

public class SnowflakeIdGenerator implements UserIdGenerator<Long> {
    @Override
    public Long generate(Class<?> entityType) {
        return snowflakeManager().nextId();
    }
}