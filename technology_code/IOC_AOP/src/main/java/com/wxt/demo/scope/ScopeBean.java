package com.wxt.demo.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//原型
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)//单列
public class ScopeBean {
}
