package geektime.spring.springbucks.waiter.BeanPackage;

/**
 * @Project:waiter-service
 * @Author :maxiaodong
 * @Time :2020/03/24
 * @Desc :
 */
public class Entitlement {
    private String name;
    private int time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
