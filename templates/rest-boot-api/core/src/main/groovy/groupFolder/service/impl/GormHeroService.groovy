package ${group}.service

import gex.commons.exception.EntityValidationException
import gex.commons.exception.ObjectNotFoundException
import gex.data.pagination.PageParams
import ${group}.dto.v1.Hero
import ${group}.dto.v1.HeroPage
import ${group}.service.HeroService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import static gex.serling.binding.Util.bind

@Component
@Transactional
class GormHeroService implements HeroService {

  @Override
  Hero createHero(Hero hero) {
    gex.example.domain.Hero domainHero = bind(hero, gex.example.domain.Hero)
    domainHero.safeSave()
    bind(domainHero, Hero)
  }


  @Override
  HeroPage listHeroes(Long from, Long size) {
    PageParams pageParams = new PageParams(from: from, size: size)
    null
  }

  @Override
  Hero getHeroByName(String name) {
    bind(getDomainHero(name), Hero)
  }

  @Override
  Hero updateHero(String name, Hero hero) {
    def domainHero = getDomainHero(name)
    domainHero = bind(hero, domainHero )
    bind(domainHero, Hero)
  }

  @Override
  def deleteHero(String name) {
    getDomainHero(name, true).delete()
  }


  private gex.example.domain.Hero getDomainHero(String name, Boolean failOnNotFound = false ){
    def domainHero = gex.example.domain.Hero.find{
      name == name
    }

    if(!domainHero && failOnNotFound) {
      throw new ObjectNotFoundException("Hero not found", name, 'Hero')
    }

    domainHero
  }
}