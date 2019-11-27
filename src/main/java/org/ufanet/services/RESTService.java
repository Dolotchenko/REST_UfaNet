package org.ufanet.services;

//import javax.ws.rs.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/work")
public class RESTService {

    @GET
    @Path("/{allnotes}")
    @Produces(MediaType.APPLICATION_JSON)

    public CustomNotes printAllNotes() {
        DBWorking dbWorking = new DBWorking();
        CustomNotes notes = new CustomNotes(dbWorking.doGet());
        return notes;

    }

    @POST
    @Path("/{add}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String postNewNote(String text) {
        DBWorking dbWorking = new DBWorking();
        if (dbWorking.doPostOrDelete(2, text)) {
            return "Note is posted";
        } else return "Error posted data";

    }


    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/del/{id}")
    public String delNote(@PathParam("id") String noteId) {
        DBWorking dbWorking = new DBWorking();
        if (dbWorking.doPostOrDelete(3, noteId)) {
            return "Note is deleteded";
        } else return "Error delete data";

    }

}
