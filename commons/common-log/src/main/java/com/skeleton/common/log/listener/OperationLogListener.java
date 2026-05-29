package com.skeleton.common.log.listener;

import com.skeleton.common.log.event.OperationLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OperationLogListener {

    @Async
    @EventListener
    public void handleLog(OperationLogEvent event) {
        log.info("操作日志: module={}, action={}, operator={}, cost={}ms",
                event.getLog().getModule(), event.getLog().getAction(),
                event.getLog().getOperator(), event.getLog().getCostTime());
    }
}
