package org.morecup.jimmerddd.kotlin.sample.admin.base.domaininterfaceimpl

import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.Configuration

/**
 * 自动注册当前目录下的所有object到spring容器
 */
@Configuration
class ObjectAutoRegisterSpringConfiguration {
    @Bean
    fun objectScanner(): BeanFactoryPostProcessor {
        return BeanFactoryPostProcessor { beanFactory ->
            val basePackage = this::class.java.`package`.name
            val scanner = ClassPathScanningCandidateComponentProvider(false)
            scanner.addIncludeFilter { _, _ -> true } // 包含所有类

            scanner.findCandidateComponents(basePackage).forEach { beanDefinition ->
                val className = beanDefinition.beanClassName
                val clazz = Class.forName(className).kotlin
                if (clazz.objectInstance != null) {
                    // 注册对象实例为单例 Bean
                    beanFactory.registerSingleton(
                        clazz.java.simpleName.decapitalize(),
                        clazz.objectInstance!!
                    )
                }
            }
        }
    }
}