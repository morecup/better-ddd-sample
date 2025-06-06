package org.morecup.jimmerddd.java.sample.admin.base;

import org.morecup.jimmerddd.java.sample.domain.base.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class DomainRegistryInit {
    private static final Logger LOG = LoggerFactory.getLogger(DomainRegistryInit.class);
    private final ApplicationContext applicationContext;

    public DomainRegistryInit(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        SpringContextUtils.setSpring(applicationContext);
        LOG.info("Domain registry initialized!");
    }
}