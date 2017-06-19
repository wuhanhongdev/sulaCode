package com.sula.service.impl;

import java.util.Date;

import com.sula.util.Status;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sula.service.SystemService;

public class SystemServiceImpl implements SystemService {

	@Override
	public Record getSysObject() {
		//System.out.println("----010----");
		Record obj = Db.findFirst("select * from shop_system");
		//System.out.println(obj.getStr("logo"));
		obj.set("logo", "http://localhost:8080/dsplat/uploadImg/set/"+obj.getStr("logo"));
		//System.out.println(obj.getStr("title"));
		return obj;
	}

	@Override
	public String modifySysObject(Record o) {
		Record obj = Db.findFirst("select * from shop_system");
		String returnStr = "";
		if (obj == null) {
			// 新增
			o.set("create_date", new Date());
			boolean flag = Db.save("shop_system", o);
			if (flag) {
				returnStr = Status.message_success;
			} else {
				returnStr = Status.message_fail;
			}
		} else {
			// 修改
			o.set("id", obj.getInt("id"));
			o.set("modify_date", new Date());
			boolean flag = Db.update("shop_system", o);
			//boolean flag = Db.update("shop_system", id, o);
			if (flag) {
				returnStr = Status.message_success;
			} else {
				returnStr = Status.message_fail;
			}
		}
		return returnStr;
	}

}
