/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

/**
 * Description:
 *
 * @author hzhou
 */
public class Tester {

	private final Logger log = Logger.getLogger(getClass());

	@Test
	public void downloader() throws IOException {
		Document doc = Jsoup.connect("http://sexy.faceks.com/").userAgent("Mozilla").get();
		log.info(doc.toString());
	}
}