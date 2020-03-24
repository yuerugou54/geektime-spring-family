package geektime.spring.springbucks.waiter.BeanPackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project:waiter-service
 * @Author :maxiaodong
 * @Time :2020/03/24
 * @Desc :
 */
@Configuration
public class AppConfig {
    @Bean(name = "entitlement")
    public Entitlement entitlement() {
        Entitlement ent = new Entitlement();
        ent.setName(entityA().getName());
        ent.setTime(1);
        return ent;
    }

    @Bean(name = "entityA")
    public EntityA entityA() {
        EntityA ent = new EntityA();
        ent.setName(entityB().getName());
        ent.setTime(2);
        return ent;
    }

    @Bean(name = "entityB")
    public EntityB entityB() {
        EntityB ent = new EntityB();
        ent.setName("entityB");
        ent.setTime(entitlement().getTime());
        return ent;
    }

}
