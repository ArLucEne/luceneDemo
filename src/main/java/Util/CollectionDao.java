package Util;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CollectionDao {
    public int add(Menu menu, Connection conn) {

        try {
            String sql = "INSERT INTO `menucollection` (`url`, `name`, `material` , `steps`, `date`, `picpath`, `kouwei`) VALUES (?, ?, ?, ?, ?, ?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, menu.getUrl());
            ps.setString(2,menu.getName());
            ps.setString(3, menu.getMaterial());
            ps.setString(4, menu.getSteps());
            ps.setDate(5,menu.getDate());
            ps.setString(6,menu.getPicPath());
            ps.setString(7,menu.getKouwei());

            System.out.println("add successfully");

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
