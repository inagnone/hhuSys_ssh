package cjcx.utils;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Administrator on 2017/1/4.
 */
public class JDBCUtils {

    /**
     * 获取连接
     * @return
     */
    public static Connection getConn(){
        String url = "jdbc:sybase:Tds:PC-20160712FWRU:5000/test?charset=cp936";
        String name = "sa";
        String password = "";
        try {
            Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance(); //注册驱动
            Connection conn =  DriverManager.getConnection(url, name, password); // 获取连接
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
