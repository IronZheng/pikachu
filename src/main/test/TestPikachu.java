import com.pikachu.core.engine.Pikachu;
import com.pikachu.core.worker.DynamicBean;
import com.pikachu.core.worker.Worker;

/**
 * @author zhenggm
 * @create 2018-04-24 17:15
 **/


public class TestPikachu {

    public static void main(String[] args) {

        new Pikachu("test")
                .init()
                .regist(new Worker("test", TestBean.class)
                        .addPipeline(new TestPipeline(new TestBean())))
                .start();

    }
}
