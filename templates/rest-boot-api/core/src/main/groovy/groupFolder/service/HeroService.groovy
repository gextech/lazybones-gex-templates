package ${group}.service

import ${group}.dto.v1.Hero
import ${group}.dto.v1.HeroPage
import ${group}.dto.v1.HeroPage

interface HeroService {

  Hero createHero(Hero hero)

  HeroPage listHeroes(Long from, Long size)

  Hero getHeroByName(String name)

  Hero updateHero(String name, Hero hero)

  def deleteHero(String name)
}
