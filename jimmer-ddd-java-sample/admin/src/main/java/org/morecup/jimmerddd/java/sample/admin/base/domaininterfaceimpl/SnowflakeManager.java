package org.morecup.jimmerddd.java.sample.admin.base.domaininterfaceimpl;

import cn.hutool.core.lang.Snowflake;
import org.morecup.jimmerddd.java.sample.domain.base.domaininterface.ISnowflakeManager;
import org.springframework.stereotype.Component;

@Component
public class SnowflakeManager implements ISnowflakeManager {
    private final Snowflake snowflake = new Snowflake(1, 1);

    @Override
    public long nextId() {
        return snowflake.nextId();
    }
}
