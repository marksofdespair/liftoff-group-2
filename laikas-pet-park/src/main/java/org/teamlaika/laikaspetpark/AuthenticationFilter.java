package org.teamlaika.laikaspetpark;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.teamlaika.laikaspetpark.controllers.AuthenticationController;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.UserRepository;

import java.io.IOException;

public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        // The user is logged in
        if (user != null) {
            return true;
        }

        // The user is NOT logged in
        response.sendRedirect("/login");
        return false;
    }
}
