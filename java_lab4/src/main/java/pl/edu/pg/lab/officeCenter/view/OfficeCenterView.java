package pl.edu.pg.lab.officeCenter.view;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.office.service.OfficeService;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.officeCenter.model.OfficeCenterModel;
import pl.edu.pg.lab.officeCenter.model.OfficeCentersModel;
import pl.edu.pg.lab.officeCenter.service.OfficeCenterService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@ViewScoped
@Named
public class OfficeCenterView implements Serializable {

    private final OfficeCenterService service;
    private final OfficeService officeService;


    @Setter
    @Getter
    private int id;

    private OfficeCenterModel officeCenter;

    @Inject
    public OfficeCenterView(OfficeCenterService service,OfficeService officeService) {
        this.service = service;
        this.officeService = officeService;
    }

    public OfficeCenterModel getOfficeCenter() {
        Optional<OfficeCenter> officeCenter = service.find(id);
        if (officeCenter.isPresent()) {
            this.officeCenter = OfficeCenterModel.entityToModelMapper().apply(officeCenter.get());
            return this.officeCenter;
        }
        return null;
    }

    public String deleteAction(int id) {
        officeService.delete(id);
        return "office_center_list?faces-redirect=true";
    }
}
