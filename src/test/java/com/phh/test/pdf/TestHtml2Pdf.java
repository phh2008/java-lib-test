package com.phh.test.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.itextpdf.tool.xml.css.CSS.Property.FONT;
import static com.itextpdf.tool.xml.html.HTML.Tag.HTML;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2019/11/10
 */
public class TestHtml2Pdf {

    @Test
    public void test1() throws FileNotFoundException {
        InputStream html = new FileInputStream("E:\\idea-phh\\my-github\\java-lib-test\\src\\test\\resources\\test.html");
        FileOutputStream out = new FileOutputStream("test.pdf");
        ITextPdfUtil.writeToOutputStreamAsPDF(html, out);
    }


    @Test
    public void test2() throws FileNotFoundException, DocumentException {

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test2.pdf"));
        document.open();
        document.add(new Paragraph("hello world"));
        document.close();
        writer.close();


    }

    @Test
    public void test3() throws IOException, DocumentException {

        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test3.pdf"));
        // step 3
        document.open();
        // step 4
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontImp.register("simsun.ttc");
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream("E:\\idea-phh\\my-github\\java-lib-test\\src\\test\\resources\\test.html"), null, Charset.forName("UTF-8"), fontImp);
        // step 5
        document.close();
    }

    @Test
    public void test4() throws IOException, DocumentException {
        FileInputStream file = new FileInputStream("E:\\idea-phh\\my-github\\java-lib-test\\src\\test\\resources\\test.html");
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test4.pdf"));
        // step 3
        document.open();
        // step 4
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontImp.register("simsun.ttc");
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                file, null, Charset.forName("UTF-8"), fontImp);
        // step 5
        document.close();
    }

}
