import com.pikachu.core.engine.Pikachu;
import com.pikachu.core.worker.DynamicBean;
import com.pikachu.core.worker.Worker;


import java.io.IOException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * @author zhenggm
 * @create 2018-04-24 17:15
 **/


public class TestPikachu {

    public static void main(String[] args) throws Exception {

        new Pikachu("test")
                .init()
                .regist(new Worker("test", TestBean.class)
                        .addPipeline(new TestPipeline(new TestBean())))
                .start();

    }

}
