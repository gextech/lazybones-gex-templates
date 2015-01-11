package ${group}.restv1

import gex.jaxrs.ApiResponse
import ${group}.dto.v1.SimpleOK
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.ws.rs.core.Response

@Component
class PubApiResponse {
  @Autowired
  ApiResponse apiResponse

  Response simpleOk(Object entity = null) {
    apiResponse.ok(new SimpleOK([success: true]))
  }
}
