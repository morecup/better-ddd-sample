package org.morecup.jimmerddd.kotlin.sample.domain.user

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.OneToMany
import org.babyfish.jimmer.sql.OneToOne
import org.morecup.jimmerddd.core.annotation.AggregatedField
import org.morecup.jimmerddd.core.annotation.AggregationType
import org.morecup.jimmerddd.core.event.lazyPublishEvent
import org.morecup.jimmerddd.kotlin.aggregateproxy.KAggregateProxy
import org.morecup.jimmerddd.kotlin.sample.domain.base.BaseEntity
import org.morecup.jimmerddd.kotlin.sample.domain.base.DomainEvent
import org.morecup.jimmerddd.kotlin.sample.domain.order.Order

@Entity
interface User: BaseEntity {

    val name: String
    val email: String?

    // 一对多：用户视角的反向关联
    @OneToMany(mappedBy = "user")
    @AggregatedField(AggregationType.NON_AGGREGATED)
    val orders: List<Order>

    @OneToOne
    val userDetail:UserDetail
}

val userAggregateProxy = KAggregateProxy(UserDraft::class)

/**
 * 也可以不委托EventHandler，通过Draft对象发送事件也是等效的
 */
class UserImpl(val user: UserDraft) : UserDraft by user {
    fun rename(newName: String) {
        user.lazyPublishEvent(UserRenameEvent(this, id, newName))
        // 注意！如果没有委托EventHandler，直接publishEvent是无效的
//        lazyPublishEvent(UserRenameEvent(this, id, newName))
//        这里会抛出异常
        this.name = newName
    }
}

class UserRenameEvent(
    source: Any,
    id: Long,
    val newName: String
) : DomainEvent(source)