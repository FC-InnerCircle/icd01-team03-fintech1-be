package incerpay.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// http://localhost:8080/swagger-ui/index.html
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swagger() {
        Info info = new Info();
        info.title("BFF Server for Payment Gateway")
                .version("1.0.0")
                .description("");

        return new OpenAPI().info(info)
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Authorization", new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)));
    }
}
