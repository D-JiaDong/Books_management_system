package Dao;

import packaging.*;
import packaging.Package;

import java.sql.*;
import java.util.ArrayList;

public class Dao<E> {

    //更新
    public boolean Update(String Sql){
        boolean bo=true;
        Statement stmt=null;
        Connection conn=null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //定义sql语句
            String sql=Sql;
            //创建连接对象
            conn= DriverManager.getConnection("jdbc:mysql:///Library_Management_System1?serverTimezone=UTC","root","123456");
            //获取执行sql对象
            stmt=conn.createStatement();
            //执行sql
            int count=stmt.executeUpdate(sql);
            if(count>0) {
                System.out.println(Sql+"：操作成功");
                bo=true;
            }
            else{
                System.out.println(Sql+"：数据不存在");
                bo=false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if (stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return bo;
    }
    //查询
    public ArrayList Select(String Sql, Package pa) {
        Statement stmt = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int table=0;
        ArrayList<Admin_package> arrayList1=new ArrayList<>();
        ArrayList<Book_package> arrayList2=new ArrayList<>();
        ArrayList<User_package> arrayList3=new ArrayList<>();
        ArrayList<Book_to_Booktype_pa> arrayList4=new ArrayList<>();
        ArrayList<Booktype_package> arrayList5=new ArrayList<>();
        ArrayList<User_to_Book_pa> arrayList6=new ArrayList<>();
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //定义sql语句
            String sql = Sql;
            //创建连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///Library_Management_System1?serverTimezone=UTC", "root", "123456");
            //获取执行sql对象
            stmt = conn.createStatement();
            //执行sql
            resultSet = stmt.executeQuery(sql);
            //bool 判断那个表
            if(pa instanceof Admin_package) {
                while (resultSet.next()) {
                    Admin_package admin_package = new Admin_package();
                    admin_package.setAdm_id(resultSet.getInt("Adm_id"));
                    admin_package.setAdm_name(resultSet.getString("Adm_name"));
                    admin_package.setAdm_name(resultSet.getString("Adm_sex"));
                    admin_package.setAdm_age(resultSet.getInt("Adm_age"));
                    admin_package.setAdm_tel(resultSet.getString("Adm_tel"));
                    admin_package.setAdm_pwd(resultSet.getString("Adm_pwd"));
                    arrayList1.add(admin_package);
                }
                table=1;
            }
            if(pa instanceof Book_package) {
                while (resultSet.next()) {
                    Book_package book_package = new Book_package();
                    book_package.setBook_id(resultSet.getInt("Book_id"));
                    book_package.setBook_name(resultSet.getString("Book_name"));
                    book_package.setBook_author(resultSet.getString("BooK_author"));
                    book_package.setBook_price(resultSet.getFloat("Book_price"));
                    book_package.setBook_num(resultSet.getInt("Book_num"));
                    arrayList2.add(book_package);
                }
                table=2;
            }
            if(pa instanceof User_package) {
                while (resultSet.next()) {
                    User_package user_package= new User_package();
                    user_package.setUser_id(resultSet.getInt("User_id"));
                    user_package.setUser_name(resultSet.getString("User_name"));
                    user_package.setUser_sex(resultSet.getString("User_sex"));
                    user_package.setUser_age(resultSet.getInt("User_age"));
                    user_package.setUser_tel(resultSet.getString("User_tel"));
                    user_package.setUser_pwd(resultSet.getString("User_pwd"));
                    arrayList3.add(user_package);
                }
                table=3;
            }
            if(pa instanceof Book_to_Booktype_pa) {
                while (resultSet.next()) {
                    Book_to_Booktype_pa Btype= new Book_to_Booktype_pa();
                    Btype.setBook_id(resultSet.getInt("Book_id"));
                    Btype.setType_id(resultSet.getShort("Type_id"));
                    arrayList4.add(Btype);
                }
                table=4;
            }
            if(pa instanceof Booktype_package) {
                while (resultSet.next()) {
                  Booktype_package booktype_package=new Booktype_package();
                  booktype_package.setType_id(resultSet.getInt("Type_id"));
                  booktype_package.setType_name(resultSet.getString("Type_name"));
                  arrayList5.add(booktype_package);
                }
                table=5;
            }
            if(pa instanceof User_to_Book_pa) {
                while (resultSet.next()) {
                    User_to_Book_pa u_b_pa=new User_to_Book_pa();
                    u_b_pa.setUser_id(resultSet.getInt("User_id"));
                    u_b_pa.setBook_id(resultSet.getInt("Book_id"));
                    u_b_pa.setBook_num(resultSet.getInt("Book_num"));
                    arrayList6.add(u_b_pa);
                }
                table=6;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (table==1)
            return arrayList1;
        if (table==2)
            return arrayList2;
        if (table==3)
            return arrayList3;
        if (table==4)
            return arrayList4;
        if (table==5)
            return arrayList5;
        if (table==6)
            return arrayList6;
        return null;
    }
}