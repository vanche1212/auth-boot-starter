//package com.zrtg.auth;
//
//import com.zrtg.auth.annotation.Auth;
////import com.zrtg.auth.annotation.AuthScannerRegister;
//import com.zrtg.auth.properties.AuthConfigurationProperties;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.ResourceLoaderAware;
//import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.core.type.filter.AnnotationTypeFilter;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.util.StringUtils;
//
//import javax.sql.DataSource;
//import java.lang.reflect.Method;
//
//
//public class AuthBeanRegister implements BeanPostProcessor {
//
//    private Logger logger = LoggerFactory.getLogger(AuthBeanRegister.class);
//
//    private AuthConfigurationProperties authConfigurationProperties;
//
//
//    public AuthBeanRegister(AuthConfigurationProperties authConfigurationProperties) {
//        this.authConfigurationProperties = authConfigurationProperties;
//    }
//
//
////    @Override
////    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//////        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
//////        for (String beanDefinitionName : beanDefinitionNames) {
//////            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
//////            System.out.println(beanDefinition.getAttribute("key"));
//////        }
////        // 定义一个扫描器,其实这边不需要扫描，因为我的注解是加在别人的注解上的，而不是全新的注解单个使用的情况
//////        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
//////        scanner.addIncludeFilter(new AnnotationTypeFilter(Auth.class));
//////        scanner.scan(StringUtils.toStringArray(AuthScannerRegister.basePackages));
////    }
//
//
//
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
////        Auth auth = bean.getClass().getAnnotation(Auth.class);
////        if (auth != null) {
////            // 处理我们加了注解的bean
//////            logger.info("找到自定义注解的类");
////            // 获取所有方法
////            Method[] methods = bean.getClass().getMethods();
////            for (Method method : methods) {
////                Auth methodAuth = method.getAnnotation(Auth.class);
////                if (methodAuth != null) {
////                    // 计算
////                }
////            }
////        }
//        return bean;
//    }
//}
//
