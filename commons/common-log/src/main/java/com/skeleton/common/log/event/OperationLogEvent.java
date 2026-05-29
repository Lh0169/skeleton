package com.skeleton.common.log.event;

import com.skeleton.common.log.model.OperationLogModel;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OperationLogEvent extends ApplicationEvent {
    private final OperationLogModel log;

    public OperationLogEvent(Object source, OperationLogModel log) {
        super(source);
        this.log = log;
    }
}
