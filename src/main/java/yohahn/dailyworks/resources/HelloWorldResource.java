package yohahn.dailyworks.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import yohahn.dailyworks.core.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yohahn.kim
 * @since 9/6/14 8:47 PM
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(this.template, name.or(this.defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
