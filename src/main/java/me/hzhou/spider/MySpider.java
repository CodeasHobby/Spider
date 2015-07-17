/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package me.hzhou.spider;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * Description:
 *
 * @author hzhou
 */
public class MySpider {

	public static void main(String[] args) {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), Prop.INSTANCE.get("redis.server"), Protocol.DEFAULT_PORT,
				Protocol.DEFAULT_TIMEOUT, Prop.INSTANCE.get("redis.auth"));
		
		Spider.create(new GithubRepoPageProcessor())
				//从https://github.com/code4craft开始抓
				.addUrl("https://github.com/code4craft")
						//设置Scheduler，使用Redis来管理URL队列
				.setScheduler(new RedisScheduler(pool))
						//设置Pipeline，将结果以json方式保存到文件
				.addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
						//开启5个线程同时执行
				.thread(5)
						//启动爬虫
				.run();
	}
}