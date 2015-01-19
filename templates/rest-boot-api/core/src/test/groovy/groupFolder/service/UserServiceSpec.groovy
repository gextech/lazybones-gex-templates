package ${group}.service

import ${group}.CoreConfigTest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.IgnoreIf
import spock.util.concurrent.PollingConditions
import spock.lang.Specification
import spock.lang.Shared

import java.text.DateFormat
import java.text.SimpleDateFormat

import ${group}.service.UserService
import ${group}.dto.v1.User
import gex.commons.exception.ObjectNotFoundException

import static java.util.Collections.emptyList

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = CoreConfigTest)
@EnableAutoConfiguration
@Transactional
class UserServiceSpec extends Specification {

  @Autowired
  UserService userService

  def "We should be able to create users"() {
    when:
      User user = new User(
        email: "user@user.com",
        username: "user",
        password: "password"
      )

      user = userService.createUser(user)

    then:
      user
  }
  
  def "We should be able to update users"() {
    when:
      User user = new User(
        email: "user@user.com",
        username: "user",
        password: "password"
      )

      user = userService.createUser(user)
      String firstId = user.id
      String firstPassword = user.password

    then:
      user

    when:
      user = new User(
        email: "user@user.com",
        username: "theuser"
      )

      user = userService.updateUser("user", user)
      String secondId = user.id
      String secondPassword = user.password

    then:
      user
      user.username == "theuser"
      firstId
      firstId == secondId
      firstPassword
      firstPassword == secondPassword
  }

  def "We should be able to delete users"() {
    when:
      User user = new User(
        email: "user@user.com",
        username: "user",
        password: "password"
      )

      user = userService.createUser(user)

    then:
      user
      user.username == "user"
    
    when:
      user = userService.findByUsername("user")

    then:
      user
      user.username == "user"

    when:
      userService.deleteUser("user")

      userService.findByUsername("user")

    then:    
      ObjectNotFoundException ex = thrown()
      ex.identifier == "user"
  }

}
