package org.teamlaika.laikaspetpark;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.teamlaika.laikaspetpark.controllers.AuthenticationController;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.UserRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements HandlerInterceptor {





//    private static final List<String> whitelist = Arrays.asList("","/login", "/register", "/logout");
//
//    private static boolean isWhitelisted(String path) {
//        for (String pathRoot : whitelist) {
//            if (path.startsWith(pathRoot)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) throws IOException {
//
//
//        if (isWhitelisted(request.getRequestURI())) {
//
//            return true;
//        }
//
//
//
//        if (JwtGenerator.verifyAndGetUserAndRole().equals(true)) {
//            return true;
//        }
//
//
//        else{
//            return false;
//        }
//    }
}
