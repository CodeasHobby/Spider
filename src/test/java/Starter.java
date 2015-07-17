/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */

import java.io.IOException;

import com.jfinal.ext.test.ControllerTestCase;
import me.hzhou.spider.Application;
import me.hzhou.spider.MySpider;
import me.hzhou.spider.download.JsoupParser;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Description:
 *
 * @author hzhou
 */
public class Starter extends ControllerTestCase<Application> {

	private static final Logger log = Logger.getLogger(Starter.class);

	@Test
	public void getImageUrls() {
		MySpider.process();
	}

	@Test
	public void getImageUrls2() throws IOException {
		JsoupParser jp = new JsoupParser("http://sexy.faceks.com");
		jp.process();
	}
}