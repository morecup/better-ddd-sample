package org.morecup.jimmerddd.kotlin.sample.domain.base

import org.morecup.jimmerddd.kotlin.sample.domain.base.domaininterface.ISnowflakeManager
import org.morecup.jimmerddd.kotlin.sample.domain.goods.GoodsFactory
import org.morecup.jimmerddd.kotlin.sample.domain.goods.GoodsRepository
import org.springframework.context.ApplicationContext


lateinit var spring: ApplicationContext

fun goodsRepository(): GoodsRepository = spring.getBean(GoodsRepository::class.java)
fun goodsFactory(): GoodsFactory = spring.getBean(GoodsFactory::class.java)
fun snowflakeManager(): ISnowflakeManager = spring.getBean(ISnowflakeManager::class.java)
