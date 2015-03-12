package ${group}.config

import gex.jaxrs.ApiResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.MessageSource
import org.springframework.context.support.ResourceBundleMessageSource


@Configuration
class ApiConfig {

  @Bean
  ApiResponse apiResponse() {
    new ApiResponse()
  }


  @Bean
  MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

}
