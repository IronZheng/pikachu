package com.pikachu.core.worker;

import com.pikachu.core.annotations.CssPath;
import com.pikachu.core.annotations.MathUrl;
import com.pikachu.core.exception.SimpleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author zhenggm
 * @create 2018-03-01 上午 11:18
 **/


public class JavassistDynamicBean extends DynamicBean {
    private final static Logger log = LoggerFactory.getLogger(JavassistDynamicBean.class);
    private MathUrl.Method method;

    public JavassistDynamicBean(String id, Class<?> bean) {
        this.id = id;
        if (bean == null) {
            throw new RuntimeException("[error] class is null");
        }
        load(bean);
    }

    private JavassistDynamicBean load(Class<?> bean) {
        MathUrl u = bean.getAnnotation(MathUrl.class);
        log.debug(bean.getName() + "is load");
        this.url = u.url();
        if (this.url == null) {
            throw new RuntimeException("[error] url can not be null");
        }
        this.method = u.method();
        return attr(bean);
    }

    public JavassistDynamicBean attr(Class<?> bean) {
        attr = new HashMap<>();
        try {
            Field[] fields = bean.getDeclaredFields();
            for (Field field : fields) {
                boolean fieldHasAnno = field.isAnnotationPresent(CssPath.class);
                if (fieldHasAnno) {
                    CssPath cssPath = field.getAnnotation(CssPath.class);
                    //输出注解属性
                    Target t = new Target(field.getName(), field.getType().toString(), cssPath.selector());
                    attr.put(field.getName(), t);
                    log.debug(field.getName() + ":" + field.getType().toString() + ":" + cssPath.selector());
                }
            }
        } catch (Exception e) {
            log.error("attr error", e);
            throw new SimpleException(e);
        }
        return this;
    }


    @Override
    public void start() {

    }

    @Override
    public String toString() {
        return "JavassistDynamicBean{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", method=" + method +
                ", attr=" + attr +
                '}';
    }
}
