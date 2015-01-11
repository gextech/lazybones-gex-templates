package ${group}.restv2

import groovy.transform.Canonical
import org.springframework.stereotype.Component

import javax.ws.rs.*
import javax.ws.rs.core.Response

import static javax.ws.rs.core.MediaType.APPLICATION_JSON

@Component
@Canonical
@Path("/hello")
class RostResource {
  final static String response = 'Bye World'
  final static Map data = [key: response]

  @GET
  @Produces(APPLICATION_JSON)
  Response hello() {
    Response.status(201).entity(response).build()
  }

  @GET
  @Path('/data')
  @Produces(APPLICATION_JSON)
  Map<String, String> data() {
    data
  }

  @POST
  @Path('/data')
  @Produces(APPLICATION_JSON)
  @Consumes(APPLICATION_JSON)
  Map<String, String> postdata(Map postdata) {
    postdata
  }

}


