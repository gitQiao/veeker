package com.veeker.swagger.config;

import com.veeker.swagger.properties.Swagger2Properties;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

/**
 * @author ：qiaoliang
 * @date ：2021-03-15
 */
@Configuration
@EnableConfigurationProperties({Swagger2Properties.class})
@ConditionalOnProperty(prefix = "swagger", name = "enabled", havingValue = "true")
@Import({Swagger2DocumentationConfiguration.class})
public class SwaggerAutoConfiguration {

  private final Swagger2Properties swagger2Properties;

  public SwaggerAutoConfiguration(Swagger2Properties swagger2Properties) {
    this.swagger2Properties = swagger2Properties;
  }

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .enable(swagger2Properties.isEnabled())
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(swagger2Properties.getTitle())
        .description(swagger2Properties.getDescription())
        .version(swagger2Properties.getVersion())
        .contact(new Contact(swagger2Properties.getName(), swagger2Properties.getUrl()
            , swagger2Properties.getEmail()))
        .build();
  }
}
