# Play Neo4JPlugin

This is a simple Play 2.1.2 plugin, for NEO4J.

I got the ideas how to do it from https://github.com/tomasmuller/playframework-neo4j-template THANKS A LOT @tomasmuller for giving me the ground ideas :)

What i didn't liked that i can't call neo4j in the static way, like Ebean etc...

So here we go, i created this plugin :)

## Versions
1.1.1: Lifted to neo4j-1.9.3 and spring-data-neo4j 2.3.1-RELEASE

1.1.0: Added controller for transactional scala

1.0.7: RestConfiguration and added AuditingEventListener

1.0.2: New Spring-Data-2.2.2 Version Dep. 



## Installation (using sbt)

You will need to add the following resolver in your `project/Build.scala` file:

```scala
resolvers += "tuxburner.github.io" at "http://tuxburner.github.io/repo",
resolvers += "Neo4j" at "http://m2.neo4j.org/content/repositories/releases/"
```

Add a dependency on the following artifact:

```scala
libraryDependencies += "com.github.tuxBurner" %% "play-neo4jplugin" % "1.1.1"
```

Activate the plugin in the `conf/play.plugins` like this:

```
900:neo4jplugin.Neo4JPlugin
```

Settings for the plugin go into the `conf/application.conf`:

```
neo4j.serviceProviderClass="neo4j.services.Neo4JServiceProvider" # the provider class which holds the annotated neo4j beans
neo4j.mode="embedded" # mode to run embedded or remote

# embedded db config
neo4j.embeddedDB="target/neo4j-db" # where to put the embedded database

# remote db config
neo4j.restDB.host="http://localhost:7474/db/data"
neo4j.restDB.user=""
neo4j.restDB.password=""

```

## Usage

All neo4j relevant stuff must go to `app/neo4j/`

All neo4j repositories go to `app/neo4j/repositories/`


You need a class which must extend from `neo4jplugin.ServiceProvider` and must be configured in the `application.conf` under the key `neo4j.serviceProviderClass`:

Example: 
```java
    @Component
    public class Neo4JServiceProvider extends ServiceProvider {

      @Autowired
      public NeoUserRepository neoUserRepository;

      public static Neo4JServiceProvider get() {
        return Neo4JPlugin.get();
      }
}

```

To access your repository you can call: `Neo4JServiceProvider.get().neoUserRepository.<do magic neo4j stuff>`

To access the neo4jtemplate you can call: `Neo4JServiceProvider.get().template.<do magic neo4j stuff>`

There is also a `@Transactional` annotation which I addopted from the play jpa plugin.

Just annotate your Result with it and it runs in a neo4j Transaction.

Example: 
```java
  @Transactional
  public static Result doSomethingInTransaction(Long id) {
    Neo4JServiceProvider.get().neoUserRepository.<do magic neo4j stuff>
    Neo4JServiceProvider.get().neoUserRepository.<do magic neo4j stuff>

    return ok("Woohhoh Neo4jTransaction");
  }  
```

Take a look into the examples


## TODO

Make the spring configuration configable with the play configuration so the neo4j stuff has not to be in the folder app/neoj do the same with the repositories.
