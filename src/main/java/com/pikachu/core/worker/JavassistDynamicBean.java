package com.pikachu.core.worker;

import com.pikachu.core.annotations.CssPath;
import com.pikachu.core.annotations.MathUrl;
import com.pikachu.core.exception.SimpleException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * @author zhenggm
 * @create 2018-03-01 上午 11:18
 **/


public class JavassistDynamicBean {
    private static Log log = LogFactory.getLog(JavassistDynamicBean.class);
    private String id;
    private String url;
    private MathUrl.Method method;
    private Map<String, Object> attr;

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
        return this;
    }

    private JavassistDynamicBean attr(Class<?> bean) {
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
            log.error(e);
            throw new SimpleException(e);
        }
        return this;
    }

    private void regist(JavassistDynamicBean bean) {

    }
}

class Target {
    private String name;
    private String type;
    private String selector;

    public Target(String name, String type, String selector) {
        this.name = name;
        this.type = type;
        this.selector = selector;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}