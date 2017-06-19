package com.sula.controller;

import com.fengpei.ioc.Controller;

/**
 * 接口编写地址 访问路径 http://ip:port/sula/methodName
 * Description : 
 * @author 冯沛
 * @date 2017年6月19日
 */
public class IndexController extends Controller {
	/**
	 * 默认跳转路径
	 */
	public void index() {
		render("index.jsp");
	}
	
	public void login() {
		
	}
}
