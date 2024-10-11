package pl.edu.pg.lab.officeCenter.servlet;
import pl.edu.pg.lab.officeCenter.dto.GetOfficeCenterResponse;
import pl.edu.pg.lab.officeCenter.dto.GetOfficeCentersResponse;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.officeCenter.service.OfficeCenterService;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/api/officeCenter/*")
public class OfficeCenterServlet extends HttpServlet {

    private OfficeCenterService service;

    @Inject
    public OfficeCenterServlet(OfficeCenterService service) {
        this.service = service;
    }
    private final Jsonb jsonb = JsonbBuilder.create();
    public static class Patterns {
        public static final String OFFICE_CENTERS = "^/?$";
        public static final String OFFICE_CENTER = "^/[a-z,0-9]+/?$";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String path = request.getPathInfo();

        if (path.matches(Patterns.OFFICE_CENTER)) {
            response.setContentType("application/json");
            String parameter  = path.replaceAll("/", "");
            Optional<OfficeCenter> officeCenter = service.find(Integer.parseInt(parameter));
            if (officeCenter.isPresent()) {
                response.getWriter().write(jsonb.toJson(GetOfficeCenterResponse.entityToDtoMapper().apply(officeCenter.get())));
                return;
            }
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } else if (path.matches(Patterns.OFFICE_CENTERS)) {
            response.setContentType("application/json");
            response.getWriter()
                    .write(jsonb.toJson(GetOfficeCentersResponse.entityToDtoMapper().apply(service.findAll())));
            return;
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
