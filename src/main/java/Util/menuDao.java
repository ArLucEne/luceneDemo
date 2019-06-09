package Util;



import java.sql.*;


 public class menuDao {

     /**
      * 获得connection
      * 将给定的menu添加到数据库
      * @param menu
      * @param conn
      * @return
      */
    public int add(Menu menu, Connection conn) {

            try {
                String sql = "INSERT INTO `menu` (`url`, `name`, `material` , `steps`, `date`, `picpath`, `kouwei`) VALUES (?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, menu.getUrl());
                ps.setString(2,menu.getName());
                ps.setString(3, menu.getMaterial());
                ps.setString(4, menu.getSteps());
                ps.setDate(5,menu.getDate());
                ps.setString(6,menu.getPicPath());
                ps.setString(7,menu.getKouwei());
                return ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return -1;
    }



 }

