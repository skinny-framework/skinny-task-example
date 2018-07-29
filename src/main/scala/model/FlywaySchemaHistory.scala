package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class FlywaySchemaHistory(
  installedRank: Int,
  version: Option[String] = None,
  description: String,
  theType: String,
  script: String,
  checksum: Option[Int] = None,
  installedBy: String,
  installedOn: DateTime,
  executionTime: Int,
  success: Boolean
)

object FlywaySchemaHistory extends SkinnyCRUDMapperWithId[Int, FlywaySchemaHistory] {
  override lazy val tableName = "flyway_schema_history"
  override lazy val defaultAlias = createAlias("fsh")
  override lazy val primaryKeyFieldName = "installedRank"
  override def idToRawValue(id: String): Any = id
  override def rawValueToId(value: Any): String = value.toString
  override def useExternalIdGenerator = true
  override def generateId = java.util.UUID.randomUUID.toString
  override lazy val nameConverters = Map("^theType$" -> "type")

  override def extract(rs: WrappedResultSet, rn: ResultName[FlywaySchemaHistory]): FlywaySchemaHistory = {
    autoConstruct(rs, rn)
  }
}
