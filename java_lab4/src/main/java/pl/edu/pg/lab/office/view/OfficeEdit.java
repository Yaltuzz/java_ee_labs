package pl.edu.pg.lab.office.view;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.office.model.OfficeEditModel;
import pl.edu.pg.lab.office.service.OfficeService;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.officeCenter.model.OfficeCenterEditModel;
import pl.edu.pg.lab.officeCenter.service.OfficeCenterService;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

/**
 * View bean for rendering single character edit form.
 */
@ViewScoped
@Named
public class OfficeEdit implements Serializable {

    private final OfficeService service;
    @Setter
    @Getter
    private int id;

    @Getter
    private OfficeEditModel office;


    @Inject
    public OfficeEdit(OfficeService service) {
        this.service = service;
    }

    public void init() throws IOException {
        Optional<Office> office = service.find(id);
        if (office.isPresent()) {
            this.office = OfficeEditModel.entityToModelMapper().apply(office.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "Character not found");
        }
    }

    public String saveAction() {
        service.update(OfficeEditModel.modelToEntityUpdater().apply(service.find(id).orElseThrow(), office));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }


}
