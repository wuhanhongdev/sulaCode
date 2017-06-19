package com.sula.service;

import com.fengpei.ioc.AutoInstance;
import com.jfinal.plugin.activerecord.Record;

@AutoInstance
public interface SystemService {

	public Record getSysObject();
	public String modifySysObject(Record o);
}
