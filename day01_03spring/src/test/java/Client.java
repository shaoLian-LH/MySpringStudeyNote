import com.dao.IAccountDao;
import com.service.IAccountService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Client {
    /**
     * ApplicationContext的三个常用实现类：
     *      ClassPathXmlApplicationContext：可以加载类路径下的配置文件，要求配置文件必须在类路径下（常用）
     *      FileSystemXMLApplicationContext：可以加载磁盘任意路径下的配置文件（必须拥有访问权限）
     *      AnnotationConfigApplicationContext：用于读取注释创建容器
     * 核心容器的两个接口引发的问题：
     * ApplicationContext：单例对象适用
     *      它在构建核心容器时，创建对象采取的策略是立即加载，
     *      只要读取完配置文件，便创建配置对象
     * BeanFactory：多例对象适用
     *      它在构建核心容器时，创建对象采取的策略是延迟加载
     *      什么时候根据id获取对象了，什么时候才真正创建对象
     *
     * 但是否懒加载可以由配置文件决定，所以更多使用的是ApplicationContext
     * @param args
     */
    @Test
    public static void main(String[] args) {
        //1.获取核心容器对象
/*
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取bean对象
        IAccountService as = (IAccountService)ac.getBean("accountService");
        IAccountDao accountDao = ac.getBean("accountDao",IAccountDao.class);
        System.out.println(as);
        System.out.println(accountDao);
*/
        /*beanFactory*/
/*        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        IAccountService as = (IAccountService)factory.getBean("accountService");
        System.out.println(as);*/
    }
}
