package com.sula.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

/**
 * 解决跨域访问，安全性降低
 * @author jcms
 * date time : 2016年6月1日 下午4:12:07
 */
public class AccessControlAllowOrigin extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		response.setHeader("Access-Control-Allow-Origin", "*");// cors解决跨域
		nextHandler.handle(target, request, response, isHandled);
	}


}
