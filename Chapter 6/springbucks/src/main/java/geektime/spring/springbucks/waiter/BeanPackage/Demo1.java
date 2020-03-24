package geektime.spring.springbucks.waiter.BeanPackage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Project:waiter-service
 * @Author :maxiaodong
 * @Time :2020/03/24
 * @Desc :
 */
public class Demo1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ct = new AnnotationConfigApplicationContext();
        ct.register(AppConfig.class);
        ct.refresh();
        Entitlement el1 = (Entitlement)ct.getBean("entitlement");
        System.out.println(el1.getName());
//        Entitlement el2 = (Entitlement)ct.getBean("entitlement2");
//        System.out.println(el2.getName());
    }
}
