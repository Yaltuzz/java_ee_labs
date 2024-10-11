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
import java.util.List;
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

        Optional<List<Office>> offices= service.findByOfficeCenter(id);
        if (offices.isEmpty()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }else{
            return Response
                    .ok(GetOfficesResponse.entityToDtoMapper().apply(offices.get()))
                    .build();
        }
    }

    @POST
    public Response postOfficeCenter(@PathParam("centerId") int id, Office office) {
        Optional<OfficeCenter> officeCenter= serviceCenter.find(id);
        if (officeCenter.isEmpty()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        else {
            office.setOfficeCenter(officeCenter.get());
            service.create(office);
            officeCenter.get().getOfficeList().add(office);
            serviceCenter.update(officeCenter.get());
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        }
    }

}
