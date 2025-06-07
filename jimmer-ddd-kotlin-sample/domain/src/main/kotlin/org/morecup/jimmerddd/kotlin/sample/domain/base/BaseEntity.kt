package org.morecup.jimmerddd.kotlin.sample.domain.base

import com.fasterxml.jackson.annotation.JsonFormat
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.MappedSuperclass
import org.babyfish.jimmer.sql.Transient
import org.morecup.jimmerddd.core.sqlclient.AggregationEntity
import java.time.LocalDateTime

/*
 * see CommonEntityDraftInterceptor
 */
@MappedSuperclass
interface BaseEntity: AggregationEntity {
//    也可以不继承AggregationEntity，但是需要声明idPreLoaded属性

    @Id
    @GeneratedValue(generatorType = SnowflakeIdGenerator::class)
    val id: Long

    /**
     * 创建时间
     * df(2025-01-20 10:00:00)
     */
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createTime: LocalDateTime

    /**
     * 修改时间
     * df(2025-01-20 10:00:00)
     */
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updateTime: LocalDateTime

    /**
     * 创建者id
     */
    val createBy: Long?

    /**
     * 修改者id
     */
    val updateBy: Long?

//    /**
//     * id是否已经提前填充过（用于提前生成了的id，但是依然是insert的场景，主要为了BaseEntityDraftInterceptor）
//     */
//    @Transient
//    val idPreLoaded: Boolean

}