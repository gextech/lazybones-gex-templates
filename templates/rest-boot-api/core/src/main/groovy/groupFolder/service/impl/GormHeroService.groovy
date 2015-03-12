package ${group}.service

import gex.commons.exception.EntityValidationException
import gex.commons.exception.ObjectNotFoundException
import gex.data.pagination.PageParams
import ${group}.dto.v1.Hero
import ${group}.dto.v1.HeroPage
import ${group}.service.HeroService
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import static gex.serling.binding.Util.bind

@Component
@Transactional
class GormHeroService implements HeroService {

  @Autowired
  @Delegate
  CommonServiceHelper commonServiceHelper

  @Override
  Hero createHero(Hero hero) {
    ${group}.domain.Hero domainHero = bind(hero, ${group}.domain.Hero)
    domainHero.safeSave()
    bind(domainHero, Hero)
  }


  @Override
  HeroPage listHeroes(Long from, Long size) {
    PageParams pageParams = new PageParams(from: from, size: size)
    def results = ${group}.domain.Hero.searchPage(pageParams)
    toDtoPage(results)
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


  private ${group}.domain.Hero getDomainHero(String name, Boolean failOnNotFound = false ){
    def domainHero = ${group}.domain.Hero.find{
      name == name
    }

    if(!domainHero && failOnNotFound) {
      throw new ObjectNotFoundException("Hero not found", name, 'Hero')
    }

    domainHero
  }
}