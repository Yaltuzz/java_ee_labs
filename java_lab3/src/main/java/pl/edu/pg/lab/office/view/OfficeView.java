package pl.edu.pg.lab.office.view;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.office.model.OfficeModel;
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
public class OfficeView implements Serializable {

    private final OfficeService service;


    @Setter
    @Getter
    private int id;

    private OfficeModel office;

    @Inject
    public OfficeView(OfficeService service) {
        this.service = service;
    }

    public OfficeModel getOffice() {
        Optional<Office> office = service.find(id);
        if (office.isPresent()) {
            this.office = OfficeModel.entityToModelMapper().apply(office.get());
            return this.office;
        }
        return null;
    }

    public String deleteAction(int id) {
        service.delete(id);
        return "office_center_list?faces-redirect=true";
    }
}
