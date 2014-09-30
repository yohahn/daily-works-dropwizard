package yohahn.dailyworks;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;
import yohahn.dailyworks.health.TemplateHealthCheck;
import yohahn.dailyworks.jdbi.WorkDAO;
import yohahn.dailyworks.resources.HelloWorldResource;
import yohahn.dailyworks.resources.WorkResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * @author yohahn.kim
 * @since 9/6/14 8:35 PM
 */
public class DailyWorksApplication extends Application<DailyWorksConfiguration> {

    public static void main(String[] args) throws Exception {
        new DailyWorksApplication().run(args);
    }

    @Override
    public String getName() {
        return "daily-works";
    }

    @Override
    public void initialize(Bootstrap<DailyWorksConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DailyWorksConfiguration configuration, Environment environment) throws Exception {

        configureCors(environment);

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final WorkDAO dao = jdbi.onDemand(WorkDAO.class);
        environment.jersey().register(new WorkResource(dao));

        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

    private void configureCors(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }
}
