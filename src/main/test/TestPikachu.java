import com.luway.pikachu.core.annotations.MathUrl;
import com.luway.pikachu.core.engine.Pikachu;
import com.luway.pikachu.core.engine.impl.PikachuImpl;
import com.luway.pikachu.core.pipeline.BasePipeline;
import com.luway.pikachu.core.worker.BathWorker;
import com.luway.pikachu.core.worker.GeneralWorker;
import com.luway.pikachu.core.worker.bean.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhenggm
 * @create 2018-04-24 17:15
 **/


public class TestPikachu {

    public static void main(String[] args) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("ll", "118172");
        map.put("bid", "-aa5HiorjRg");
        map.put("_vwo_uuid_v2", "DC8E907471DA947421527AF0C0DF2C1F9|4d65ae4cce5c51225165ec2e92c4145e");
        map.put(" douban-fav-remind", "1");
        map.put("__utmv", "30149280.8344");
        map.put("gr_user_id", "edbe46fd-3869-4ffb-b3c0-efd4e5b0a8f1");
        map.put("ue", "389093062@qq.com");
        map.put("ldbcl2l", "83440468:F0SngCd1yt4");
        map.put("ck", "QiyY");
        map.put("frodotk", "44c013c2eb58277cce403b48c2087674");
        map.put("__utma", "30149280.179897845.1532763395.1535086972.1535511933.16");
        map.put("ll", "118172");


//        Pikachu pikachu = new PikachuImpl("test")
//                .setCoreNum(5)
//                .setMaxThreadNum(20)
//                .init()
//                .regist(new GeneralWorker("test", TestBean.class)
//                        .cookies(map)
//                        .addPipeline(new TestPipeline(new TestBean())));

        List<String> urlList = new ArrayList<>();

        Map<String, Target> attr = new HashMap<>();
        attr.put("title", new Target("title", "List",
                "body > div.content > div.leftContent > ul > li > div.info.clear > div.title > a", null));

        int i = 1;
        while (i < 100) {
            String url = "https://hz.lianjia.com/ershoufang/xihu/pg" + i + "/";
            urlList.add(url);
            i++;
        }

        Pikachu pikachu = new PikachuImpl("test")
                .setCoreNum(10)
                .setMaxThreadNum(20)
                .init()
                .regist(new BathWorker()
                        .method(MathUrl.Method.GET)
                        .urlList(urlList)
                        .attr(attr)
                        .addPipeline(new TestPipeline(new TestBean())));


        pikachu.start();

//        pikachu.stopAfterTime(30L);
//        pikachu.regist(new GeneralWorker("1", TestBean.class).addPipeline(new BasePipeline(TestBean.class) {
//            @Override
//            public void output(Map result) {
//                System.out.println(result);
//            }
//        }));
    }

}
