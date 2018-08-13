import com.luway.pikachu.core.engine.Pikachu;
import com.luway.pikachu.core.worker.Worker;


/**
 * @author zhenggm
 * @create 2018-04-24 17:15
 **/


public class TestPikachu {

    public static void main(String[] args) throws Exception {

        Pikachu pikachu = new Pikachu("test")
                .setCoreNum(5)
                .setMaxThreadNum(20)
                .init()
                .regist(new Worker("test", TestBean.class)
                        .addPipeline(new TestPipeline(new TestBean())));

        pikachu.start();
//        pikachu.stop();
    }

}
