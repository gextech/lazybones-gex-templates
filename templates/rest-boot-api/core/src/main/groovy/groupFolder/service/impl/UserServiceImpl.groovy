package ${group}.service

import ${group}.dto.v1.User
import ${group}.service.UserService

import ${group}.domain.User as UserDomain
import gex.commons.exception.ObjectNotFoundException

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import static gex.serling.binding.Util.bind

@Component
@Transactional
class UserServiceImpl implements UserService { 

  User findByUsername(String username) {
    UserDomain domain = UserDomain.findByUsername(username)

    if(domain == null) {
      throw new ObjectNotFoundException(username, "user")
    }

    map domain
  }

  @Override
  User createUser(User user) {
    UserDomain domain = new UserDomain()
    bind(user, domain, ['roles'])
    domain.save(failOnError: true)
    findByUsername(user.username)
  }

  @Override
  User updateUser(String username, User user) {
    UserDomain domain = UserDomain.findByUsername(username)

    if(domain == null) {
      throw new ObjectNotFoundException(username)
    }

    bind(user, domain, ['roles'])
    domain.save(failOnError: true)

    findByUsername(user.username)
  }

  @Override
  void deleteUser(String username) {
    UserDomain domain = UserDomain.findByUsername(username)

    if(domain == null) {
      throw new ObjectNotFoundException(username)
    }

    domain.delete(failOnError: true)
  }

  private User map(UserDomain domain) {
    User dto = bind(domain, User, ['roles'])
    dto.roles = domain.roles.collect {
      it.name
    }
    dto
  }

}

