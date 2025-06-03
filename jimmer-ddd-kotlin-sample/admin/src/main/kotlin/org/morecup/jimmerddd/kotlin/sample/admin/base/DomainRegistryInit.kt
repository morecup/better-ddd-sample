package org.morecup.jimmerddd.kotlin.sample.admin.base

import org.morecup.jimmerddd.kotlin.sample.domain.base.spring
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DomainRegistryInit(
    private val applicationContext: ApplicationContext
) {
    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(DomainRegistryInit::class.java)
    }

    @PostConstruct
    fun init() {
        spring = applicationContext
        LOG.info("Domain registry initialized!")
    }
}