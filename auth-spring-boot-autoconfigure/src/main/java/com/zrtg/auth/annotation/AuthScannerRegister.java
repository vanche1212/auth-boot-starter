//package com.zrtg.auth.annotation;
//
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.context.ResourceLoaderAware;
//import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
//import org.springframework.core.annotation.AnnotationAttributes;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.core.type.AnnotationMetadata;
//import org.springframework.util.ClassUtils;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AuthScannerRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
//
//    private ResourceLoader resourceLoader;
//
//    public static List<String> basePackages = new ArrayList<>();
//
//
//    @Override
//    public void setResourceLoader(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
//
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
//                                        BeanDefinitionRegistry registry) {
//
//        AnnotationAttributes annoAttrs
//                = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(AuthScan.class.getName()));
//
//
//        for (String pkg : annoAttrs.getStringArray("value")) {
//            if (StringUtils.hasText(pkg)) {
//                basePackages.add(pkg);
//            }
//        }
//
//        for (String pkg : annoAttrs.getStringArray("basePackages")) {
//            if (StringUtils.hasText(pkg)) {
//                basePackages.add(pkg);
//            }
//        }
//
//        for (Class<?> clazz : annoAttrs.getClassArray("basePackageClasses")) {
//            basePackages.add(ClassUtils.getPackageName(clazz));
//        }
//
//    }
//}
