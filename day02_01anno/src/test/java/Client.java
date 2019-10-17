import com.dao.IAccountDao;
import com.service.IAccountService;
import org.junit.Test;
import org.springframework.context.*;
import org.springframework.context.support.*;

public class Client {
    @Test
    public static void main(String[] args) {
        //1.获取核心容器对象
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取bean对象
        IAccountService as = (IAccountService)ac.getBean("accountService");
        as.saveAccount();
        ac.close();
     }
}
