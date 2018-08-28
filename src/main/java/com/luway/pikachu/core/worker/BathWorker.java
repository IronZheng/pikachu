package com.luway.pikachu.core.worker;

import com.luway.pikachu.core.annotations.WorkerType;
import com.luway.pikachu.core.pipeline.BasePipeline;
import com.luway.pikachu.core.worker.bean.BaseWorker;

import java.util.Map;

/**
 * @author : iron
 * @version : 1.0.0
 * @date : 上午11:31 2018/8/28
 */

public class BathWorker extends BaseWorker implements Worker {

    public BathWorker() {
        this.type = WorkerType.BATCH;
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
    public Worker cookies(Map<String, String> cookies) {
        return null;
    }

    @Override
    public String toString() {
        return "BathWorker{" +
                "method=" + method +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", attr=" + attr +
                ", cookies=" + cookies +
                ", pipeline=" + pipeline +
                ", loadJs=" + loadJs +
                '}';
    }
}
