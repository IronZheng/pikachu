import com.pikachu.core.worker.DynamicBean;
import com.pikachu.core.worker.Worker;

/**
 * @author zhenggm
 * @create 2018-04-24 17:15
 **/


public class TestPikachu {

    public static void main(String[] args) {
        Worker jdb= new Worker("test",TestBean.class);
        System.out.println(jdb);
    }
}
