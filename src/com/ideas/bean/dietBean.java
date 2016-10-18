package com.ideas.bean;


import java.lang.*;
import java.util.*;
import java.sql.*;

public class dietBean extends ParentBean
{
    //取得数据表中的某一条记录
    public Vector getOneData() throws Exception
    {
        String sql = " select * from diet";
        return getResultSetData(selectRecord(sql));
    }

    public int mod(Hashtable hash) throws Exception
    {
        String diet[][]  = new String[8][6];

        Vector vect =new Vector();
        vect.add("diet");
        for(int i=1;i<=7;i++)
        {
            for(int j=1;j<=5;j++)
            {
                diet[i][j] = ds.toString((String)hash.get("diet"+i+""+j));
                vect.add(addVector("m"+i+""+j,diet[i][j],"CHAR"));
            }
        }
        vect.add("");

        return updateRecord(vect);
    }


}