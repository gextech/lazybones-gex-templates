package ${group}.domain

import grails.persistence.*

import org.jadira.usertype.dateandtime.joda.PersistentDateTime
import org.joda.time.DateTime

@Entity
class Hero {

  String id
  String name
  Integer age
  Boolean immortal

  static mapping = {
    table('heroes')
    id generator: 'uuid2'
  }

  static constraints = {
    name nullable: false, blank: false, unique: true
  }
}