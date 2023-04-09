package vip.chentianxiang.middleware.mybatis.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author: TrueNewBee
 * @Date: 2023/4/9 15:19
 * @Github: https://github.com/TrueNewBee
 * @Description: 定义配置 Yml 配置信息
 * ps: 单独加上@Configuration会标红,需要再加个 @EnableConfigurationProperties({当前类.class})
 */
@EnableConfigurationProperties(MybatisProperties.class)
@ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
public class MybatisProperties {

    public static final String MYBATIS_PREFIX = "mybatis.datasource";

    private String driver;              // com.mysql.jdbc.Driver
    private String url;                 // jdbc:mysql://127.0.0.1:3306/bugstack?useUnicode=true
    private String username;            // root
    private String password;            // 123456
    private String mapperLocations;    // classpath*:mapper/*.xml
    private String baseDaoPackage;      // cn.bugstack.middleware.test.infrastructure.dao

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public String getBaseDaoPackage() {
        return baseDaoPackage;
    }

    public void setBaseDaoPackage(String baseDaoPackage) {
        this.baseDaoPackage = baseDaoPackage;
    }
}