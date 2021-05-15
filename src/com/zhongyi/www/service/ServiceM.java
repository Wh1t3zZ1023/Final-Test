package com.zhongyi.www.service;


import com.zhongyi.www.dao.AddM;
import com.zhongyi.www.dao.SeekExist;
import com.zhongyi.www.dao.Updatepwd;

/**
 * @author Wh1t3zZ
 */
public class ServiceM {

	 // 添加管理员

       public Boolean addM(String name,String pwd){
		   //判断该管理员是否已添加
    	   Boolean bool=new SeekExist().seekExist(name,"管理员");
    	   if(bool){
    		   bool=false;
    	   }
    	   else{
			   //添加管理员
    		   bool=new AddM().addM(name, pwd);
    	   }    	    
    	   return bool;
       }


	  //修改密码

	public String getUpdatePwd(String userSort,String username,String pwd){
	    	Updatepwd ups=new Updatepwd();
	    	return ups.update(userSort, username, pwd);

	   }
       
}
