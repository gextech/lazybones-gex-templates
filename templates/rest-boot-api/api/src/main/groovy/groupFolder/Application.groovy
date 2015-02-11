package ${group}

import org.springframework.boot.autoconfigure.SpringBootApplication 
import org.springframework.cloud.netflix.hystrix.EnableHystrix

import static org.springframework.boot.SpringApplication.run

@SpringBootApplication
@EnableHystrix
class Application {
  static void main(String[] args) {
    run Application, args
  }
}

