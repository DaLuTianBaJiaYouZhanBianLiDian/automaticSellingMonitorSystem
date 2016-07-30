/**
 * 
 */
package com.zyuc.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

/**
 * Copyright @ 2012 sohu.com Inc.
 * All right reserved.
 * <p>
 * 手动加载Bean
 * </p>
 * @author fudawei
 * @since 2014年6月24日
 */
@Repository
public class SpringContextHelper implements ApplicationContextAware{
	private static ApplicationContext context;
	/**
	 * set spring上下文
	 * @param context 上下文对象
	 * @throws BeansException bean异常
	 */
	public void setApplicationContext(ApplicationContext context)throws BeansException {
		SpringContextHelper.context = context;
	}
	/**
	 * <pre>
	 * spring容器获得bean
	 * </pre>
	 * @param beanName bean名称
	 * @return 对象
	 * @author fudawei, 2013-12-30 下午5:42:30
	 */
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	/**
	 * <pre>
	 * 载入bean对象，beanName必须是spring托管的对象
	 * </pre>
	 * @param <T> 泛型类
	 * @param beanName bean名称
	 * @param clazz bean的class对象
	 * @return 注入后的对象
	 * @author fudawei, 2013-12-27 下午5:30:09
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return context.getBean(beanName, clazz);
	}
}
