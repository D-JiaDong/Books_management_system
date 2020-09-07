import Dao.AdminDao;
import packaging.*;

import java.util.ArrayList;

public class Administrator extends Person implements Operation{
   private int Adm_id;
   private String Adm_pwd;

    public Administrator(int adm_id, String adm_pwd) {
        Adm_id = adm_id;
        Adm_pwd = adm_pwd;
    }


    //登录
    public boolean login() {
       String sql = "select * from t_administrator where Adm_id=" + Adm_id;
       //创建管理员操作对象 接收结果集合
       AdminDao adminDao = new AdminDao();
       ArrayList<Admin_package> arrayList = adminDao.Select(sql,new Admin_package());

       String pwd = null;
       boolean notempty = true;
       if (arrayList.size() != 0) {
           for (Admin_package Ap : arrayList) {
               pwd = Ap.getAdm_pwd();
           }
       } else {
           System.out.println("用户名不存在");
           notempty = false;
       }
       if (Adm_pwd.equals(pwd) && notempty) {
           return true;
       } else {
           if(notempty==true)
               System.out.println("密码错误");
           return false;
       }
   }



    //添加管理员
    public  void add_Adminstrator(Admin_package admin_package){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from t_administrator where Adm_id="+admin_package.getAdm_id();
        ArrayList<Admin_package> arrayList=adminDao.Select(sql0,new Admin_package());
        if (arrayList.size()!=0) {
            System.out.println("用户ID已存在，添加失败");
        }
        else {String sql="insert into t_administrator values("+admin_package.getAdm_id()+",\""+admin_package.getAdm_name()+"\",\""+admin_package.getAdm_sex()+"\","+admin_package.getAdm_age()
                    +",\""+admin_package.getAdm_tel()+"\",\""+admin_package.getAdm_pwd()+"\")";
            System.out.println(sql);
            adminDao.Update(sql);
        }
    }
    //属性名称显示
   /* public  void display() {
        System.out.printf("%10s","图书编号");
        System.out.printf("%30s","图书名称");
        System.out.printf("%30s","图书作者");
        System.out.printf("%30s","图书价格");
        System.out.printf("%30s","图书现存数量");
        System.out.println("");
    }*/


    //增加图书
    public void add_Book(Book_package book_package,String type) {
        AdminDao adminDao = new AdminDao();
        //判断是否有此编号图书
        String sql0 = "select* from t_book where Book_id=" + book_package.getBook_id();
        ArrayList<Book_package> arrayList = adminDao.Select(sql0, new Book_package());
        //判断是否有此类型图书
        String sql1 = "select* from t_booktype where Type_name=\"" + type + "\"";
        ArrayList<Booktype_package> arrayList1 = adminDao.Select(sql1, new Booktype_package());
        //如果该书存在 num增加
        if (arrayList.size() != 0 && arrayList1.size() != 0) {
            System.out.println("BookID已存在");
            String sql = "update t_book set Book_num=Book_num+" + book_package.getBook_num() + " where Book_id=" + book_package.getBook_id();
            System.out.println(sql);
            adminDao.Update(sql);
        }
        //不存在 插入数据
        else {
            //book表插入数据
            String sql = "insert into t_book values(" + book_package.getBook_id() + ",\"" + book_package.getBook_name() + "\",\"" + book_package.getBook_author() + "\"," + book_package.getBook_price()
                    + "," + book_package.getBook_num() + ")";
            adminDao.Update(sql);

            //book_booktype表插入数据
            //查询类型id
            int Type_id = 0;
            //能够在类型表找到 就直接向中间表插入数据
            for (Booktype_package Btype : arrayList1) {
                Type_id = Btype.getType_id();
                System.out.println(Type_id);
            }
            String sql2 = "insert into book_to_booktype values(" + book_package.getBook_id() + "," + Type_id + ")";
            adminDao.Update(sql2);
            System.out.println(sql2);
        }
    }



    //删除图书
    public void delete_Book(int Book_id) {
        AdminDao adminDao = new AdminDao();
        String sql0="delete from t_book where Book_id="+Book_id;
        String sql1="delete from book_to_booktype where Book_id="+Book_id;
        adminDao.Update(sql1);
        adminDao.Update(sql0);
    }



    //属性名称显示
    public  void display1() {
        System.out.printf("%10s","用户ID");
        System.out.printf("%30s","用户姓名");
        System.out.printf("%30s","用户性别");
        System.out.printf("%30s","用户年龄");
        System.out.printf("%30s","用户电话号码");
        System.out.println("");
    }
    public  void display2() {
        System.out.printf("%10s","用户ID");
        System.out.printf("%30s","图书编号");
        System.out.printf("%30s","借阅数目");
        System.out.println("");
    }
    public  void  display_Username(){
        System.out.printf("%10s","用户ID");
        System.out.printf("%30s","用户姓名");
        System.out.printf("%30s","图书编号");
        System.out.printf("%30s","借阅数目");
        System.out.println("");

    }
    public  void display_Bookname() {
        System.out.printf("%10s","用户ID");
        System.out.printf("%30s","图书编号");
        System.out.printf("%30s","图书名称");
        System.out.printf("%30s","借阅数目");
        System.out.println("");
    }

