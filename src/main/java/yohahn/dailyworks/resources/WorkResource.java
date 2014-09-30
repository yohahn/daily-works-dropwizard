package yohahn.dailyworks.resources;

import com.codahale.metrics.annotation.Timed;
import yohahn.dailyworks.core.Work;
import yohahn.dailyworks.jdbi.WorkDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author yohahn.kim
 * @since 9/12/14 10:25 PM
 */
@Path("/works")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkResource {

    private final WorkDAO workDAO;

    public WorkResource(WorkDAO workDAO) {
        this.workDAO = workDAO;
    }

    @GET
    @Timed
    public List<Work> findAll() {
        return this.workDAO.findAll();
    }

    @POST
    @Timed
    public Response create(Work work) throws URISyntaxException {
        int newWorkId = this.workDAO.create(work.getTitle(), work.isCompleted());
        return Response.created(new URI(String.valueOf(newWorkId))).build();
    }

    @GET
    @Path("/{id}")
    @Timed
    public Response findById(@PathParam("id") int id) {
        Work work = this.workDAO.findById(id);
        return Response.ok(work).build();
    }

    @PUT
    @Path("/{id}")
    @Timed
    public Response update(@PathParam("id") int id, Work work) {
        this.workDAO.update(id, work.getTitle(), work.isCompleted());
        return Response.ok(work).build();
    }

    @DELETE
    @Path("/{id}")
    @Timed
    public Response delete(@PathParam("id") int id) {
        this.workDAO.delete(id);
        return Response.noContent().build();
    }
}
