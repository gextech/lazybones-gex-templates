package ${group}.restv1

import gex.javax.ws.rs.Resource
import org.springframework.beans.factory.annotation.Autowired

import ${group}.domain.v1.Hero
import ${group}.jaxrs.v1.HeroesResource
import ${group}.service.HeroService

import gex.javax.ws.rs.Resource
import gex.jaxrs.ApiResponse
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.core.Response

@Resource
@Slf4j
class HeroesResourceV1 implements HeroesResource{

  @Autowired
  @Delegate
  ApiResponse apiResponse

  @Autowired
  HeroService heroService

  @Override
  Response getHeroes() {
    ok(heroService.listHeroes())
  }

  @Override
  Response postHeroes(Hero hero) {
    created(heroService.createHero(hero))
  }
}