package com.example.JPA.SQL;

import java.sql.*;

public class SqlApplication {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);

        try{
            String sql = "select * from member";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                Member member = new Member();
                member.setAge(age);
                member.setName(name);
                member.setId(id);
                System.out.println(member.toString());
            }
        }catch(Exception e){
            System.out.println("DB Connetion Fail: "+e);
        }
    }


}
