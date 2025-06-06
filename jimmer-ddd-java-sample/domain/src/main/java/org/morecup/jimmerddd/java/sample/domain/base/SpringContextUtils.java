package org.morecup.jimmerddd.java.sample.domain.base;

import org.morecup.jimmerddd.java.sample.domain.base.domaininterface.ISnowflakeManager;
import org.morecup.jimmerddd.java.sample.domain.goods.GoodsFactory;
import org.morecup.jimmerddd.java.sample.domain.goods.GoodsRepository;
import org.springframework.context.ApplicationContext;

public class SpringContextUtils {
    private static ApplicationContext spring;

    public static void setSpring(ApplicationContext applicationContext) {
        spring = applicationContext;
    }

    public static GoodsRepository goodsRepository() {
        return spring.getBean(GoodsRepository.class);
    }

    public static GoodsFactory goodsFactory() {
        return spring.getBean(GoodsFactory.class);
    }

    public static ISnowflakeManager snowflakeManager() {
        return spring.getBean(ISnowflakeManager.class);
    }
}