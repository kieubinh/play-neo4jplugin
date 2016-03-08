package neo4j;

import com.typesafe.config.ConfigFactory;
import neo4jplugin.Neo4jPlugin;
import neo4jplugin.configuration.Neo4JBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Johannes Unterstein (unterstein@me.com)
 */
//@EnableTransactionManagement
//@Configuration
//@EnableNeo4jRepositories(basePackages = "neo4j.repositories")
//@ComponentScan("neo4j")
public class ExampleOwnConfiguration extends Neo4JBaseConfiguration {

//  private static String REST_DB_HOST_CFG_KEY = "neo4j.restDB.host";
//
//  private static String REST_DB_USER_CFG_KEY = "neo4j.restDB.user";
//
//  private static String REST_DB_PASSWORD_CFG_KEY = "neo4j.restDB.password";
//
//  @Bean
//  public Neo4jServer graphDatabaseService()
//  {
//    String restDbHost = ConfigFactory.load().getString(REST_DB_HOST_CFG_KEY);
//    String restDbUser = ConfigFactory.load().getString(REST_DB_USER_CFG_KEY);
//    String restDbPassword = ConfigFactory.load().getString(REST_DB_PASSWORD_CFG_KEY);
//
//    if(Neo4jPlugin.LOGGER.isDebugEnabled() == true) {
//      Neo4jPlugin.LOGGER.debug("Connecting to remote database: "+restDbUser+"@"+restDbHost);
//    }
//
//    return new RemoteServer(restDbHost, restDbUser, restDbPassword);
//  }
}
