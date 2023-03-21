package vip.chentianxiang.middleware.whitelist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/21 10:38
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
@ConfigurationProperties("white.whitelist")
public class WhiteListProperties {

    private String users;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
