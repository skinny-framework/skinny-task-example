### Simple example which shows how to use skinny-task

This is a simple example which shows how to use only [skinny-task](http://skinny-framework.org/documentation/tasks.html) without web application project settings.
Since skinny-task's task runner works as a main class, `sbt run` detects and invokes this class. 
Yes, build tool independent and easy to customize! That's pretty good.

skinny-task has the following out-of-the-box generators:

https://github.com/skinny-framework/skinny-framework/blob/master/task/src/main/scala/skinny/task/DefaultTaskLauncher.scala

Especially reverse-xxx generators will help you a lot when bootstrapping new application which works with existing database tables.

Seeing is believing. Let's get started with skinny's useful generators.

### Project Structure

The project structure is completely simple. 
The Flyway things under `src/main/resources/db` are just needed for the purpose of demonstration.
If you work with existing databases, you don't need any migration things.
So only `build.sbt`, `application.conf` and `TaskRunner.scala` are essentially required.

```bash
$ tree
.
├── README.md
├── build.sbt // very simple sbt settings
├── project
│   └── build.properties // specify sbt version
└── src
    └── main
        ├── resources
        │   ├── application.conf // settings for database connection
        │   ├── db
        │   │   └── migration
        │   │       └── V0__example.sql // flywaydb style migration file, just for demo
        │   └── logback.xml // settings for logging
        └── scala
            └── TaskRunner.scala // task runner as a main class
```

### Task Runner

We believe you'll immediately undestand how to define tasks.

https://github.com/skinny-framework/skinny-task-example/blob/master/src/main/scala/TaskRunner.scala

The simplest task definition is as below. If you'd like to customize some configurations, instantiate with overriding methods as the above example.

```scala
register("generate:reverse-model-all", (params) => ReverseModelAllGenerator.run(params)) 
```

### How to run this example

```bash
brew install sbt
sbt "run db:migrate"
sbt "run generate:reverse-model-all"
```

You will see the following output

### Result

See this branch:

https://github.com/skinny-framework/skinny-task-example/tree/after

#### Reference Links

- http://skinny-framework.org/documentation/tasks.html
- http://skinny-framework.org/documentation/orm.html
- http://flywaydb.org/ for db migration
- http://scalikejdbc.org/ for SQL execution, connection management
