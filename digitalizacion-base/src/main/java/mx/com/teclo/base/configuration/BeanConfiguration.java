package mx.com.teclo.base.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(value={"mx.com.teclo"})
public class BeanConfiguration {
 
	@Bean("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
