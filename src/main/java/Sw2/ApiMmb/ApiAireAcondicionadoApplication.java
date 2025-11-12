package Sw2.ApiMmb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class ApiAireAcondicionadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAireAcondicionadoApplication.class, args);
	}
	@Bean //Documentacion
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Demo")
                        .version("1.0.0")
                        .description("Documentacion de la API Demo")
                        );
    }
}


	