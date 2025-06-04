package org.morecup.jimmerddd.kotlin.sample.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.morecup.jimmerddd.kotlin.sample.admin.base.R
import org.morecup.jimmerddd.kotlin.sample.domain.goods.ChangeAddressGoodsCmdHandle
import org.morecup.jimmerddd.kotlin.sample.domain.goods.CreateGoodsCmdHandle
import org.morecup.jimmerddd.kotlin.sample.domain.goods.dto.ChangeAddressGoodsCmd
import org.morecup.jimmerddd.kotlin.sample.domain.goods.dto.CreateGoodsCmd
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "货物管理")
@RequestMapping("/goods")
class GoodsController(
    private val createGoodsCmdHandle: CreateGoodsCmdHandle,
    private val changeAddressGoodsCmdHandle: ChangeAddressGoodsCmdHandle,
    private val sqlClient: KSqlClient
) {

    @PostMapping("/create")
    @Transactional
    @Operation(summary = "创建")
//    可以直接使用领域内的cmd对象，省去了dto对象的转换
    fun createGoods(@RequestBody @Validated createGoodsRequest: CreateGoodsCmd): R<Long> {
        return R.success(createGoodsCmdHandle.handle(createGoodsRequest))
    }

    @PostMapping("/changeAddress")
    @Transactional
    @Operation(summary = "修改地址")
    fun changeAddress(@RequestBody @Validated changeAddressRequest: ChangeAddressGoodsCmd): R<Boolean> {
        return R.success(changeAddressGoodsCmdHandle.handle(changeAddressRequest))
    }
}