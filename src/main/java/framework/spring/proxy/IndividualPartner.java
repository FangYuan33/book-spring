package framework.spring.proxy;

import framework.spring.pojo.Player;
import lombok.Data;

@Data
public class IndividualPartner implements Partner {

    private String name;

    public IndividualPartner(String name) {
        this.name = name;
    }

    @Override
    public void receiveMoney(int money) {
        System.out.println(name + "收到佣金: " + money + "元");
    }

    @Override
    public void playWith(Player player) {
        System.out.println(name + "与" + player.getName() + "一起越快的玩耍");
    }
}
