package ${group}

import ${group}.client.v1.*
import ${group}.dto.Hero
import org.springframework.beans.factory.annotation.Value 
import org.springframework.test.context.ContextConfiguration 
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.IntegrationTest

import spock.lang.*

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
@WebAppConfiguration
@IntegrationTest
class HeroSpec extends Specification{

  @Shared
  ApiClient apiClient


  def setup() {
    apiClient = new Builder().create().buildApi()
  }


  def 'Posting invalid  hero throws exception'(){
    setup:
      Hero hero = new Hero()

    when:
      apiClient.postHeroes(hero).toBlocking().first()

    then:
      UserErrorException e = thrown()
      e.message == "The provided entity has some errors."
      e.errorResponse.validationErrors.containsAll(["Name is mandatory"])

  }

  def 'Posting hero works happily'(){
    setup:
      Hero hero = new Hero()
      hero.name = 'Goku'
      hero.age = 12
      hero.immortal = false

    when:
      def response = apiClient.postHeroes(hero).toBlocking().first()

    then:
      response.name == 'Goku'
      response.age == 12
      response.immortal == false
  }

}