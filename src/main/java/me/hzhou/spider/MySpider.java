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
				//��https://github.com/code4craft��ʼץ
				.addUrl("https://github.com/code4craft")
						//����Scheduler��ʹ��Redis������URL����
				.setScheduler(new RedisScheduler(pool))
						//����Pipeline���������json��ʽ���浽�ļ�
				.addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
						//����5���߳�ͬʱִ��
				.thread(5)
						//��������
				.run();
	}
}