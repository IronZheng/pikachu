package com.luway.pikachu.jobs;

import com.luway.pikachu.common.SnowFlakeUtil;
import com.luway.pikachu.core.engine.Pikachu;
import com.luway.pikachu.core.worker.GeneralWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author : iron
 * @version : 1.0.0
 * @date : 下午3:48 2018/8/22
 */

public class PikachuJobManage {

    private ScheduledExecutorService taskPool;
    private Pikachu pikachu;
    private final static Logger logger = LoggerFactory.getLogger(PikachuJobManage.class);

    public PikachuJobManage(Pikachu pikachu) {
        this.pikachu = pikachu;
        this.taskPool = new ScheduledThreadPoolExecutor(2);
    }

    /**
     * 注册定时任务
     *
     * @param worker
     * @param startTime
     * @param space
     * @param unit
     * @return
     */
    public boolean regiest(GeneralWorker worker, Long startTime, Long space, TimeUnit unit) throws Exception {
        try {
            String id = SnowFlakeUtil.getId();
            logger.info("注册定时任务，id为【{}}】", id);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    pikachu.regist(worker);
                }
            };

            taskPool.scheduleAtFixedRate(task, startTime, space, unit);
            return true;
        } catch (Exception e) {
            throw new Exception("regiest error", e);
        }
    }


}
