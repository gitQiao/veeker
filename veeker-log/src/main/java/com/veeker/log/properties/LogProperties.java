package com.veeker.log.properties;

import com.veeker.log.enums.LogLevelType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：qiaoliang
 */
@ConfigurationProperties(prefix = "log")
public class LogProperties {
    /**是否启用日志输出,生产环境建议关闭**/
    private boolean enabled = false;
    /**日志级别**/
    private String level = LogLevelType.DEBUG.toString();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getLevel() {
        return level.toUpperCase();
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
