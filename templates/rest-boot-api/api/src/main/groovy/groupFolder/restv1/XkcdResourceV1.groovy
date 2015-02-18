package ${group}.restv1

import gex.javax.ws.rs.Resource
import org.springframework.beans.factory.annotation.Autowired

import ${group}.jaxrs.v1.XkcdResource
import ${group}.service.XkcdService

import javax.ws.rs.core.Response

@Resource
class XkcdResourceV1 implements XkcdResource {

  @Autowired
  XkcdService xkcdService

  Response getXkcd() {
    Map comicData = xkcdService.getLastComic("http://xkcd.com")
    Response.status(200).entity(comicData).build()
  }

}


