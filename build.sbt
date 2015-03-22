scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.skinny-framework" %% "skinny-orm"        % "1.3.+",
  "org.apache.commons"   %  "commons-dbcp2"     % "2.1",
  "org.skinny-framework" %% "skinny-task"       % "1.3.+",
  "com.h2database"       %  "h2"                % "1.4.+",
  "ch.qos.logback"       %  "logback-classic"   % "1.1.+"
)

// these commands will be executed when invoking `sbt console`
initialCommands := """
import scalikejdbc._
import skinny.orm._
skinny.DBSettings.initialize()
implicit val dbSession = AutoSession
"""
