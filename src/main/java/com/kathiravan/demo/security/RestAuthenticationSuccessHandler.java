package com.kathiravan.demo.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kathiravan.demo.entity.ToDo;
import com.kathiravan.demo.entity.User;
import com.kathiravan.demo.repository.UserRepository;

/**
 * Spring Security success handler, specialized for Ajax requests.
 */
@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {
        User user = userService.findByEmail(authentication.getName());
        user.setTodos(new ArrayList<ToDo>());
        user.setPassword("");
        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, user);
    }
}