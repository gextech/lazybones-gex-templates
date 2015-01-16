package ${group}.restv1

import ${group}.Application
import ${group}.controller.SampleController
import ${group}.restv2.RostResource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
@WebAppConfiguration
@IntegrationTest
class RestResourceSpec extends Specification {

  @Value('\${local.server.port}')
  int port

  RestTemplate restTemplate = new TestRestTemplate()

  @Unroll
  def 'Resource #version hello should work as expected'() {
    given:
      def url = "http://localhost:\$port/\$version/hello"
      def entity = restTemplate.getForEntity(url, String)
      def result = entity.body
    expect:
      result == expected
      entity.statusCode == CREATED
    where:
      version || expected
      'v1'    || RestResource.response
      'v2'    || RostResource.response
  }

  def 'SampleController should work as expected'() {
    given:
      def result = "http://localhost:\$port/test".toURL().text
    expect:
      result == SampleController.response
  }

  @Unroll
  def 'Resource #version hello/data should work as expected'() {
    given:
      def url = "http://localhost:\$port/\$version/hello/data"
      def entity = restTemplate.getForEntity(url, Map)
      def result = entity.body
    expect:
      result == expected
      entity.statusCode == OK
    where:
      version || expected
      'v1'    || RestResource.data
      'v2'    || RostResource.data
  }

  @Unroll
  def 'Resource #version hello/data should work as expected when post'() {
    given:
      def url = "http://localhost:\$port/\$version/hello/data"
      def entity = restTemplate.postForEntity(url, expected, Map, [:])
      def result = entity.body
    expect:
      result == expected
      entity.statusCode == OK
    where:
      version || expected
      'v1'    || RestResource.data
      'v2'    || RostResource.data
  }
}
