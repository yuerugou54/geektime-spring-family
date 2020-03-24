package geektime.spring.springbucks.waiter.BeanPackage.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Project:waiter-service
 * @Author :maxiaodong
 * @Time :2020/03/24
 * @Desc :
 */
@Component
public class FactoryBeanPojo implements FactoryBean {
    private String type;

    @Override
    public Object getObject() throws Exception {
        if ("student".equals(type)) {
            return new Student();
        } else {
            return new School();
        }

    }

    @Override
    public Class getObjectType() {
        return School.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class Main {
    public static void main(String[] args) {
//        String url = "com/spring/config/BeanConfig.xml";
//        ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext(url);
        AnnotationConfigApplicationContext cpxa = new AnnotationConfigApplicationContext();
        cpxa.register(FactoryBeanPojo.class);
        cpxa.refresh();
        Object school = cpxa.getBean("factoryBeanPojo");
        //只能通过&符获取到原生bean
        FactoryBeanPojo factoryBeanPojo = (FactoryBeanPojo) cpxa.getBean("&factoryBeanPojo");
        System.out.println(school.getClass().getName());//输出student
        System.out.println(factoryBeanPojo.getClass().getName());//输出factoryBean

    }
}
