package ${group}.service

import ${group}.dto.v1.User

interface UserService {
  User findByUsername(String username)
}

