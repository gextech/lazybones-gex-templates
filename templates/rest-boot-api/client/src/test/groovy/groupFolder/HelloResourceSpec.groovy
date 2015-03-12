package ${group}

import ${group}.client.v1.*
import org.springframework.beans.factory.annotation.Value 
import org.springframework.test.context.ContextConfiguration 
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.IntegrationTest

import spock.lang.*

@IntegrationTest
@WebAppConfiguration 
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
class HelloResourceSpec extends Specification {

  @Shared
  ApiClient client

  def setup() {
    client = Builder.create().buildApi()
  }

  def 'We can login'() {
    when:
      def result = client.getRest().toBlocking().single()
    then:
      result.key == 'Hello World'
  }

  def 'We can not login with invalid username or password'() {
    when:
      def newClient = Builder
        .create()
        .withLoginData(
          'test',
          'password',
          'passwor',
          'read write',
          'clientapp',
          '123456'
        )
        .buildApi()

    then:
      ${group}.client.v1.ApiException ex = thrown()
      ex.message == "Cannot login with request data"
  }

  def 'We can login with username'() {
    when:
      def newClient = Builder
        .create()
        .withLoginData(
          'test',
          'password',
          'password',
          'read write',
          'clientapp',
          '123456'
        )
        .buildApi()

    then:
      true
  }

}
