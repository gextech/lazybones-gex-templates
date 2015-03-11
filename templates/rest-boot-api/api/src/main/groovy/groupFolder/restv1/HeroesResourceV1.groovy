package ${group}.restv1

import ${group}.domain.Hero
import ${group}.jaxrs.v1.HeroesResource
import ${group}.service.HeroService

import gex.javax.ws.rs.Resource
import gex.jaxrs.ApiResponse
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.QueryParam
import javax.ws.rs.PathParam

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
  Response getHeroes(
    @QueryParam("from")Long from, @QueryParam("size")Long size){
    from = (from == null) ? 0 : from
    size = (size == null) ? 10 : size
    ok(heroService.listHeroes(from, size))
  }

  @Override
  Response postHeroes(Hero hero) {
    created(heroService.createHero(hero))
  }
}