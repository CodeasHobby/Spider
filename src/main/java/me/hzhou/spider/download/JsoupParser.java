/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package me.hzhou.spider.download;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;
import me.hzhou.spider.model.Image;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Description:
 *
 * @author hzhou
 */
public class JsoupParser {

	private final Logger log = Logger.getLogger(getClass());

	private String baseUrl = "http://sexy.faceks.com";

	public JsoupParser(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<String> getPostUrls() throws IOException {
		Document html = getHtml(baseUrl);
		Elements elements = html.select(".pic a.img");

		List<String> result = Lists.newArrayList();
		for (Element e : elements) {
			result.add(e.attr("href"));
		}
		return result;
	}

	public void process() throws IOException {
		List<String> posts = getPostUrls();
		for (String post : posts) {
			log.info(post);
			Image.me.persist(post, getImageUrls(post));
		}
	}

	private List<String> getImageUrls(String postUrl) throws IOException {
		Document html = getHtml(postUrl);
		Elements elements = html.select(".imgclasstag");
		List<String> result = Lists.newArrayList();
		for (Element e : elements) {
			result.add(e.attr("bigimgsrc").replaceAll("(\\?|&)[\\w=]+", ""));
		}
		return result;
	}

	private Document getHtml(String baseUrl) throws IOException {
		return Jsoup.connect(baseUrl).get();
	}

	public static void main(String[] args) throws IOException {
		JsoupParser jp = new JsoupParser("http://sexy.faceks.com");
		jp.process();
	}
}