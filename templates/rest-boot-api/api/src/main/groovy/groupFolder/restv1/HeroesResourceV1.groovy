package ${group}.restv1

import gex.javax.ws.rs.Resource
import org.springframework.beans.factory.annotation.Autowired

import ${group}.domain.v1.Hero
import ${group}.jaxrs.v1.HeroResource
import ${group}.service.HeroService

import gex.javax.ws.rs.Resource
import gex.jaxrs.ApiResponse
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.PathParam

import javax.ws.rs.core.Response

@Resource
@Slf4j
class HeroResourceV1 implements HeroResource{

  @Autowired
  @Delegate
  ApiResponse apiResponse

  @Autowired
  HeroService heroService

  @Override
  Response getHero(@PathParam("name") String name) {
    ok(heroService.getHeroByName(name))
  }

  @Override
  Response putHero(@PathParam("name") String name, Hero hero) {
    ok(heroService.updateHero(name, hero))
  }

  @Override
  Response deleteHero(@PathParam("name") String name) {
    heroService.deleteHero(name)
    noContent()
  }
}


