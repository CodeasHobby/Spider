/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package me.hzhou.spider.model;

import java.util.List;

import com.google.common.base.Objects;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Description:
 *
 * @author hzhou
 */
//@TargetUrl("http://sexy\\.faceks\\.com/post/\\w+")
@TargetUrl("http://www.zhuamei5.com/home.php\\?mod=space&uid=6&do=album&id=\\d+")
public class ZhuaMeiImageExtract implements AfterExtractor {

    @ExtractBy(value = "//li/a/img/@src")
    private List<String> imageUrls;

    @ExtractByUrl
    private String url = "";

    @Override
    public void afterProcess(Page page) {

    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ZhuaMeiImageExtract)) {
            return false;
        }
        ZhuaMeiImageExtract that = (ZhuaMeiImageExtract) o;
        return Objects.equal(getImageUrls(), that.getImageUrls()) && Objects.equal(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getImageUrls(), getUrl());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("imageUrls", imageUrls).add("url", url).toString();
    }
}