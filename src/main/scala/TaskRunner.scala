import skinny.SkinnyEnv
import skinny.dbmigration.DBMigration
import skinny.task._, generator._

/**
 * A task runnner which simply works as a main class. `sbt run` detects and invokes this class.
 * No generators are not automatically registered in SkinnyTaskLauncher.
 */
object TaskRunner extends SkinnyTaskLauncher {

  // "development.db.default" configuration in application.conf is expected by default.
  // see also: http://skinny-framework.org/documentation/orm.html
  register("db:migrate", {
    case env :: dbName :: rest => DBMigration.migrate(env, dbName)
    case params => DBMigration.migrate(params.headOption.getOrElse(SkinnyEnv.Development))
  })
  register("db:repair", {
    case env :: dbName :: rest => DBMigration.repair(env, dbName)
    case params => DBMigration.repair(params.headOption.getOrElse(SkinnyEnv.Development))
  })

  // If you don't need to customize settings, use ReverseModelAllGenerator object instead.
  val reverseModelAllGenerator = new ReverseModelAllGenerator {
    override def sourceDir = "src/main/scala"
    override def testSourceDir = "src/test/scala"
    override def resourceDir = "src/main/resources"
    override def testResourceDir = "src/test/resources"
    override def modelPackage = "model"
  }
  register("generate:reverse-model-all", (params) => reverseModelAllGenerator.run(params))  

  // If you'd like to add other generators, see the following code:
  // https://github.com/skinny-framework/skinny-framework/blob/master/task/src/main/scala/skinny/task/DefaultTaskLauncher.scala
 
}
