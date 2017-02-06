package com.lessons.db.phonebook;

import com.lessons.db.phonebook.db.Store;
import com.lessons.db.phonebook.db.file.FileStore;
import com.lessons.db.phonebook.db.inmemory.MemoryStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Lazy
    public Store store(@Value("${store.type}") String storeType) {
        switch (storeType) {
            case "memory":
                return new MemoryStore();
            case "file":
                return new FileStore();
            default:
                throw new IllegalArgumentException(String.format("property store.type has incorrect value (%s)", storeType));
        }
    }

    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
        //.apiInfo(apiInfo());
    }

}
