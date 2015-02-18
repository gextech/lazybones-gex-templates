package ${group}.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard

import static org.springframework.boot.SpringApplication.run

@SpringBootApplication
@EnableHystrixDashboard
class App {
  static void main(String[] args) {
    run App, args
  }
}
