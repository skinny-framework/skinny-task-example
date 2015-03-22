import skinny.SkinnyEnv
import skinny.dbmigration.DBMigration
import skinny.task._, generator._

object TaskRunner extends SkinnyTaskLauncher {

  // If you'd like to add other generators, see the following code:
  // https://github.com/skinny-framework/skinny-framework/blob/master/task/src/main/scala/skinny/task/DefaultTaskLauncher.scala
 
  register("db:migrate", {
    case env :: dbName :: rest => DBMigration.migrate(env, dbName)
    case params => DBMigration.migrate(params.headOption.getOrElse(SkinnyEnv.Development))
  })

  val reverseModelAllGenerator = new ReverseModelAllGenerator {
    override def sourceDir = "src/main/scala"
    override def testSourceDir = "src/test/scala"
    override def resourceDir = "src/main/resources"
    override def testResourceDir = "src/test/resources"
    override def modelPackage: String = "model"
  }
  register("generate:reverse-model-all", (params) => reverseModelAllGenerator.run(params))  

}
