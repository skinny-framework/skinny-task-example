package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Person(
  id: Long,
  name: String,
  countryId: Long,
  createdAt: DateTime,
  country: Option[Country] = None,
  memberships: Seq[Membership] = Nil
)

object Person extends SkinnyCRUDMapper[Person] {
  override lazy val tableName = "person"
  override lazy val defaultAlias = createAlias("p")

  lazy val countryRef = belongsTo[Country](Country, (p, c) => p.copy(country = c))

  lazy val membershipsRef = hasMany[Membership](
    many = Membership -> Membership.defaultAlias,
    on = (p, m) => sqls.eq(p.id, m.personId),
    merge = (p, ms) => p.copy(memberships = ms)
  )

  override def extract(rs: WrappedResultSet, rn: ResultName[Person]): Person = {
    autoConstruct(rs, rn, "country", "memberships")
  }
}
