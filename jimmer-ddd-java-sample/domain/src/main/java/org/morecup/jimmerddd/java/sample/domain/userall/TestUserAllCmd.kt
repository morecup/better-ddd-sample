package org.morecup.jimmerddd.kotlin.sample.domain.userall

import org.morecup.jimmerddd.core.event.EventManager
import org.springframework.stereotype.Service

class TestUserAllCmd(
    val id: Long
)

@Service
class TestUserAllCmdHandle(
    private val userAllRepository: UserAllRepository,
) {
    fun handle(command: TestUserAllCmd): Boolean {
        val userAll = userAllRepository.findByIdOrErr(command.id,UserAllImpl::test)
        val result = userAllAggregateProxy.execMultiAndSave(userAll) {
            UserAllImpl(it).test()
            true
        }
        return result
    }

    fun handle2(command: TestUserAllCmd): Boolean {
        val userAll = userAllRepository.findByIdOrErr(command.id,UserAllImpl::test)
        val multiProxyResult = userAllAggregateProxy.execMulti(userAll) {
            UserAllImpl(it).test()
            true
        }
        EventManager.publish(multiProxyResult.lazyPublishEventList)
        userAllRepository.saveUserAll(multiProxyResult.changed)
        return multiProxyResult.result
    }
}