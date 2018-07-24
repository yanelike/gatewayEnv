package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import hello.filters.pre.QueryParamPortPreFilter;
import hello.filters.pre.SimpleFilter;

@EnableZuulProxy
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		AopAutoConfiguration.class
} )
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public SimpleFilter simpleFilter() {
    return new SimpleFilter();
  }
  
  @Bean 
  public QueryParamPortPreFilter simpleRouter(){
	  return new QueryParamPortPreFilter();
  }
}
