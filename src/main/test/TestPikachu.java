import com.pikachu.core.worker.JavassistDynamicBean;

/**
 * @author zhenggm
 * @create 2018-04-24 17:15
 **/


public class TestPikachu {

    public static void main(String[] args) {
        JavassistDynamicBean jdb= new JavassistDynamicBean("test",TestBean.class);
        System.out.println(jdb);
    }
}
