package com.sula.service;

import java.util.List;

import com.fengpei.ioc.AutoInstance;
import com.jfinal.plugin.activerecord.Record;

/**
 * 
 * Description : 
 * @author 冯沛
 * @date 2017年5月19日
 */
@AutoInstance
public interface LoginService {

	public Record login(String account,String pwd);
	
	/**
	 * 根据用户名,查询拥有权限的模块
	 * @return
	 */
	public List<Record> getModules(String roleId);

	/**
	 * 注册用户
	 * @param record 用户信息
	 * @return
	 */
	public int saveLoginUser(Record record);
}
