/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package me.hzhou.spider.model;

import com.jfinal.plugin.activerecord.Model;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Description:
 *
 * @author hzhou
 */
public class Image extends Model<Image> {

	private static final Logger log = Logger.getLogger(Image.class);

	public static final Image me = new Image();

	public void persist(@NotNull ImageExtract ie) {
		for (String url : ie.getImageUrls()) {
			try {
				new Image().setPost(ie.getUrl()).setUrl(url).save();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	public Image setPost(String post) {
		this.set("post", post);
		return this;
	}

	public Image setUrl(String url) {
		this.set("url", url);
		return this;
	}

	public String getPost() {
		return this.getStr("post");
	}

	public String getUrl() {
		return this.getStr("url");
	}

	public Integer getId() {
		return this.getInt("id");
	}
}