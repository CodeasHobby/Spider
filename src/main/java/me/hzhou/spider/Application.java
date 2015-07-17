/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package me.hzhou.spider;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.druid.DruidPlugin;
import me.hzhou.spider.model.Image;

/**
 * Description:
 *
 * @author hzhou
 */
public class Application extends JFinalConfig {

	public Application() {
		PropKit.use("app.properties");
	}

	@Override
	public void configConstant(Constants me) {

	}

	@Override
	public void configRoute(Routes me) {

	}

	@Override
	public void configPlugin(Plugins me) {

		DruidPlugin ds = new DruidPlugin(PropKit.get("mysql.url"), PropKit.get("mysql.user"),
				PropKit.get("mysql.password"));
		me.add(ds);
		ActiveRecordPlugin arp = new ActiveRecordPlugin("spider", ds);
		arp.setShowSql(true);
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		me.add(arp);
		// Table Mapping
		arp.addMapping("images", Image.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {

	}

	@Override
	public void configHandler(Handlers me) {

	}
}