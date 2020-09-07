package Dao;


import packaging.Package;

import java.util.ArrayList;


public class AdminDao  extends Dao {
    @Override
    public boolean Update(String Sql) {
        return super.Update(Sql);
    }

    @Override
    public ArrayList Select(String Sql, Package pa) {
        return super.Select(Sql, pa);
    }
}
