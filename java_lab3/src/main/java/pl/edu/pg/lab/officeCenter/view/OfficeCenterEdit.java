package pl.edu.pg.lab.officeCenter.view;

import lombok.Getter;
import lombok.Setter;
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
public class OfficeCenterEdit implements Serializable {

    private final OfficeCenterService service;
    @Setter
    @Getter
    private int id;

    @Getter
    private OfficeCenterEditModel officeCenter;


    @Inject
    public OfficeCenterEdit(OfficeCenterService service) {
        this.service = service;
    }

    public void init() throws IOException {
        Optional<OfficeCenter> officeCenter = service.find(id);
        if (officeCenter.isPresent()) {
            this.officeCenter = OfficeCenterEditModel.entityToModelMapper().apply(officeCenter.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "Character not found");
        }
    }

    public String saveAction() {
        service.update(OfficeCenterEditModel.modelToEntityUpdater().apply(service.find(id).orElseThrow(), officeCenter));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }


}
