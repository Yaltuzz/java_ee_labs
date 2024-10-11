package pl.edu.pg.lab.user.servlet;

import pl.edu.pg.lab.user.dto.GetUserResponse;
import pl.edu.pg.lab.user.dto.GetUsersResponse;
import pl.edu.pg.lab.user.entity.User;
import pl.edu.pg.lab.user.service.UserService;

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

@WebServlet(urlPatterns = "/api/user/*")
public class UserServlet extends HttpServlet {

    private UserService service;

    @Inject
    public UserServlet(UserService service) {
        this.service = service;
    }
    private final Jsonb jsonb = JsonbBuilder.create();
    public static class Patterns {
        public static final String USERS = "^/?$";
        public static final String USER = "^/[a-z]+/?$";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String path = request.getPathInfo();

        if (path.matches(Patterns.USER)) {
            response.setContentType("application/json");
            String login = path.replaceAll("/", "");
            Optional<User> user = service.find(login);
            if (user.isPresent()) {
                response.getWriter().write(jsonb.toJson(GetUserResponse.entityToDtoMapper().apply(user.get())));
                return;
            }
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } else if (path.matches(Patterns.USERS)) {
            response.setContentType("application/json");
            response.getWriter()
                    .write(jsonb.toJson(GetUsersResponse.entityToDtoMapper().apply(service.findAll())));
            return;
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
