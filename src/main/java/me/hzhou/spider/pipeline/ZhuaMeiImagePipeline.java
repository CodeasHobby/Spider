/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package me.hzhou.spider.pipeline;

import me.hzhou.spider.model.ZhuaMeiImageExtract;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Description:
 *
 * @author hzhou
 */
public class ZhuaMeiImagePipeline implements PageModelPipeline<ZhuaMeiImageExtract> {

    private static final Logger log = Logger.getLogger(ZhuaMeiImagePipeline.class);

    public void process(ZhuaMeiImageExtract imageExtract, Task task) {
        //log.info(imageExtract);
        //Image.me.persist(imageExtract);

        for (String image : imageExtract.getImageUrls()) {
            image = getUrl(image);
            log.info(image);
            try {
                FileUtils.copyURLToFile(new URL(image), new File("download" + File.separator + System.nanoTime() + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getUrl(String image) {
        String newUrl = image.replace(".thumb.jpg", "");
        if (!newUrl.startsWith("http")) {
            newUrl = "http://www.zhuamei5.com/" + newUrl;
        }
        return newUrl;
    }

}