package ${group}.domain

import grails.persistence.*

import org.jadira.usertype.dateandtime.joda.PersistentDateTime
import org.joda.time.DateTime
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Entity
@Component
class User {

  static PasswordEncoder encoder  

  String id
  String email

  String username
  String password

  DateTime dateCreated
  DateTime lastUpdated

  static transients = ['encoder']
  static hasMany = [roles: Role]

  static mapping = {
    table "person"
    id generator: 'uuid2'
    dateCreated type: PersistentDateTime
    lastUpdated type: PersistentDateTime
  }

  static constraints = {
    username nullable: false, blank: false, unique: true
    email email: true, nullable: false, blank: false
  }

  def beforeInsert() {
    encodePassword()
  }

  def beforeUpdate() {
    if (isDirty('password')) {
      encodePassword()
    }
  }

  protected void encodePassword() {
    password = encoder.encode(password)
  }

  @Autowired
  void setEncoder(PasswordEncoder encoder) {
    User.encoder = encoder
  }

}

