package com.luway.pikachu.core.worker;

import com.luway.pikachu.core.annotations.MatchUrl;
import com.luway.pikachu.core.annotations.WorkerType;
import com.luway.pikachu.core.pipeline.BasePipeline;
import com.luway.pikachu.core.worker.bean.BaseWorker;
import com.luway.pikachu.core.worker.bean.Target;

import java.util.List;
import java.util.Map;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 上午11:31 2018/8/28
 */

public class BathWorker extends BaseWorker implements Worker {

    public BathWorker() {
        super.setType(WorkerType.BATCH);
    }

    @Override
    public BathWorker addPipeline(BasePipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    @Override
    public Boolean validate() {
        if (pipeline == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public BathWorker cookies(Map<String, String> cookies) {
        super.setCookies(cookies);
        return this;
    }


    public BathWorker urlList(List<String> list) {
        super.setUrlList(list);
        return this;
    }

    public BathWorker attr(Map<String, Target> attr) {
        super.setAttr(attr);
        return this;
    }

    public BathWorker method(MatchUrl.Method method) {
        super.setMethod(method);
        return this;
    }

    @Override
    public String toString() {
        return "BathWorker{" +
                "method=" + super.getMethod() +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", attr=" + attr +
                ", cookies=" + super.getType() +
                ", pipeline=" + pipeline +
                ", loadJs=" + loadJs +
                '}';
    }
}
