package ${group}.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {
  final static String response = 'Greetings from Spring Boot!'


  @RequestMapping("/test")
  String index() {
    response
  }

}

