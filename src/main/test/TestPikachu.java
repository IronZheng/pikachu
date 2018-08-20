import com.luway.pikachu.core.engine.Pikachu;
import com.luway.pikachu.core.engine.impl.PikachuImpl;
import com.luway.pikachu.core.pipeline.BasePipeline;
import com.luway.pikachu.core.worker.Worker;

import java.util.Map;


/**
 * @author zhenggm
 * @create 2018-04-24 17:15
 **/


public class TestPikachu {

    public static void main(String[] args) throws Exception {

        Pikachu pikachu = new PikachuImpl("test")
                .setCoreNum(5)
                .setMaxThreadNum(20)
                .init()
                .regist(new Worker("test", TestBean.class)
                        .addPipeline(new TestPipeline(new TestBean())));

        pikachu.start();
        pikachu.stopAfterTime(30L);
        pikachu.regist(new Worker("1",TestBean.class).addPipeline(new BasePipeline(TestBean.class) {
            @Override
            public void output(Map result) {

            }
        }));
    }

}
