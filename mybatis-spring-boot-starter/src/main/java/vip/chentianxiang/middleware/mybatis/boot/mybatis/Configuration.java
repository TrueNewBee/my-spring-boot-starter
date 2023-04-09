package vip.chentianxiang.middleware.mybatis.boot.mybatis;

import java.sql.Connection;
import java.util.Map;

/**
 * @Author: TrueNewBee
 * @Date: 2023/4/9 15:20
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
public class Configuration {
    protected Connection connection;
    protected Map<String, String> dataSource;
    protected Map<String, XNode> mapperElement;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setDataSource(Map<String, String> dataSource) {
        this.dataSource = dataSource;
    }

    public void setMapperElement(Map<String, XNode> mapperElement) {
        this.mapperElement = mapperElement;
    }
}
