package br.com.formalizacaobackoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableFeignClients
@EnableRetry
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class FormalizacaobackofficeApplication {

//    @Bean
//    public AuditorAware<String> auditorAware() {
//        return new AuditorAwareImpl();
//    }

    public static void main(String[] args) {
        SpringApplication.run(FormalizacaobackofficeApplication.class, args);
    }
}
