package take.home.cook.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {



    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("take.home.cook.api.resource"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(basicAuth() , apiKey() , token()))
                .securityContexts(Arrays.asList(securityContext()));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Take Home Cook API")
                .version("v1")
//                .description("ACAP is the acronym of Axis Camera Application Platform which is a platform in Axis' " +
//                        "devices that allows third-party applications to be installed. It is also the name of the service" +
//                        " offered by Axis to enable copy protection of the applications running on the platform. This service is free " +
//                        "to use for Axis Software Vendors (ADPs) and other companies developing applications. The ACAP service is accessed " +
//                        " through the Partner Pages for partners and through www.axis.com where customers can register license keys. Partners " +
//                        " can within this service register and manage their applications, license codes and license keys.")
//                .contact(new Contact("Pernilla Allansson", null, "Pernilla.Allansson@axis.com"))
                .build();
    }

    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey("apiKey","apiKey" , ApiKeyVehicle.HEADER.getValue() );
    }

    @Bean
    public SecurityScheme token() {
        return new ApiKey("token", "token" , ApiKeyVehicle.HEADER.getValue() );
    }

    @Bean
    public SecurityScheme basicAuth() {
        return new BasicAuth("basic" , Arrays.asList( new StringVendorExtension("basic", ApiKeyVehicle.HEADER.getValue() )));
    }

    @Bean
    public SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return Arrays.asList(
                new SecurityReference("apiKey", authorizationScopes)
                , new SecurityReference("token" , authorizationScopes)
                , new SecurityReference("basic" , authorizationScopes)
        );
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }



    private class Token extends SecurityScheme {

        private final String keyname;
        private final String passAs;

        public Token(String name, String keyname, String passAs) {
            this(name, keyname, passAs, new ArrayList());
        }

        public Token(String name, String keyname, String passAs, List<VendorExtension> vendorExtensions) {
            super(name, "token");
            this.keyname = keyname;
            this.passAs = passAs;
            this.addValidVendorExtensions(vendorExtensions);
        }

        public String getKeyname() {
            return this.keyname;
        }

        public String getPassAs() {
            return this.passAs;
        }
    }
}
