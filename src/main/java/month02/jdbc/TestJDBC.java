package month02.jdbc;

import org.jboss.netty.handler.codec.replay.VoidEnum;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
    @BeforeClass
    public void before(){
        try {
            Class<?> name = Class.forName("com.mysql.jdbc.Driver");
            System.out.println(name);
            String url = "jdbc:mysql://hadoop102:3306/company";
            String username ="root";
            String password = "1";
            Connection connection = DriverManager.getConnection(url, username, password);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void  test1(){
//        String sql = ""
    }

}
