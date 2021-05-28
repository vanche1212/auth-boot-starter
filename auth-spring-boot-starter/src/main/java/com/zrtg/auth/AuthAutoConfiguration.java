package com.zrtg.auth;

import com.zrtg.auth.core.AuthHandler;
import com.zrtg.auth.core.AuthScanner;
import com.zrtg.auth.core.SqlHandler;
import com.zrtg.auth.properties.AuthConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({AuthHandler.class})
@EnableConfigurationProperties({AuthConfigurationProperties.class})
@ConditionalOnProperty(prefix = "auth", value = "enabled", matchIfMissing = false)
public class AuthAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(AuthAutoConfiguration.class);

    // 这边无法属性注入因为注入的bean实现了BeanDefinitionRegistryPostProcessor
//    @Bean
//    @ConditionalOnMissingBean
//    public AuthBeanRegister authBeanRegister(AuthConfigurationProperties authConfigurationProperties) {
//        logger.info("注入了我们自己的类");
//        return new AuthBeanRegister(authConfigurationProperties);
//    }

    @Bean
    @ConditionalOnMissingBean
    public AuthHandler authHandler(AuthConfigurationProperties authConfigurationProperties) {
        return new AuthHandler(authConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlHandler sqlHandler(AuthConfigurationProperties authConfigurationProperties) {
        return new SqlHandler(authConfigurationProperties);
    }


    @Bean
    @ConditionalOnMissingBean
    public AuthScanner authScanner() {
        return new AuthScanner();
    }


}
