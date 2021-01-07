package util;

import javafx.scene.media.VideoTrack;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

/**
 * 数据库工具类 - 集成了 增 删 改 查 连接  关闭等操作
 */
public class BaseDao {

    private static Connection conn = null;
    private static PreparedStatement ps = null;
    public static ResultSet rs = null;
    /**
     *  1.通用的连接
     */
    public Connection getConnection(){
        try {
            /*// 1. 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newsmanagersystem","root","root");*/
            // 从tomcat连接池中获取连接对象
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/news");
            // 给连接对象赋值
            conn = dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 2.通用的查询 返回结果集
     *  三个点代表可以接受任意个数的参数
     */
    public void executeQuery(String sql,Object...args) throws Exception{
        conn = getConnection();// 获取连接对象
        ps = conn.prepareStatement(sql);
        // 遍历填充参数
        if (args != null){
            for (int i = 0; i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
        }
        // 执行SQL语句
        rs =  ps.executeQuery();

    }

    /***
     * 3.通用的关流
     */
    public void closeAll(){

        try {
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (conn != null){
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /***
     * 4.通用的增删改
     */
    public int executeUpdate(String sql,Object...args)throws Exception{
        conn = getConnection();
        ps  = conn.prepareStatement(sql);
        if (args != null){
            for (int i = 0; i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
        }
        return ps.executeUpdate();
    }

}
