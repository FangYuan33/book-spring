package framework.spring.proxy;

import framework.spring.pojo.Player;

public interface Partner {
    void receiveMoney(int money);

    void playWith(Player player);
}
