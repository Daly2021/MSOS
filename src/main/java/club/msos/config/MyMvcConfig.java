package club.msos.config;

import club.msos.interceptors.jwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/homepage" );

        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        WebMvcConfigurer.super.addViewControllers( registry );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new jwtInterceptor())
            .addPathPatterns("/user/**")
            .addPathPatterns("/html/index")
            .addPathPatterns("/toUpdateUser")
             .addPathPatterns("/desktop")
             .addPathPatterns("/updatePassword")
            .excludePathPatterns("/user/toLogin","/user/insertUser","/user/forget","/static/**");
    }
}
