package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class userDao {
    public static int add(User user, Connection conn) {

        try {
            String sql = "INSERT INTO `user` (`name`, `phone`, `nashoucai` , `kouwei`, `favofood`, `time`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,user.getName());
            ps.setString(2,user.getPhone());
            ps.setString(3, user.getNashoucai());
            ps.setString(4, user.getKouwei());
            ps.setString(5,user.getFavofood());
            ps.setString(6,user.getTime());

            System.out.println("add successfully");

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }



/*
    public static void main(String[] args){
        User u=new User();
        userDao userDao=new userDao();
        userDao.add(u,sqlUtil.connect());
    }*/
}
