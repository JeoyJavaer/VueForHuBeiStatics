package gaea.hubeimobile.statics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCrosConfig {

    @Bean
    public WebMvcConfigurer crosConfigurer(){
       return  new WebMvcConfigurer() {
           @Override
           public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/*")
                       .allowedOrigins("*")
                       .allowCredentials(true)
                       .allowedMethods("GET","POST","DELETE","PUT")
                       .maxAge(3600);
           }
       };
    }
}
