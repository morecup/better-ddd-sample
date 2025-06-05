package org.morecup.jimmerddd.kotlin.sample.admin.domain

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.morecup.jimmerddd.core.aggregateproxy.multi.MultiEntityFactory
import org.morecup.jimmerddd.kotlin.sample.domain.base.DomainException
import org.morecup.jimmerddd.kotlin.sample.domain.user.User
import org.morecup.jimmerddd.kotlin.sample.domain.userall.UserAll
import org.morecup.jimmerddd.kotlin.sample.domain.userall.UserAllRepository
import org.morecup.jimmerddd.kotlin.sample.domain.userall.UserExtend
import org.morecup.jimmerddd.kotlin.spring.preanalysis.findById
import org.morecup.jimmerddd.kotlin.spring.preanalysis.saveMultiEntityAggregate
import org.springframework.stereotype.Repository
import kotlin.reflect.KFunction

@Repository
class UserAllRepositoryImpl(
    private val sqlClient: KSqlClient
) :  UserAllRepository {

    override fun saveUserAll(userAll: UserAll): UserAll {
        return sqlClient.saveMultiEntityAggregate(userAll)
    }

    override fun findByIdOrErr(id: Long,function: KFunction<*>?): UserAll {
        val user = sqlClient.findById(User::class, id, function) ?: throw DomainException("user not found")
        val userExt = sqlClient.findById(UserExtend::class, id, function) ?: throw DomainException("userExt not found")
        return MultiEntityFactory.create(UserAll::class, user,userExt)
    }

}