package neo4jplugin.configuration;

import com.typesafe.config.ConfigFactory;
import neo4jplugin.Neo4jPlugin;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration which is used to connect via rest to the database.
 */
@EnableTransactionManagement
@Configuration
@EnableNeo4jRepositories(basePackages = "neo4j.repositories")
@ComponentScan("neo4j")
public class RestNeo4jConfig extends Neo4JBaseConfiguration
{

  private static String REST_DB_HOST_CFG_KEY = "neo4j.restDB.host";

  private static String REST_DB_USER_CFG_KEY = "neo4j.restDB.user";

  private static String REST_DB_PASSWORD_CFG_KEY = "neo4j.restDB.password";

  @Bean
  public Neo4jServer graphDatabaseService()
  {
    String restDbHost = ConfigFactory.load().getString(REST_DB_HOST_CFG_KEY);
    String restDbUser = ConfigFactory.load().getString(REST_DB_USER_CFG_KEY);
    String restDbPassword = ConfigFactory.load().getString(REST_DB_PASSWORD_CFG_KEY);

    if(Neo4jPlugin.LOGGER.isDebugEnabled() == true) {
      Neo4jPlugin.LOGGER.debug("Connecting to remote database: "+restDbUser+"@"+restDbHost);
    }

    return new RemoteServer(restDbHost, restDbUser, restDbPassword);
  }

  @Override
  public SessionFactory getSessionFactory()
  {
    org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration();
    configuration.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver");
    configuration.driverConfiguration().setURI(StringUtils.remove(ConfigFactory.load().getString(REST_DB_HOST_CFG_KEY), "/db/data"));
    configuration.driverConfiguration().setCredentials(ConfigFactory.load().getString(REST_DB_USER_CFG_KEY), ConfigFactory.load().getString(REST_DB_PASSWORD_CFG_KEY));
    return new SessionFactory(configuration, "neo4j.models");
  }
}
