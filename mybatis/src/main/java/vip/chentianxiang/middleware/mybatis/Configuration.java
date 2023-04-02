package vip.chentianxiang.middleware.mybatis;

import java.sql.Connection;
import java.util.Map;

/**
 * @Author: TrueNewBee
 * @Date: 2023/4/1 23:33
 * @Github: https://github.com/TrueNewBee
 * @Description: 数据源配置
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
