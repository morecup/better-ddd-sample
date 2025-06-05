package org.morecup.jimmerddd.kotlin.sample.domain.userall

import org.morecup.jimmerddd.core.aggregateproxy.multi.MultiEntity
import org.morecup.jimmerddd.kotlin.aggregateproxy.KAggregateProxy
import org.morecup.jimmerddd.kotlin.sample.domain.user.User
import org.morecup.jimmerddd.kotlin.sample.domain.user.UserDraft

interface UserAll : MultiEntity,User, UserExtend{

}

interface UserAllDraft : UserDraft, UserExtendDraft{

}

val userAllAggregateProxy = KAggregateProxy(UserAllDraft::class)

class UserAllImpl(val userAll: UserAllDraft) : UserAllDraft by userAll  {

    fun test() {
        println(name)
        println(msg)
        msg = "hello"
        email = null
    }
}