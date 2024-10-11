package pl.edu.pg.lab.office.controller;

import pl.edu.pg.lab.office.dto.GetOfficeResponse;
import pl.edu.pg.lab.office.dto.GetOfficesResponse;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.office.service.OfficeService;
import pl.edu.pg.lab.officeCenter.dto.GetOfficeCenterResponse;
import pl.edu.pg.lab.officeCenter.dto.GetOfficeCentersResponse;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.officeCenter.model.OfficeCentersModel;
import pl.edu.pg.lab.officeCenter.service.OfficeCenterService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.util.Optional;

@Path("/office")
public class OfficeController {

    private OfficeService service;


    public OfficeController() {
    }

    @Inject
    public void setService(OfficeService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOffice() {
        return Response
                .ok(GetOfficesResponse.entityToDtoMapper().apply(service.findAll()))
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOffice(@PathParam("id") Long id) {
        Optional<Office> office = service.find(id.intValue());
        if (office.isPresent()) {
            return Response
                    .ok(GetOfficeResponse.entityToDtoMapper().apply(office.get()))
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putOffice(@PathParam("id") Long id,Office office) {
        Optional<Office> officeCheck = service.find(id.intValue());
        if (officeCheck.isEmpty()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        office.setId(id.intValue());
        office.setOfficeCenter(officeCheck.get().getOfficeCenter());
        service.update(office);
        return Response
                .status(Response.Status.ACCEPTED)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteOffice(@PathParam("id") Long id) {
        Optional<Office> officeCheck = service.find(id.intValue());
        if (officeCheck.isEmpty()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        service.delete(id.intValue());
        return Response
                .status(Response.Status.ACCEPTED)
                .build();
    }
}
