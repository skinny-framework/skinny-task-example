package model

import skinny.DBSettings
import skinny.test._
import org.scalatest.fixture.FlatSpec
import org.scalatest._
import scalikejdbc._
import scalikejdbc.scalatest._
import org.joda.time._

class OrganizationSpec extends FlatSpec with Matchers with DBSettings with AutoRollback {
}
