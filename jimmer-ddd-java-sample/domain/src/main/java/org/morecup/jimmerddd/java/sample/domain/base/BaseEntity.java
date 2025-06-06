package org.morecup.jimmerddd.java.sample.domain.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.babyfish.jimmer.sql.GeneratedValue;
import org.babyfish.jimmer.sql.Id;
import org.babyfish.jimmer.sql.MappedSuperclass;
import org.babyfish.jimmer.sql.Transient;
import java.time.LocalDateTime;

/**
 * see CommonEntityDraftInterceptor
 */
@MappedSuperclass
public interface BaseEntity{
    
    @Id
    @GeneratedValue(generatorType = SnowflakeIdGenerator.class)
    long id();
    
    /**
     * 创建时间
     * df(2025-01-20 10:00:00)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createTime();
    
    /**
     * 修改时间
     * df(2025-01-20 10:00:00)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime updateTime();
    
    /**
     * 创建者id
     */
    Long createBy();
    
    /**
     * 修改者id
     */
    Long updateBy();

    /**
     * id是否已经提前填充过（用于提前生成了的id，但是依然是insert的场景，主要为了BaseEntityDraftInterceptor）
     */
    @Transient
    Boolean idPreLoaded();
}