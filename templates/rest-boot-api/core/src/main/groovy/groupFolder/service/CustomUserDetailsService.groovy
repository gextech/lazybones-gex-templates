package ${group}.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import ${group}.dto.v1.User
import gex.commons.exception.ObjectNotFoundException

@Service
class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  UserService userService

  @Override
  public UserDetails loadUserByUsername(String username) {
    try {
      User user = userService.findByUsername(username)
      new CustomUserDetails(user)
    } catch(ObjectNotFoundException ex) {
      throw new UsernameNotFoundException("User \${username} does not exist!")
    }
  }

  private final static class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L
    private String username
    private String password
    private Collection<? extends GrantedAuthority> authorities

    private CustomUserDetails(User user) {
      username = user.username
      password = user.password
      authorities = AuthorityUtils.createAuthorityList(*user.roles)
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
      authorities
    }

    @Override
    String getUsername() {
      username
    }

    @Override
    String getPassword() {
      password
    }

    @Override
    boolean isAccountNonExpired() {
      true
    }

    @Override
    boolean isAccountNonLocked() {
      true
    }

    @Override
    boolean isCredentialsNonExpired() {
      true
    }

    @Override
    boolean isEnabled() {
      true
    }
  }
}

