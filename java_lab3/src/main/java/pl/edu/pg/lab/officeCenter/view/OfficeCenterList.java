package pl.edu.pg.lab.officeCenter.view;

import pl.edu.pg.lab.officeCenter.model.OfficeCentersModel;
import pl.edu.pg.lab.officeCenter.service.OfficeCenterService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@RequestScoped
@Named
public class OfficeCenterList implements Serializable {

    private final OfficeCenterService service;

    private OfficeCentersModel officeCenters;

    @Inject
    public OfficeCenterList(OfficeCenterService service) {
        this.service = service;
    }

    public OfficeCentersModel getOfficeCenters() {
        officeCenters = OfficeCentersModel.entityToModelMapper().apply(service.findAll());
        return officeCenters;
    }
    public String deleteAction(OfficeCentersModel.OfficeCenter officeCenter) {
        service.delete(officeCenter.getId());
        return "office_center_list?faces-redirect=true";
    }

}
