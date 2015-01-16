package ${group}.restv1

import groovy.json.JsonBuilder

import javax.servlet.http.HttpServletRequest
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import static javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/hello")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
class RestResource {
  final static String response = 'Hello World'
  final static Map data = [key: response]

  @GET
  Response hello() {
    Response.status(200).entity(data).build()
  }

  @POST
  Response hello2(@Context HttpServletRequest req) {
    Response.status(201).entity("Hello World post :)").build()
  }

  @POST
  @Path("/post")
  Response hello3(Map dto) {
    Response.status(201).entity(dto).build()
  }

  @GET
  @Path('/data')
  Map<String, String> data() {
    data
  }

  @POST
  @Path('/data')
  Map<String, String> postdata(Map postdata) {
    postdata
  }
}

