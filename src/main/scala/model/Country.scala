package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

// If your model has +23 fields, switch this to normal class and mixin scalikejdbc.EntityEquality.
case class Country(
  id: Long,
  name: String,
  organizations: Seq[Organization] = Nil,
  persons: Seq[Person] = Nil
)

object Country extends SkinnyCRUDMapper[Country] {
  override lazy val tableName = "country"
  override lazy val defaultAlias = createAlias("c")

  lazy val organizationsRef = hasMany[Organization](
    many = Organization -> Organization.defaultAlias,
    on = (c, o) => sqls.eq(c.id, o.countryId),
    merge = (c, os) => c.copy(organizations = os)
  )

  lazy val personsRef = hasMany[Person](
    many = Person -> Person.defaultAlias,
    on = (c, p) => sqls.eq(c.id, p.countryId),
    merge = (c, ps) => c.copy(persons = ps)
  )

  override def extract(rs: WrappedResultSet, rn: ResultName[Country]): Country = {
    autoConstruct(rs, rn, "organizations", "persons")
  }
}
