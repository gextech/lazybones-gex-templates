package ${group}

import ${group}.client.v1.*
import ${group}.dto.v1.Hero
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

    cleanup:
      apiClient.deleteHero('Goku').toBlocking().first()
  }

  def 'GET: Hero pagination works'(){
    setup:
      Hero hero = new Hero(name: 'Goku', age: 12, immortal: false)
      apiClient.postHeroes(hero).toBlocking().first()

      hero = new Hero(name: 'Vegeta', age: 12, immortal: false)
      apiClient.postHeroes(hero).toBlocking().first()
    when:
      def response = apiClient.getHeroesAndSizeAndFrom(1, 0).toBlocking().first()


    then:
      response.items
      response.items.size() == 1
      response.items[0].class == Hero.class
      response.page.from == 0
      response.page.size == 1
      response.page.currentPage == 1
      response.page.total >= 2
      response.page.hasPrev == false
      response.page.hasNext == true

    cleanup:
      apiClient.deleteHero('Goku').toBlocking().first()
      apiClient.deleteHero('Vegeta').toBlocking().first()
  }

}