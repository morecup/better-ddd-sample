package org.morecup.jimmerddd.kotlin.sample.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.morecup.jimmerddd.kotlin.sample.admin.base.R
import org.morecup.jimmerddd.kotlin.sample.domain.userall.TestUserAllCmd
import org.morecup.jimmerddd.kotlin.sample.domain.userall.TestUserAllCmdHandle
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "userAll")
@RequestMapping("/userAll")
class UserAllController(
    private val testUserAllCmdHandle:TestUserAllCmdHandle,
    private val sqlClient: KSqlClient
) {

    @PostMapping("/test")
    @Transactional
    @Operation(summary = "test")
    fun createUserAll(@RequestBody @Validated testRequest: TestUserAllCmd): R<Boolean> {
        testUserAllCmdHandle.handle2(testRequest)
        return R.success(true)
    }
}