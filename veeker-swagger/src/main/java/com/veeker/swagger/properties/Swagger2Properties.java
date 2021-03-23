package com.veeker.swagger.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：qiaoliang
 * @date ：2021-03-15
 */
@ConfigurationProperties(prefix = "swagger")
public class Swagger2Properties {
  /**是否启用swagger,生产环境建议关闭**/
  private boolean enabled = false;
  /**文档标题**/
  private String title = "接口文档";
  /**文档描述**/
  private String description = "接口文档描述";

  /**作者**/
  private String name = "qiaoliang";

  /**作者联系地址**/
  private String url = "http://localhost:8080/doc.html";

  /**作者联系email**/
  private String email = "1224999091@qq.com";

  /**版本**/
  private String version = "1.0.0";

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
