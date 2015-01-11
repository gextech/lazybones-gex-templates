package ${group}.config

import gex.jaxrs.provider.DomainClassValidationExceptionMapper
import gex.jaxrs.provider.GenericExceptionExceptionMapper
import gex.jaxrs.provider.ObjectNotFoundExceptionExceptionMapper
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.server.spring.scope.RequestContextFilter
import org.glassfish.jersey.servlet.ServletContainer
import org.glassfish.jersey.servlet.ServletProperties
import org.glassfish.jersey.media.multipart.MultiPartFeature
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppJerseyConfig {

  @Bean
  ServletRegistrationBean jerseyServletResourcesV1() {
    ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainerResourcesV1(), "/v1/*")
    registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfigResourcesV1.class.getName())
    registration
  }

  @Bean
  ServletRegistrationBean jerseyServletResourcesV2() {
    ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainerResourcesV2(), "/v2/*")
    registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfigResourcesV2.class.getName())
    registration
  }

  static class ServletContainerResourcesV1 extends ServletContainer {
  }

  static class JerseyConfigResourcesV1 extends ResourceConfig {

    JerseyConfigResourcesV1() {
      register(RequestContextFilter)
      register(GsonProvider)
      register(DomainClassValidationExceptionMapper)
      register(ObjectNotFoundExceptionExceptionMapper)
      register(GenericExceptionExceptionMapper)
      register(MultiPartFeature)
      packages("${group}.restv1")
    }
  }

  static class ServletContainerResourcesV2 extends ServletContainer {
  }

  static class JerseyConfigResourcesV2 extends ResourceConfig {

    JerseyConfigResourcesV2() {
      register(RequestContextFilter)
      register(GsonProvider)
      register(DomainClassValidationExceptionMapper)
      register(ObjectNotFoundExceptionExceptionMapper)
      register(GenericExceptionExceptionMapper)
      register(MultiPartFeature)
      packages("${group}.restv2")
    }
  }


}
