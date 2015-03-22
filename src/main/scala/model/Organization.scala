package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

// If your model has +23 fields, switch this to normal class and mixin scalikejdbc.EntityEquality.
case class Organization(
  id: Long,
  name: String,
  countryId: Long,
  url: Option[String] = None,
  createdAt: DateTime,
  country: Option[Country] = None,
  memberships: Seq[Membership] = Nil
)

object Organization extends SkinnyCRUDMapper[Organization] {
  override lazy val tableName = "organization"
  override lazy val defaultAlias = createAlias("o")

  lazy val countryRef = belongsTo[Country](Country, (o, c) => o.copy(country = c))

  lazy val membershipsRef = hasMany[Membership](
    many = Membership -> Membership.defaultAlias,
    on = (o, m) => sqls.eq(o.id, m.organizationId),
    merge = (o, ms) => o.copy(memberships = ms)
  )

  override def extract(rs: WrappedResultSet, rn: ResultName[Organization]): Organization = {
    autoConstruct(rs, rn, "country", "memberships")
  }
}
