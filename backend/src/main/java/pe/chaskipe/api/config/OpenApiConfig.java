package pe.chaskipe.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  OpenAPI chaskipeOpenApi() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Chaskipé API")
                .version("v1")
                .description(
                    "API REST para comunicación accesible mediante LSP, texto, voz y avatar."));
  }
}
