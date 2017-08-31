package cn.wantedonline.usercenter.service;

import cn.wantedonline.common.utils.SnowflakeIDWorker;
import org.springframework.stereotype.Service;

/**
 * Created by louiswang on 17/8/31.
 */
@Service
public class UserIdGenerator {

    private static final int workerId = 1;

    private SnowflakeIDWorker worker = new SnowflakeIDWorker(workerId);

    public long nextId() {
        return worker.nextId();
    }
}
