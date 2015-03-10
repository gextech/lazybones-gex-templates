package ${group}.service

import ${group}.dto.v1.Hero

interface HeroService {

  Hero createHero(Hero hero)

  List<Hero> listHeroes()

  Hero getHeroByName(String name)

  Hero updateHero(String name, Hero hero)

  def deleteHero(String name)
}
