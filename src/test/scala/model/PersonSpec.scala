package model

import skinny.DBSettings
import skinny.test._
import org.scalatest.fixture.FlatSpec
import org.scalatest._
import scalikejdbc._
import scalikejdbc.scalatest._
import org.joda.time._

class PersonSpec extends FlatSpec with Matchers with DBSettings with AutoRollback {
}
