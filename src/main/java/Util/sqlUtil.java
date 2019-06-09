package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库驱动类
 */
public class sqlUtil {

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/daily?"
                    + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";
            conn = DriverManager.getConnection(url);

            System.out.println("connect DataBase successfully---------------");

        } catch (
                ClassNotFoundException e)

        { 
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
