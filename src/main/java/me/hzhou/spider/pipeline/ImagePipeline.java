/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package me.hzhou.spider.pipeline;

import me.hzhou.spider.model.Image;
import me.hzhou.spider.model.ImageExtract;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * Description:
 *
 * @author hzhou
 */
public class ImagePipeline implements PageModelPipeline<ImageExtract> {

	private static final Logger log = Logger.getLogger(ImagePipeline.class);

	public void process(ImageExtract imageExtract, Task task) {
		log.info(imageExtract);
		Image.me.persist(imageExtract);
	}
}