package com.veeker.core.bus.listeners;


import com.veeker.core.bus.event.DomainEvent;

/**
 * 触发接口限制
 *
 * @author ：qiaoliang
 */
public interface DomainEventPublisher<T extends DomainEvent> {

    String identify();

    /**
     * 注册
     *
     * @author ：qiaoliang
     * @param listener : 监听
     */
    void register(Object listener);

    /**
     * 同步消息
     *
     * @author ：qiaoliang
     * @param event : 消息模型
     */
    void publish(T event);
    /**
     * 异步消息
     *
     * @author ：qiaoliang
     * @param event : 消息模型
     */
    void asyncPublish(T event);

}
