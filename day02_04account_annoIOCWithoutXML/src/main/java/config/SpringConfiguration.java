package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 该类是一个配置类，作用与bean.xml相同
 * spring中的新注解
 * @Configuration：
 *      指定当前类是一个配置类
 *      细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，可以不写
 *
 * @ComponetScan:
 *      用于通过注解指定spring在创建容器时要扫描的包
 *      属性：
 *      value和basePackages相同，使用后相当于在xml中配置了扫描器
 * @ComponetScans:
 *      在内可以同时指定一组@ComponentScan数组
 * @Bean:
 *      用于把当前方法的返回值作为bean对象存入Spring的ioc容器中
 *      属性：
 *      name：用于指定bean的id，默认是当前方法道的名称
 *      细节：
 *          当使用注解配置方法时，如果方法有参数，spring会在容器中查找是否存在可用的bean对象
 *          查找的方式和Autoried方式一样
 * @Import：
 *      用于导入其他配置类
 *      属性：
 *      value：用于指定其他配置类的字节码，使用import注解之后，有import注解的类就是主配置（父配置类），而导入的都是子配置类
 * @PropertySource
 *      用于指定properties文件的位置
 *      属性：
 *      value：指定文件的名称和文件的路径
 *          关键字：classpath 表示在类路径下
 */
/*@Configuration*/
@ComponentScans({
        @ComponentScan(basePackages = {"com"}),
       /* @ComponentScan(basePackages = {"config"})*/
})
@Import(jdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {

}
