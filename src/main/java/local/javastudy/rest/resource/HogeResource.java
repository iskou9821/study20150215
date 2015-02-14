package local.javastudy.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import local.javastudy.rest.resource.model.HogeModel;

import com.sun.jersey.multipart.FormDataParam;

@Path("/hoge")
public class HogeResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HogeModel[] findAll() {
		List<HogeModel> res = new ArrayList<HogeModel>();
		res.add(new HogeModel(1L, "hoge", "piyo"));
		res.add(new HogeModel(2L, "foo", "bar"));
		return res.toArray(new HogeModel[0]);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public HogeModel findOne(@PathParam("id")Long id) {
		return new HogeModel(id, "aaa", "bbb");
	}
	
	@POST
	@Consumes("multipart/form-data")
	@Produces(MediaType.APPLICATION_JSON)
	public HogeModel createRecord(
							@FormDataParam("msg1")String msg1,
							@FormDataParam("msg2")String msg2) {
		return new HogeModel(100L, "hoge", "piyo");
	}
}
