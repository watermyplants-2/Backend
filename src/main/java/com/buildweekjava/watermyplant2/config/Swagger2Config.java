package com.buildweekjava.watermyplant2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configures the default Swagger Documentation
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config
{

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                              .basePackage("com.buildweekjava.watermyplants2"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointsInfo());
    }


    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("Water My Plants")
                .description("Water My Plants back end")
                .contact(new Contact("Matias Iturbide",
                                     "http://www.fakesite.com",
                                     "Matiasiturbide@gmail.com"))
                .license("MIT")
                .licenseUrl("https://github.com/watermyplants-2/Backend/blob/master/LICENSE")
                .version("1.0.0")
                .build();
    }
}