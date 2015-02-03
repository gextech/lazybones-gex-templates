package gex.serling.wat.config

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.orm.hibernate.cfg.HibernateUtils
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration

import javax.annotation.PostConstruct

/**
 * Enabling addTo* and removeFrom*
 * https://jira.grails.org/browse/GRAILS-11597
 */
@Configuration
class BootStrap {

  @Autowired
  ApplicationContext applicationContext

  @Autowired
  SessionFactory sessionFactory

  @Autowired
  GrailsApplication grailsApplication

  @PostConstruct
  def bootstrap() {
    HibernateUtils.enhanceSessionFactory(sessionFactory, grailsApplication, applicationContext)
  }
}
