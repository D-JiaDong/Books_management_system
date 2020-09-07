import Dao.AdminDao;
import packaging.Book_package;

import java.util.ArrayList;

public interface Operation {
    //属性名称显示
    public default void display() {
        System.out.printf("%10s","图书编号");
        System.out.printf("%30s","图书名称");
        System.out.printf("%30s","图书作者");
        System.out.printf("%30s","图书价格");
        System.out.printf("%30s","图书现存数量");
        System.out.println("");
    }
    //查询书籍
    public default void select_Book(){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from t_book";
        ArrayList<Book_package> arrayList=adminDao.Select(sql0,new Book_package());
        //如果该书存在 num增加
        if (arrayList.size()!=0){
            display();
            for (Book_package book_package:arrayList) {
                System.out.printf("%10s",book_package.getBook_id());
                System.out.printf("%30s",book_package.getBook_name());
                System.out.printf("%30s",book_package.getBook_author());
                System.out.printf("%30s",book_package.getBook_price());
                System.out.printf("%30s",book_package.getBook_num());
                System.out.println("");
            }
        }
        //不存在 插入数据
        else {
            System.out.println("查询无果");
        }
    }
    public default void select_Book_byid(int Book_id){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from t_book where Book_id="+Book_id;
        ArrayList<Book_package> arrayList=adminDao.Select(sql0,new Book_package());
        //如果该书存在 num增加
        if (arrayList.size()!=0){
            display();
            for (Book_package book_package:arrayList) {
                System.out.printf("%10s",book_package.getBook_id());
                System.out.printf("%30s",book_package.getBook_name());
                System.out.printf("%30s",book_package.getBook_author());
                System.out.printf("%30s",book_package.getBook_price());
                System.out.printf("%30s",book_package.getBook_num());
                System.out.println("");
            }
        }
        //不存在 插入数据
        else {
            System.out.println("查询无果");
        }
    }
    public default void select_Book_byname(String Book_name){
        AdminDao adminDao = new AdminDao();
        String sql0="select* from t_book where Book_name="+"\""+Book_name+"\"";
        ArrayList<Book_package> arrayList=adminDao.Select(sql0,new Book_package());
        //如果该书存在 num增加
        if (arrayList.size()!=0){
            display();
            for (Book_package book_package:arrayList) {
                System.out.printf("%10s",book_package.getBook_id());
                System.out.printf("%30s",book_package.getBook_name());
                System.out.printf("%30s",book_package.getBook_author());
                System.out.printf("%30s",book_package.getBook_price());
                System.out.printf("%30s",book_package.getBook_num());
                System.out.println("");
            }
        }
        //不存在 插入数据
        else {
            System.out.println("查询无果");
        }
    }
    public default void select_Book_bytypename(String type_name){
        AdminDao adminDao = new AdminDao();
        String sql0="SELECT *FROM t_book WHERE Book_id IN (SELECT Book_id FROM book_to_booktype WHERE Type_id =(SELECT Type_id FROM t_booktype WHERE Type_name='"+type_name+"'))";
        System.out.println(sql0);
        ArrayList<Book_package> arrayList=adminDao.Select(sql0,new Book_package());
        //如果该书存在 num增加
        if (arrayList.size()!=0){
            display();
            for (Book_package book_package:arrayList) {
                System.out.printf("%10s",book_package.getBook_id());
                System.out.printf("%30s",book_package.getBook_name());
                System.out.printf("%30s",book_package.getBook_author());
                System.out.printf("%30s",book_package.getBook_price());
                System.out.printf("%30s",book_package.getBook_num());
                System.out.println("");
            }
        }
        //不存在 插入数据
        else {
            System.out.println("查询无果");
        }
    }
}
