package com.sula.service.impl;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sula.service.ModuleService;

/**
 * 
 * Description : 
 * @author 冯沛
 * @date 2017年5月19日
 */
public class ModuleServiceImpl implements ModuleService {

	@Override
	public List<Record> getModules(int id) {
		String sql = " select * from shop_module where parent = ? order by orderno asc";
		List<Record> list = Db.find(sql,id);
		return list;
	}

}
