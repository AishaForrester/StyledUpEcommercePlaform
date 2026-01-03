package com.ecommerce.styledup;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Get the user's role
        String role = authentication.getAuthorities().stream()
                            .findFirst()
                            .map(auth -> auth.getAuthority())
                            .orElse("");

        if ("SELLER".equals(role)) {
            response.sendRedirect("/dashboardSeller");
        } else if ("BUYER".equals(role)) {
            response.sendRedirect("/dashboardBuyer");
        } else {
            response.sendRedirect("/"); // fallback
        }
    }
}
