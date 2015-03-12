package gex.data

import gex.commons.exception.EntityValidationException
import gex.data.pagination.PageParams
import gex.data.pagination.Paginable

trait Persistable {

  def safeSave() {
    this.validate()
    if (this.hasErrors()) {
      throw new EntityValidationException(this)
    }

    this.save()
  }

}