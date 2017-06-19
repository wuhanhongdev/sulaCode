package com.sula.service;

import java.util.List;

import com.fengpei.ioc.AutoInstance;
import com.jfinal.plugin.activerecord.Record;

/**
 * 模块管理
 * Description : 
 * @author 冯沛
 * @date 2017年5月19日
 */
@AutoInstance
public interface ModuleService {
	
	public List<Record> getModules(int id);
	
}
