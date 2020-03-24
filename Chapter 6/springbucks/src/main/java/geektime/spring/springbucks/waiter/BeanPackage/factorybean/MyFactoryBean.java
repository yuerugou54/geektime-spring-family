package geektime.spring.springbucks.waiter.BeanPackage.factorybean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Project:waiter-service
 * @Author :maxiaodong
 * @Time :2020/03/24
 * @Desc :
 */
@Component
public class MyFactoryBean implements FactoryBean<Object>, InitializingBean, DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(MyFactoryBean.class);
    private String interfaceName;
    private Object target;
    private Object proxyObj;
    @Override
    public void destroy() throws Exception {
        logger.debug("destroy......");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        proxyObj = Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[] { Class.forName(interfaceName) },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        logger.debug("invoke method......" + method.getName());
                        logger.debug("invoke method before......" + System.currentTimeMillis());
                        Object result = method.invoke(target, args);
                        logger.debug("invoke method after......" + System.currentTimeMillis());
                        return result;            }
                });
        logger.debug("afterPropertiesSet......");
    }

    @Override
    public Object getObject() throws Exception {
        logger.debug("getObject......");
        return proxyObj;
    }

    @Override
    public Class<?> getObjectType() {
        return proxyObj == null ? Object.class : proxyObj.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxyObj() {
        return proxyObj;
    }

    public void setProxyObj(Object proxyObj) {
        this.proxyObj = proxyObj;
    }

}

class Main2{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ct = new AnnotationConfigApplicationContext();
        ct.register(HelloWorldService.class);
        ct.refresh();
        HelloWorldService helloWorldService = (HelloWorldService)ct.getBean("helloWorldService");
        helloWorldService.sayHello();
    }
}

@Component
class HelloWorldService extends MyFactoryBean {
    @Override
    public void setInterfaceName(String interfaceName) {
        super.setInterfaceName("helloWorldService");
    }

    @Override
    public void setTarget(Object target) {
        super.setTarget("HelloWorldService");
    }

    void sayHello(){
        System.out.println("本类中的hello");
    }
}
