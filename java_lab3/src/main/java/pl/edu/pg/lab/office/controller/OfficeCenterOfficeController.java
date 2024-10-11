package pl.edu.pg.lab.office.controller;


import pl.edu.pg.lab.office.dto.GetOfficesResponse;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.office.service.OfficeService;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.officeCenter.service.OfficeCenterService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/officeCenter/{centerId}/office")
public class OfficeCenterOfficeController {

    private OfficeService service;
    private OfficeCenterService serviceCenter;



    public OfficeCenterOfficeController() {
    }

    @Inject
    public void setService(OfficeService service) {
        this.service = service;
    }

    @Inject
    public void setService(OfficeCenterService service) {
        this.serviceCenter = service;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOffices(@PathParam("centerId") int id) {
        Optional<OfficeCenter> officeCheck = serviceCenter.find(id);
        if (officeCheck.isEmpty()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .ok(GetOfficesResponse.entityToDtoMapper().apply(service.findByOfficeCenter(id)))
                .build();
    }

    @POST
    public Response postOfficeCenter(@PathParam("centerId") int id, Office office) {
        OfficeCenter officeCenter = serviceCenter.find(id).get();
        officeCenter.getOfficeList().add(office);
        serviceCenter.update(officeCenter);
        service.create(office);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

}
