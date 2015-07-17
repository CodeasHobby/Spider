/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package me.hzhou.spider;

import com.jfinal.kit.PropKit;
import me.hzhou.spider.model.ImageExtract;
import me.hzhou.spider.pipeline.ImagePipeline;
import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * Description:
 *
 * @author hzhou
 */
public class MySpider {

	private static final Logger log = Logger.getLogger(MySpider.class);

	public static void process() {
		log.debug(PropKit.get("redis.server"));

		JedisPool pool = new JedisPool(new JedisPoolConfig(), PropKit.get("redis.server"), Protocol.DEFAULT_PORT,
				Protocol.DEFAULT_TIMEOUT, PropKit.get("redis.auth"));

		OOSpider.create(Site.me().setUserAgent(PropKit.get("user-agent")), new ImagePipeline(), ImageExtract.class)
				.addUrl("http://sexy.faceks.com").setScheduler(new RedisScheduler(pool)).thread(5).run();
	}
}