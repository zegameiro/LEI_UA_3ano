package deti.tqs.backend.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {

    @Bean
    GroupedOpenApi publicApi() {
      return GroupedOpenApi.builder()
          .group("public-api")
          .pathsToMatch("/api/**")
          .build();
    }

    @Bean
    OpenAPI customOpenAPI() {
      return new OpenAPI()
        .info(new Info().title("Bus Wise Service")
          .description("A simple API to manage bus trips reservations.")
          .version("v1.0")
          .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}