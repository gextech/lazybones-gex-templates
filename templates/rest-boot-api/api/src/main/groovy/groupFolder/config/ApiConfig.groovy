package ${group}.config

import gex.jaxrs.ApiResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfig {

  @Bean
  ApiResponse apiResponse() {
    new ApiResponse()
  }

}
