package com.eduardo.rdguez.zssn.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

  @Bean
  fun openAPI(): OpenAPI? {
    return OpenAPI()
      .info(
        Info()
          .title("ZSSN (Zombie Survival Social Network) REST API")
          .version("1.0.0")
          .description("ZSSN (Zombie Survival Social Network) REST API v1.0.0")
      )
  }

}