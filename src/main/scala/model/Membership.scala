package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Membership(
  organizationId: Option[Long] = None,
  personId: Option[Long] = None,
  createdAt: DateTime,
  organization: Option[Organization] = None,
  person: Option[Person] = None
)

object Membership extends SkinnyNoIdCRUDMapper[Membership] {
  override lazy val tableName = "membership"
  override lazy val defaultAlias = createAlias("m")

  lazy val organizationRef = belongsTo[Organization](Organization, (m, o) => m.copy(organization = o))

  lazy val personRef = belongsTo[Person](Person, (m, p) => m.copy(person = p))

  override def extract(rs: WrappedResultSet, rn: ResultName[Membership]): Membership = {
    autoConstruct(rs, rn, "organization", "person")
  }
}
