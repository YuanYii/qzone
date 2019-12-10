package top.yuanyii.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//让Spring来加载该类
@Configuration
//通过EnableSwagger2注解来启用Swagger2
@EnableSwagger2
public class SwaggerConfig {

//    创建Docket的bean对象
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
//                用来创建Api的基本信息,这些信息会展示在文档页面
                .apiInfo(apiInfo())
//                返回一个ApiSelectorBuilder实例来控制哪些接口暴露给Swagger来展现
                .select()
//                配置error
//                对所有api进行监控
                .apis(RequestHandlerSelectors.any())
//                不显示错误的接口地址
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
//                指定包,swagger会扫描该报下所有controller定义的API,并产生文档内容,被@ApiIgnore指定的请求会被忽略
                .apis(RequestHandlerSelectors.basePackage("top.yuanyii.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("元一的swagger测试文档")
                .description("你好! 友好的Swagger2!")
                .termsOfServiceUrl("http://yuanyii.top")
                .version("1.0")
                .contact(new Contact("zhangsan","http://yuanyii.top","233171887@qq.com"))
                .build();
    }
}
