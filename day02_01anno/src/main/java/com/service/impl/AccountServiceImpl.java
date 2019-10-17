package com.service.impl;

import com.dao.IAccountDao;
import com.dao.impl.AccountImpl;
import com.service.IAccountService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * xml中的配置
 *     <bean id="accountService" class="com.service.impl.AccountServiceImpl"></bean>
 * 用于创建对象的注解
 *      作用等同于编写bean标签<bean></bean>
 * @Component
 *      把对象存入Spring容器
 *      属性：value 用于指定bean的id，不写时，默认为当前类名且首字母改成小写
 * @Controller：表现层
 * @Service：业务层
 * @Repository：持久层
 * 以上三个注解的作用和属性与Component是一模一样的，它们三个是Spring框架为我们提供的三层明确注解，使三层对象更加清晰
 * 用于注入数据的
 *      作用等同于在bean标签中指定<property></property>
 * @Autoried
 *      自动按照类型注入，只要容器中有唯一的bean对象的类型和要注入的类型匹配，就可以诸如成功
 *      如果IOC容器中没有任何bean的类型和变量匹配，那么就会报错
 *      如果IOC容器中有多个类型匹配时，
 *      出现位置可以在变量上与方法上
 * @Qualifier: 与@Autoried配合使用
 *      在类型注入的基础上再按照名称注入，它在给成员注入是不能单独使用，但是给方法参数注入时可以
 *      属性：value 用于指定注入bean的ID
 * @Resource:
 *      直接按照bean的id注入，可以单独使用不依靠于@Autoried
 *      属性：name 用于指定id
 * 以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法通过上诉三个注解实现
 * 另外，集合类型的注入只能通过xml来实现
 * @Value：
 *      用于注入基本类型和Srting类型的数据
 *      属性：value 用于指定数据的值，可以使用Spring中的SpEL（Spring中的EL表达式）
 *           SpEL的写法：${表达式}
 *      使用注解注入时，set方法就不是必须实现的了
 * 用于改变作用范围的
 *      作用等用于指定scope属性
 * @Scope：
 *      用于指定bean的作用范围
 *      属性：value 指定范围的取值
 * 和生命周期相关的（了解）
 * @PreDestroy:
 *      用于指定销毁方法
 * @PostConstruct:
 *      用于指定初始化方法
 *      作用等同于使用init-method和destroy-method
 */

@Service(value = "accountService")
/*@Scope(value = "prototype")*/
public class AccountServiceImpl implements IAccountService {
    @Resource(name = "accountImpl")
    private IAccountDao accountDao;
    @PostConstruct
    public void init(){
        System.out.println("AccountImpl的Init方法");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("AccountImpl的destroy方法");
    }
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
