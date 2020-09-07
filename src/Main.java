import packaging.Admin_package;
import packaging.Book_package;
import packaging.User_package;


import java.awt.print.Book;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       ma_in();
    }
    //主界面
    public  static void ma_in(){
        display1();
        System.out.print("请选择操作内容");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                login_Adm();
                ma_in();
                break;
            case 2:
                login_User();
                ma_in();
                break;
            case 3:
                Register_User();
                ma_in();
                break;
            case 4:
                break;
        }
        return;
    }

    //登录界面
    public static void display1(){
        System.out.println("-------------------------------------");
        System.out.println("| 1,管理员登录   |   2,普通用户登录 |");
        System.out.println("-------------------------------------");
        System.out.println("| 3,普通用户注册 |   4,退出系统     |");
        System.out.println("-------------------------------------");
        int arr[]=new int[4];
        int a=arr.length;
    }

    //管理员登录
    public static void login_Adm(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入ID：");
        int ID = sc.nextInt();
        System.out.print("请输入密码：");
        String password = sc.next();
        Administrator administrator=new Administrator(ID,password);
        boolean islogin=administrator.login();
        if (islogin){
            System.out.println("登录成功");
            Adm_Operation(administrator);

        } else{
            System.out.println("登录失败");
        }
    }
    //管理员操作菜单
    public static void Adm_Operation(Administrator administrator){
        display2();
        System.out.print("请选择具体操作");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                Adm_Operation_addAdm(administrator);
                Adm_Operation(administrator);
                break;
            case 2:
                Adm_Operation_addBook(administrator);
                Adm_Operation(administrator);
                break;
            case 3:
                Adm_Operation_delectBook(administrator);
                Adm_Operation(administrator);
               break;
            case 4:
                Adm_Operation_select_book(administrator);
                Adm_Operation(administrator);
                break;
            case 5:
                Adm_Operation_select_User(administrator);
                Adm_Operation(administrator);
                break;
            case  6:
                Adm_Operation_select_Borrow(administrator);
                Adm_Operation(administrator);
                break;
            case 7:
                break;
        }
    }
    //管理员添加
    public static void Adm_Operation_addAdm(Administrator administrator){
        Scanner sc = new Scanner(System.in);
        System.out.println("请依次输入管理员ID 姓名 性别 年龄 电话号码 账户密码");
        Admin_package admin_package=new Admin_package();
        admin_package.setAdm_id(Integer.parseInt(sc.next()));
        admin_package.setAdm_name(sc.next());
        admin_package.setAdm_sex(sc.next());
        admin_package.setAdm_age(Integer.parseInt(sc.next()));
        admin_package.setAdm_tel(sc.next());
        admin_package.setAdm_pwd(sc.next());
        administrator.add_Adminstrator(admin_package);
    }
    //管理员增加图书
    public static void Adm_Operation_addBook(Administrator administrator){
        Scanner sc = new Scanner(System.in);
        System.out.println("请依次输入书籍的ID 名称 作者 价格 增加数量 类型");
        Book_package book_package=new Book_package();
        book_package.setBook_id(Integer.parseInt(sc.next()));
        book_package.setBook_name(sc.next());
        book_package.setBook_author(sc.next());
        book_package.setBook_price(Float.parseFloat(sc.next()));
        book_package.setBook_num(Integer.parseInt(sc.next()));
        String type=sc.next();
        administrator.add_Book(book_package,type);
    }
    //删除图书
    public static void Adm_Operation_delectBook(Administrator administrator){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入书籍的ID");
        administrator.delete_Book(Integer.parseInt(sc.next()));
    }
    //管理员图书查询
    public static void Adm_Operation_select_book(Administrator administrator){
        display3();
        System.out.print("请选择查找方式");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.print("请输入图书编号");
                int Book_id=sc.nextInt();
                administrator.select_Book_byid(Book_id);
                Adm_Operation_select_book(administrator);
                break;
            case 2:
                System.out.print("请输入图书名称");
                String Book_name=sc.next();
                administrator.select_Book_byname(Book_name);
                Adm_Operation_select_book(administrator);
                break;
            case 3:
                System.out.print("请输入图书类型");
                String Type_name=sc.next();
                administrator.select_Book_bytypename(Type_name);
                Adm_Operation_select_book(administrator);
                break;
            case 4:
                administrator.select_Book();
                Adm_Operation_select_book(administrator);
                break;
            case 5:
                break;
        }
        return;
    }
    //用户查询
    public static void Adm_Operation_select_User(Administrator administrator){
        display_userselect();
        System.out.print("请选择查找方式");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.print("请输入用户编号");
                int User_id=sc.nextInt();
                administrator.select_User_byid(User_id);
                Adm_Operation_select_User(administrator);
                break;
            case 2:
                System.out.print("请输入用户名称");
                String User_name=sc.next();
                administrator.select_User_byname(User_name);
                Adm_Operation_select_User(administrator);
                break;
            case 3:
                System.out.print("请输入用户电话号码");
                String User_tel=sc.next();
                administrator.select_User_bytel(User_tel);
                Adm_Operation_select_User(administrator);
                break;
            case 4:
                administrator.select_User();
                Adm_Operation_select_User(administrator);
                break;
            case 5:
                break;
        }
        return;
    }
    //借阅查询
    public static void Adm_Operation_select_Borrow(Administrator administrator){
        display_Borrowselect();
        System.out.print("请选择查找方式");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.print("请输入图书编号");
                int Book_id=sc.nextInt();
                administrator.select_UserBook_bybookid(Book_id);
                Adm_Operation_select_Borrow(administrator);
                break;
            case 2:
                System.out.print("请输入图书名称");
                String Book_name=sc.next();
                administrator.select_UserBook_bybookname(Book_name);
                Adm_Operation_select_Borrow(administrator);
                break;
            case 3:
                System.out.print("请输入用户编号");
                int User_id=sc.nextInt();
                administrator.select_UserBook_byuserid(User_id);
                Adm_Operation_select_Borrow(administrator);
                break;
            case 4:
                System.out.print("请输入用户姓名");
                String User_name=sc.next();
                administrator.select_UserBook_byusername(User_name);
                Adm_Operation_select_Borrow(administrator);
                break;
            case 5:
                administrator.select_UserBook();
                Adm_Operation_select_Borrow(administrator);
                break;
        }
        return;
    }

    //管理员界面
    public static void display2(){
        System.out.println("-------------------------------------");
        System.out.println("| 1,添加管理员   |   2,增添书籍     |");
        System.out.println("-------------------------------------");
        System.out.println("| 3,删除图书     |   4,查找图书     |");
        System.out.println("-------------------------------------");
        System.out.println("| 5,用户查询     |   6,借阅查询     |");
        System.out.println("-------------------------------------");
        System.out.println("|           7,退出账户              |");
        System.out.println("-------------------------------------");
    }
    //管理员图书查询界面
    public static void display3(){
        System.out.println("-------------------------------------");
        System.out.println("| 1,图书编号查找 |   2,书籍名称查找 |");
        System.out.println("-------------------------------------");
        System.out.println("| 3,图书类型查找 |   4,显示图书列表 |");
        System.out.println("-------------------------------------");
        System.out.println("|           5,退出查询              |");
        System.out.println("-------------------------------------");
    }
    //管理员用户查询界面
    public static void display_userselect(){
        System.out.println("-------------------------------------");
        System.out.println("| 1,用户编号查找 |   2,用户姓名查找 |");
        System.out.println("-------------------------------------");
        System.out.println("| 3,用户电话查找 |   4,显示用户列表 |");
        System.out.println("-------------------------------------");
        System.out.println("|           5,退出查询              |");
        System.out.println("-------------------------------------");
    }
    //管理员用户借阅查询
    public static void display_Borrowselect(){
        System.out.println("-------------------------------------");
        System.out.println("| 1,图书编号查找 |   2,图书姓名查找 |");
        System.out.println("-------------------------------------");
        System.out.println("| 3,用户编号查找 |   4,用户姓名查找 |");
        System.out.println("-------------------------------------");
        System.out.println("| 5,显示借阅列表 |   6,退出查询     |");
        System.out.println("-------------------------------------");
    }







    //普通用户登录
    public static void login_User(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入ID：");
        int ID = sc.nextInt();
        System.out.print("请输入密码：");
        String password = sc.next();
        User user=new User(ID,password);
        boolean islogin=user.login();
        if (islogin){
            System.out.println("登录成功");
            User_Operation(user);

        } else{
            System.out.println("登录失败");
        }
    }
    //普通用户操作
    public static void User_Operation(User user){
        display4();
        System.out.print("请选择具体操作");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                User_Operation_borrowBook(user);
                User_Operation(user);
                break;
            case 2:
                User_Operation_backBook(user);
                User_Operation(user);
                break;
            case 3:
                User_Operation_select(user);
                User_Operation(user);
                break;
            case 4:
                break;
        }
    }
    //普通用户借书
    public static void User_Operation_borrowBook(User user){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入借阅图书编号和数量");
        int Book_id=sc.nextInt();
        int Book_num=sc.nextInt();
        user.br_Book(user.getUser_id(),Book_id,0-Book_num);
    }
    //普通用户还书
    public static void User_Operation_backBook(User user){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入归还图书编号和数量");
        int Book_id=sc.nextInt();
        int Book_num=sc.nextInt();
        user.br_Book(user.getUser_id(),Book_id,Book_num);
    }
    //普通用户查询
    public static void User_Operation_select(User user){
        display5();
        System.out.print("请选择查询方式");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("请输入图书id");
                int Book_id=sc.nextInt();
                user.select_Book_byid(Book_id);
                User_Operation_select(user);
                break;
            case 2:
                System.out.println("请输入图书名称");
                String Book_name=sc.next();
                user.select_Book_byname(Book_name);
                User_Operation_select(user);
                break;
            case 3:
                System.out.println("请输入图书类型");
                String Type_name=sc.next();
                user.select_Book_bytypename(Type_name);
                User_Operation_select(user);
                break;
            case 4:
                user.select_Book();
                User_Operation_select(user);
                break;
            case 5:
                break;
        }


    }


    //普通用户界面
    public static void display4(){
        System.out.println("-------------------------------------");
        System.out.println("| 1,借阅图书     |   2,归还图书     |");
        System.out.println("-------------------------------------");
        System.out.println("| 3,查找图书     |   4,退出账户     |");
        System.out.println("-------------------------------------");

    }
    //普通用户图书查询界面
    public static void display5(){
        System.out.println("-------------------------------------");
        System.out.println("| 1,图书编号查找 |   2,书籍名称查找 |");
        System.out.println("-------------------------------------");
        System.out.println("| 3,图书类型查找 |   4,显示图书列表 |");
        System.out.println("-------------------------------------");
        System.out.println("|           5,退出查询              |");
        System.out.println("-------------------------------------");
    }







    //普通用户注册
    public static void  Register_User(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请依次输入ID 姓名 性别 年龄 电话 密码");
        User_package u_p=new User_package();
        u_p.setUser_id(sc.nextInt());
        u_p.setUser_name(sc.next());
        u_p.setUser_sex(sc.next());
        u_p.setUser_age(sc.nextInt());
        u_p.setUser_tel(sc.next());
        u_p.setUser_pwd(sc.next());
        User user=new User(u_p);
    }
}