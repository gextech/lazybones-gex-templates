package ${group}

import gex.example.domain.Hero
import gex.example.dto.v1.HeroPage
import gex.serling.binding.Util

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@EnableAutoConfiguration
@Configuration
@ComponentScan
class CoreConfig {

  @Bean
  CoreConfigObject getCoreConfigObject() {
    CoreConfigObject config = new CoreConfigObject()

    config.equivalenceMapper = [  
      [domain: Hero, dto: ${group}.dto.v1.Hero, page: HeroPage],
    ]
    
    config
  }

  @Bean
  Util getBindingUtil() {

    def util = new Util()

    def dynamicBindings = [
    ]

    dynamicBindings.each{
      util.registerBinding( it )
    }

    util
  }

}
