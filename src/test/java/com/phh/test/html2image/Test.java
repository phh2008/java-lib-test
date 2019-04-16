package com.phh.test.html2image;

import com.moodysalem.phantomjs.wrapper.PhantomJS;
import com.moodysalem.phantomjs.wrapper.RenderException;
import com.moodysalem.phantomjs.wrapper.beans.BannerInfo;
import com.moodysalem.phantomjs.wrapper.beans.Margin;
import com.moodysalem.phantomjs.wrapper.beans.PaperSize;
import com.moodysalem.phantomjs.wrapper.beans.PhantomJSOptions;
import com.moodysalem.phantomjs.wrapper.beans.RenderOptions;
import com.moodysalem.phantomjs.wrapper.beans.ViewportDimensions;
import com.moodysalem.phantomjs.wrapper.enums.RenderFormat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.html2image
 * @date 2019/4/15
 */
public class Test {

    /**
     * 生成pdf
     *
     * @throws IOException
     * @throws RenderException
     */
    @org.junit.Test
    public void test() throws IOException, RenderException {
        String url = "https://www.ufa.hk/mall/sto/product/detail/2770746332643328.html";
        InputStream html = new URL(url).openConnection().getInputStream();
        InputStream pdf = PhantomJS.render(null, html, PaperSize.Letter, ViewportDimensions.VIEW_1280_1024,
                Margin.ZERO, BannerInfo.EMPTY, BannerInfo.EMPTY, RenderFormat.PDF, 10000L, 100L);
        Path dest = Paths.get("ufa.pdf");
        Files.deleteIfExists(dest);
        Files.copy(pdf, dest);
    }

    @org.junit.Test
    public void test2() throws IOException, RenderException {
        //String url = "https://www.ufa.hk/mall/sto/product/detail/2770746332643328.html";
        String url = "https://www.ufa.hk/demand/list.html";
        InputStream html = new URL(url).openConnection().getInputStream();
        InputStream jpg = PhantomJS.render(null, html, PaperSize.Letter, ViewportDimensions.VIEW_1280_1024,
                Margin.ZERO, BannerInfo.EMPTY, BannerInfo.EMPTY, RenderFormat.JPEG, 10000L, 100L);
        Path dest = Paths.get("qq.jpg");
        Files.deleteIfExists(dest);
        Files.copy(jpg, dest);
    }

    @org.junit.Test
    public void test3() throws IOException, RenderException {
        InputStream html = this.getClass().getClassLoader().getResourceAsStream("test.html");
        InputStream jpg = PhantomJS.render(null, html, PaperSize.Letter, new ViewportDimensions(200, 80),
                Margin.ZERO, BannerInfo.EMPTY, BannerInfo.EMPTY, RenderFormat.JPEG, 10000L, 100L);
        Path dest = Paths.get("test.jpg");
        Files.deleteIfExists(dest);
        Files.copy(jpg, dest);
    }
}
