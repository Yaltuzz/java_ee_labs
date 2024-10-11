package pl.edu.pg.lab.officeCenter.controller;

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

@Path("/officeCenter")
public class OfficeCenterController {

    private OfficeCenterService service;
    private OfficeService officeService;


    public OfficeCenterController() {
    }

    @Inject
    public void setService(OfficeCenterService service) {
        this.service = service;
    }


    @Inject
    public void setService(OfficeService service) {
        this.officeService = service;
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOfficeCenters() {
        return Response
                .ok(GetOfficeCentersResponse.entityToDtoMapper().apply(service.findAll()))
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOfficeCenter(@PathParam("id") Long id) {
        Optional<OfficeCenter> officeCenter = service.find(id.intValue());
        if (officeCenter.isPresent()) {
            return Response
                    .ok(GetOfficeCenterResponse.entityToDtoMapper().apply(officeCenter.get()))
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    public Response postOfficeCenter(OfficeCenter officeCenter) {
        service.create(officeCenter);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putOfficeCenter(@PathParam("id") Long id,OfficeCenter officeCenter) {
        Optional<OfficeCenter> officeCheck = service.find(id.intValue());
        if (officeCheck.isEmpty()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        officeCenter.setId(id.intValue());
        service.update(officeCenter);
        return Response
                .status(Response.Status.ACCEPTED)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteOfficeCenter(@PathParam("id") Long id) {
        Optional<OfficeCenter> officeCenter = service.find(id.intValue());
        if (officeCenter.isEmpty()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }else {
            service.delete(id.intValue());
            return Response
                    .status(Response.Status.ACCEPTED)
                    .build();
        }
    }
}
