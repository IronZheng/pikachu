import com.luway.pikachu.core.annotations.MatchUrl;
import com.luway.pikachu.core.engine.Pikachu;
import com.luway.pikachu.core.engine.impl.PikachuImpl;
import com.luway.pikachu.core.pipeline.BasePipeline;
import com.luway.pikachu.core.worker.BathWorker;
import com.luway.pikachu.core.worker.GeneralWorker;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 2:34 PM 2018/12/14
 */

public class JJ {

    public static void main(String[] args) throws IOException {
        Pikachu pikachu = new PikachuImpl("test")
                .setCoreNum(10)
                .setMaxThreadNum(20)
                .init();

        pikachu.putWork(new GeneralWorker("jj", JuejinTest.class)
                .addPipeline(new BasePipeline(JuejinTest.class) {
                    @Override
                    public void output(Map result, String url) {
                        System.out.println(result.get("title"));
                    }
                }));
        pikachu.start();

        //知乎热搜
//        Document document = pikachu.getConnect("https://www.zhihu.com/explore", MatchUrl.Method.GET);
//        Elements elements = document.select("#zh-recommend-list > div > h2 > a");



        Document document = pikachu.getConnect("  http://top.baidu.com/buzz?b=1&fr=20811", MatchUrl.Method.GET);
        Elements elements = document.select("#main > div.mainBody > div > table > tbody > tr");



        pikachu.stop();
    }
}
