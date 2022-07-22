package framework.spring;

import framework.spring.factory.PartnerPlatform;
import framework.spring.pojo.Player;
import framework.spring.proxy.Partner;

public class AOPEasyApplication {

    public static void main(String[] args) {
        Player player = new Player("郝吾撩");

        Partner partner = PartnerPlatform.getPartner(100);

        partner.receiveMoney(0);
        partner.playWith(player);

        Partner cglibPartner = PartnerPlatform.getCglibPartner(100);
        cglibPartner.receiveMoney(100);
        cglibPartner.playWith(player);
    }
}
