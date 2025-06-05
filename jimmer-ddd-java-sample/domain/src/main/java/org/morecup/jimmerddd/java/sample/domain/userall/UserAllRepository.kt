package org.morecup.jimmerddd.kotlin.sample.domain.userall

import kotlin.reflect.KFunction

interface UserAllRepository {
    /**
     * 持久化UserAll
     * @param userAll 要保存的实体（按引用传递）
     */
    fun saveUserAll(userAll: UserAll): UserAll

    /**
     * 查找并校验存在性
     * @param id 实体标识符
     */
    fun findByIdOrErr(id: Long,function: KFunction<*>?=null): UserAll
}