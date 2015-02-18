package ${group}.turbine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.turbine.amqp.EnableTurbineAmqp

import static org.springframework.boot.SpringApplication.run

@SpringBootApplication
@EnableTurbineAmqp
class App {
  static void main(String[] args) {
    run App, args
  }
}