    //查询用户
    public void select_User(){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from t_user";
        ArrayList<User_package> arrayList=adminDao.Select(sql0,new User_package());
        if (arrayList.size()!=0){
            display1();
            for (User_package user_package:arrayList) {
                System.out.printf("%10s",user_package.getUser_id());
                System.out.printf("%30s",user_package.getUser_name());
                System.out.printf("%30s",user_package.getUser_sex());
                System.out.printf("%30s",user_package.getUser_age());
                System.out.printf("%30s",user_package.getUser_tel());
                System.out.println("");
            }
        }
        else {
            System.out.println("查询无果");
        }
    }
    public void select_User_byid(int User_id){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from t_user where User_id="+User_id;
        ArrayList<User_package> arrayList=adminDao.Select(sql0,new User_package());
        if (arrayList.size()!=0){
            display1();
            for (User_package user_package:arrayList) {
                System.out.printf("%10s",user_package.getUser_id());
                System.out.printf("%30s",user_package.getUser_name());
                System.out.printf("%30s",user_package.getUser_sex());
                System.out.printf("%30s",user_package.getUser_age());
                System.out.printf("%30s",user_package.getUser_tel());
                System.out.println("");
            }
        }
        else {
            System.out.println("查询无果");
        }
    }
    public void select_User_byname(String User_name){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from t_user where User_name=\""+User_name+"\"";
        ArrayList<User_package> arrayList=adminDao.Select(sql0,new User_package());
        if (arrayList.size()!=0){
            display1();
            for (User_package user_package:arrayList) {
                System.out.printf("%10s",user_package.getUser_id());
                System.out.printf("%30s",user_package.getUser_name());
                System.out.printf("%30s",user_package.getUser_sex());
                System.out.printf("%30s",user_package.getUser_age());
                System.out.printf("%30s",user_package.getUser_tel());
                System.out.println("");
            }
        }
        else {
            System.out.println("查询无果");
        }
    }
    public void select_User_bytel(String User_tel){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from t_user where User_tel=\""+User_tel+"\"";
        ArrayList<User_package> arrayList=adminDao.Select(sql0,new User_package());
        if (arrayList.size()!=0){
            display1();
            for (User_package user_package:arrayList) {
                System.out.printf("%10s",user_package.getUser_id());
                System.out.printf("%30s",user_package.getUser_name());
                System.out.printf("%30s",user_package.getUser_sex());
                System.out.printf("%30s",user_package.getUser_age());
                System.out.printf("%30s",user_package.getUser_tel());
                System.out.println("");
            }
        }
        else {
            System.out.println("查询无果");
        }
    }

    //查询借阅记录
    public void select_UserBook(){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from user_to_book";
        ArrayList<User_to_Book_pa> arrayList=adminDao.Select(sql0,new User_to_Book_pa());
        if (arrayList.size()!=0){
            display2();
            for (User_to_Book_pa pa:arrayList) {
                System.out.printf("%10s",pa.getUser_id());
                System.out.printf("%30s",pa.getBook_id());
                System.out.printf("%30s",pa.getBook_num());
                System.out.println("");
            }
        }
        else {
            System.out.println("查询无果");
        }
    }
    public void select_UserBook_byuserid(int User_id){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from user_to_book where User_id="+User_id;
        ArrayList<User_to_Book_pa> arrayList=adminDao.Select(sql0,new User_to_Book_pa());
        if (arrayList.size()!=0){
            display2();
            for (User_to_Book_pa pa:arrayList) {
                System.out.printf("%10s",pa.getUser_id());
                System.out.printf("%30s",pa.getBook_id());
                System.out.printf("%30s",pa.getBook_num());
                System.out.println("");
            }
        }
        else {
            System.out.println("查询无果");
        }
    }
    public void select_UserBook_byusername(String User_name){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from user_to_book where User_id IN (select User_id from t_user where User_name=\""+User_name+"\")";
        ArrayList<User_to_Book_pa> arrayList=adminDao.Select(sql0,new User_to_Book_pa());
        if (arrayList.size()!=0){
            display_Username();
            for (User_to_Book_pa pa:arrayList) {
                System.out.printf("%10s",pa.getUser_id());
                System.out.printf("%30s",User_name);
                System.out.printf("%30s",pa.getBook_id());
                System.out.printf("%30s",pa.getBook_num());
                System.out.println("");
            }
        }
        else {
            System.out.println("查询无果");
        }
    }
    public void select_UserBook_bybookid(int Book_id){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from user_to_book where Book_id="+Book_id;
        ArrayList<User_to_Book_pa> arrayList=adminDao.Select(sql0,new User_to_Book_pa());
        if (arrayList.size()!=0){
            display2();
            for (User_to_Book_pa pa:arrayList) {
                System.out.printf("%10s",pa.getUser_id());
                System.out.printf("%30s",pa.getBook_id());
                System.out.printf("%30s",pa.getBook_num());
                System.out.println("");
            }
        }
        else {
            System.out.println("查询无果");
        }
    }
    public void select_UserBook_bybookname(String Book_name){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from user_to_book where Book_id IN (select Book_id from t_book where Book_name=\""+Book_name+"\")";
        ArrayList<User_to_Book_pa> arrayList=adminDao.Select(sql0,new User_to_Book_pa());
        if (arrayList.size()!=0){
            display_Bookname();
            for (User_to_Book_pa pa:arrayList) {
                System.out.printf("%10s",pa.getUser_id());
                System.out.printf("%30s",pa.getBook_id());
                System.out.printf("%30s",Book_name);
                System.out.printf("%30s",pa.getBook_num());
                System.out.println("");
            }
        }
        else {
            System.out.println("查询无果");
        }
    }


}
