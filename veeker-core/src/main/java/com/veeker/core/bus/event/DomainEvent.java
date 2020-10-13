package com.veeker.core.bus.event;

import java.util.Date;

/**
 * @author ：qiaoliang
 */
public class DomainEvent {
    /**创建时间**/
    private final Date curredTime;

    public DomainEvent() {
        this.curredTime = new Date();
    }

    public Date getCurredTime() {
        return curredTime;
    }
}
