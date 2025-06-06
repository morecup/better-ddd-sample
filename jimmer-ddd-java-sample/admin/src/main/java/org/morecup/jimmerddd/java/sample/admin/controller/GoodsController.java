package org.morecup.jimmerddd.java.sample.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.morecup.jimmerddd.java.sample.admin.base.R;
import org.morecup.jimmerddd.java.sample.domain.goods.ChangeAddressGoodsCmdHandle;
import org.morecup.jimmerddd.java.sample.domain.goods.CreateGoodsCmdHandle;
import org.morecup.jimmerddd.java.sample.domain.goods.dto.ChangeAddressGoodsCmd;
import org.morecup.jimmerddd.java.sample.domain.goods.dto.CreateGoodsCmd;
import org.morecup.jimmerddd.java.spring.preanalysis.JAggregateSqlClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "货物管理")
@RequestMapping("/goods")
public class GoodsController {
    private final CreateGoodsCmdHandle createGoodsCmdHandle;
    private final ChangeAddressGoodsCmdHandle changeAddressGoodsCmdHandle;
    private final JAggregateSqlClient sqlClient;

    public GoodsController(CreateGoodsCmdHandle createGoodsCmdHandle, ChangeAddressGoodsCmdHandle changeAddressGoodsCmdHandle, JAggregateSqlClient sqlClient) {
        this.createGoodsCmdHandle = createGoodsCmdHandle;
        this.changeAddressGoodsCmdHandle = changeAddressGoodsCmdHandle;
        this.sqlClient = sqlClient;
    }

    @PostMapping("/create")
    @Transactional
    @Operation(summary = "创建")
    //    可以直接使用领域内的cmd对象，省去了dto对象的转换
    public R<Long> createGoods(@RequestBody @Validated CreateGoodsCmd createGoodsRequest) {
        return R.success(createGoodsCmdHandle.handle(createGoodsRequest));
    }

    @PostMapping("/changeAddress")
    @Transactional
    @Operation(summary = "修改地址")
    public R<Boolean> changeAddress(@RequestBody @Validated ChangeAddressGoodsCmd changeAddressRequest) {
        return R.success(changeAddressGoodsCmdHandle.handle(changeAddressRequest));
    }
}