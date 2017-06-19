package com.sula.config;

import com.sula.controller.AppIndexController;
import com.sula.controller.IndexController;
import com.sula.interceptor.Md5Interceptor;
import com.sula.util.ConfigUtils;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

/**
 * jfinal 配置项
 * @author jcms
 * date time : 2016年5月3日 上午8:59:40
 */
public class Config extends JFinalConfig {
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用getProperty(...)获取参数
		loadPropertyFile("jdbc_config.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
		// 设置视图类型为Jsp，否则默认为FreeMarker ，可以不配置
		me.setViewType(ViewType.JSP); 
		// 修改默认保存文件路径
		me.setUploadedFileSaveDirectory(ConfigUtils.getProperty("file.path"));
	}
	
	/**
	 *配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/",IndexController.class);
		me.add("/appserver/index", AppIndexController.class);
	}
	
	/**
	 *  配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim(),getProperty("driverClassName"));
		me.add(c3p0Plugin);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		boolean devMode = getPropertyToBoolean("devMode", false);
		if(devMode)
			arp.setShowSql(true);
		me.add(arp);
	}
	public void afterJFinalStart(){
		 System.out.println("数据库连接成功了!");
         // 定时任务
	}
	/**
	 * 配置全局拦截
	 */
	public void configInterceptor(Interceptors me) {
		//me.add(new Md5Interceptor());
	}
	
	/**
	 * 配置处理
	 */
	public void configHandler(Handlers me) {
		// 如果使用freemarker，解决路径问题，页面获取路径时，直接输入${ path }
		me.add(new ContextPathHandler("path"));
//		me.add(new AccessControlAllowOrigin());
	}
}
