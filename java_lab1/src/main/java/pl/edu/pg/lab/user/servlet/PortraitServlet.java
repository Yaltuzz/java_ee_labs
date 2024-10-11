package pl.edu.pg.lab.user.servlet;

import pl.edu.pg.lab.user.entity.User;
import pl.edu.pg.lab.user.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = PortraitServlet.Paths.PORTRAITS + "/*")
@MultipartConfig(maxFileSize = 200 * 1024)
public class PortraitServlet extends HttpServlet {

    private UserService service;

    @Inject
    public PortraitServlet(UserService service) {
        this.service = service;
    }
    public static class Paths {
        public static final String PORTRAITS = "/api/portraits";

    }
    public static class Patterns {
        public static final String PORTRAIT = "^/[a-z]+/?$";
    }
    public static class Parameters {
        public static final String PORTRAIT = "portrait";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        String servletPath = request.getServletPath();
        if (Paths.PORTRAITS.equals(servletPath)) {
            if (path.matches(Patterns.PORTRAIT)) {
                String login = request.getPathInfo().replaceAll("/", "");
                Optional<User> user = service.find(login);
                if (user.isPresent() && user.get().getPortrait()!=null) {
                    response.addHeader(HttpHeaders.CONTENT_TYPE, "image/png");
                    response.setContentLength(user.get().getPortrait().length);
                    response.getOutputStream().write(user.get().getPortrait());
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        String servletPath = request.getServletPath();
        if (Paths.PORTRAITS.equals(servletPath)) {
            if (path.matches(Patterns.PORTRAIT)) {
                String login = path.replaceAll("/", "");
                Optional<User> user = service.find(login);
                if (user.isPresent()) {
                    Part portrait = request.getPart(Parameters.PORTRAIT);
                    if (portrait != null) {
                        service.updatePortrait(login, portrait.getInputStream());
                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                        return;
                    }else{
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);

                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        String servletPath = request.getServletPath();
        if (Paths.PORTRAITS.equals(servletPath)) {
            if (path.matches(Patterns.PORTRAIT)) {
                String login = path.replaceAll("/", "");
                Optional<User> user = service.find(login);
                if (user.isPresent()) {
                    service.deletePortrait(login);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

}
