import Dao.UserDao;
import packaging.Book_package;
import packaging.User_package;
import packaging.User_to_Book_pa;
import java.util.ArrayList;

public class User  extends Person implements Operation{
    private int User_id;
    private String User_pwd;

    public User(int user_id, String user_pwd) {
        User_id = user_id;
        User_pwd = user_pwd;
    }

    public User(User_package u_pa) {
        super(u_pa.getUser_name(), u_pa.getUser_sex(),u_pa.getUser_age(), u_pa.getUser_tel());
        User_id = u_pa.getUser_id();
        User_pwd = u_pa.getUser_pwd();
        Register();
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getUser_pwd() {
        return User_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        User_pwd = user_pwd;
    }


    //用户登录
    public boolean login(){
        String sql = "select * from t_user where User_id=" + User_id;
        //创建管理员操作对象 接收结果集合
        UserDao userDao  = new UserDao();
        ArrayList<User_package> arrayList = userDao.Select(sql,new User_package());

        String pwd = null;
        boolean notempty = true;
        if (arrayList.size() != 0) {
            for (User_package Up : arrayList) {
                pwd = Up.getUser_pwd();
            }
        } else {
            System.out.println("用户名不存在");
            notempty = false;
        }
        if (User_pwd.equals(pwd) && notempty) {
            return true;
        } else {
            if(notempty==true)
                System.out.println("密码错误");
            return false;
        }
    }
    //用户借还图书
    public void br_Book(int user_id,int Book_id,int Book_num){
        UserDao userDao = new UserDao();
        String sql0="select* from t_book where Book_id="+Book_id;
        ArrayList<Book_package> arrayList=userDao.Select(sql0,new Book_package());
        //如果该书存在 num增加
        if (arrayList.size()!=0) {
//            System.out.println("BookID已存在");
            String sql;
            String sql1;
            //还书
            if (Book_num>0) {
                sql = "update t_book set Book_num=Book_num+" + Book_num + " where Book_id=" + Book_id;
                sql1="update user_to_book set Book_num=Book_num+" + (0-Book_num) + " where Book_id=" + Book_id+" and User_id="+user_id;
                boolean bo=userDao.Update(sql1);
                if (bo){
                    userDao.Update(sql);
                }
//                System.out.println(sql1);
//                System.out.println(sql);
                //查询该条记录是否有存在价值}'
                //借书数为零删除记录
                String sql_select="select* from user_to_book where Book_id="+ Book_id+" and User_id="+user_id;
                System.out.println(sql_select);
                ArrayList<User_to_Book_pa> arrayList2=userDao.Select(sql_select,new User_to_Book_pa());
                if (arrayList2.size()!=0){
                    int num=0;
                    System.out.println(arrayList2.size());
                  for(User_to_Book_pa u_b_pa:arrayList2){
                      num=u_b_pa.getBook_num();
//                      System.out.println(u_b_pa.getBook_id()+"------"+u_b_pa.getUser_id());
//                      System.out.println(u_b_pa.getBook_num());
                  }
                  if(num==0) {
                      String sql_delect = "delete from user_to_book where Book_id=" + Book_id+" and User_id="+user_id;
                      userDao.Update(sql_delect);
//                      System.out.println(sql_delect);
                  }
                }
            }
            //借书
            else {
                sql = "update t_book set Book_num=Book_num+" + Book_num + " where Book_id=" + Book_id+" and Book_num>="+(0-Book_num);
                boolean bo=userDao.Update(sql);
                if (bo){
                    //查询是否存在借书记录
                    String sql_select="select* from user_to_book where Book_id=" + Book_id+" and User_id="+user_id;
                    ArrayList<User_to_Book_pa> arrayList1=userDao.Select(sql_select,new User_to_Book_pa());
                    //存在就累加
                    if (arrayList1.size()!=0){
                        sql1="update user_to_book set Book_num=Book_num+" + (0-Book_num) + " where Book_id=" + Book_id+" and User_id="+user_id;
                        userDao.Update(sql1);
                        System.out.println(sql1);
                    }
                    //不存在就插入新数据
                    else {
                        String sql2="insert into user_to_book values("+user_id+","+Book_id+","+(0-Book_num)+")";
                        userDao.Update(sql2);
                        System.out.println(sql2);
                    }

                }
            }
        }
        //不存在 插入数据
        else {
            System.out.println("图书ID无效");
        }
    }
    //用户注册
    public void Register(){
        UserDao userDao=new UserDao();
        String sql_select="select* from t_user where User_id="+User_id;
        ArrayList<User_package> arrayList=userDao.Select(sql_select,new User_package());
        if (arrayList.size()!=0){
            System.out.println("ID已存在");
        }
        else {
        String sql="insert into t_user values("+User_id+",\""+super.getPersonName()+"\",\""+super.getPersonSex()+"\","+super.getPersonAge()+",\""+super.getPersontel()
                +"\",\""+User_pwd+"\")";
        userDao.Update(sql);
        }
    }
}
