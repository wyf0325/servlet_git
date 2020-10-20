package dao;

import pojo.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //封装一个方法根据管理员的名字查询管理员的信息
    public User selectAdminInfo(String name){
        User user=null;
      Connection connection= DBUtil.getConn();
      String sql="select * from tb_user where user_name=?";

        try {
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,name);
           ResultSet r= ps.executeQuery();

           if(r.next()){
               //取出数据的每个字段值
               Integer id = r.getInt("ID");
               String userName = r.getString("USER_NAME");
               String pwd = r.getString("USER_PASSWORD");
               Integer userType = r.getInt("USER_TYPE");
               Integer userState = r.getInt("USER_STATE");
               //将取出的数据封装到对象
               user=new User();
               user.setId(id);
               user.setUserName(userName);
               user.setUserPassword(pwd);
               user.setUserType(userType);
               user.setUserState(userState);


           }




        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return  user;

    }

    // 查询用户信息
    public List<User> userSelect(String id) throws SQLException{
        List<User> list=new ArrayList<>();
        Connection conn = DBUtil.getConn();
        String sql="";

        if(id==null||id.equals("")){
            sql="select * from tb_user";
        }else{
            sql="select * from tb_user where id="+id;
        }

       PreparedStatement ps= conn.prepareStatement(sql);
        ResultSet r = ps.executeQuery();
        //遍历结果集
        while(r.next()){
            Integer id1 = r.getInt("ID");
            String userName = r.getString("USER_NAME");
            String pwd = r.getString("USER_PASSWORD");
            Integer userType = r.getInt("USER_TYPE");
            Integer userState = r.getInt("USER_STATE");
           User  user = new User();
            user.setId(id1);
            user.setUserName(userName);
            user.setUserPassword(pwd);
            user.setUserType(userType);
            user.setUserState(userState);
                list.add(user);
        }


    return list;
    }
}
