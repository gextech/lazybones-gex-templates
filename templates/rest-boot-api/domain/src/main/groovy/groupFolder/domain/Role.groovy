package ${group}.domain

import grails.persistence.*

import org.jadira.usertype.dateandtime.joda.PersistentDateTime
import org.joda.time.DateTime

@Entity
class Role {

  String id

  String name
  String description

  DateTime dateCreated
  DateTime lastUpdated

  Boolean active = true

  static mapping = {
    id generator: 'uuid2'
    dateCreated type: PersistentDateTime
    lastUpdated type: PersistentDateTime
  }

  static constraints = {
    description nullable: true, blank: true
  }

}


