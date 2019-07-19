/**
 * @Title: ApplicationContextUtil
 * @Package com.shein.pss.utils
 * @Description: 系统工具类
 * @author: zhangpeng
 * @date: 2018/1/18
 * @version: V1.0
 */
package com.wxt.comm.util;

import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ApplicationContextUtil
 * @Description: 系统工具类
 * @author: zhangpeng
 * @date: 2018/1/18
 *
 * 1.0
 */
public class ApplicationContextUtil {

    /**初始化标志*/
    private static volatile boolean hasInit = false;

    /**应用上下文*/
    private static ApplicationContext ctx;

    /**
     * 初始化ApplicationContext
     * @param ctx
     */
    public static void initApplicationContext(ApplicationContext ctx) {
        if (!hasInit) {
            synchronized (ApplicationContextUtil.class) {
                if (!hasInit) {
                    ApplicationContextUtil.ctx = ctx;
                    hasInit = true;
                }
            }
        }
    }

    /**
     * 判断bean是否存在
     * @param beanName
     * @return
     */
    public static boolean containsBean(String beanName) {
        return hasInit ? ctx.containsBean(beanName) : null;
    }

    /**
     * 根据名称获取bean
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return hasInit ? ctx.getBean(beanName) : null;
    }

    /**
     * 根据类型获取bean
     * @param requiredType
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        return hasInit ? ctx.getBean(requiredType) : null;
    }

    /**
     * 根据名称和类型获取bean
     * @param beanName
     * @param requiredType
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return hasInit ? ctx.getBean(beanName, requiredType) : null;
    }

    /**
     * 根据名称和参数获取bean
     * @param beanName
     * @param args
     * @return
     */
    public static Object getBean(String beanName, Object... args) {
        return hasInit ? ctx.getBean(beanName, args) : null;
    }

	/**
	 * 获取所有Service
	 * @return
	 */
	public static List<Object> getAllService() {
		if (ctx == null) {
			return null;
		}
		String[] serviceArr = ctx.getBeanDefinitionNames();
		if (serviceArr != null) {

			List<Object> list = new ArrayList<>();
			for (String serviceName : serviceArr) {
				if (serviceName.endsWith("Service")) {
					Object service = ctx.getBean(serviceName);
					list.add(service);
				}
			}
			return list;
		}
		else {
			return null;
		}
	}
}
