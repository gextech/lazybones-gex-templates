package ${group}.service

import gex.serling.binding.Util
import ${group}.CoreConfigObject
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Tsunllly on 11/27/14.
 */
@Service
@Slf4j
class CommonServiceHelper {

  @Autowired
  CoreConfigObject coreConfigObject

  @Autowired
  Util util


  def toDtoPage(Map mapPage, Closure dtoTransformation = null){

    Class domainClazz = mapPage.remove('domainClazz')

    Map equivalence = coreConfigObject.equivalenceMapper.find {
      it.domain == domainClazz
    }

    Class dtoClazz = equivalence?.dto ?: Class.forName("gex.serling.pub.dto.v1.\${domainClazz.simpleName}")

    if(!dtoTransformation) {
      dtoTransformation = { x -> util.dynamicBind(x, dtoClazz) }
    }

    mapPage.items = mapPage.items.collect {
      dtoTransformation.call(it)
    }

    Class pageClazz = equivalence?.page ?: Class.forName("gex.serling.pub.dto.v1.\${domainClazz.simpleName}Page")

    mapPage.asType(pageClazz)
  }
  
}
