package gr.hua.dit.ErasmusRequest.config;

import gr.hua.dit.ErasmusRequest.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Value("${app.react.proxy}")
    String redirectURL;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();

        System.out.println(userDetails.getUsername());
        Collection<? extends GrantedAuthority> authorities= userDetails.getAuthorities();

        authorities.forEach(auth -> System.out.println(auth.getAuthority()));

        if(Objects.equals(userDetails.hasRole(), "ADMIN")){
            redirectURL +="admin/users/register";

        }
        if(Objects.equals(userDetails.hasRole(), "STUDENT")){
            redirectURL +="request/form";
        }
        if(Objects.equals(userDetails.hasRole(), "TEACHER")){
            redirectURL +="requests/sendTo";
        }

        response.sendRedirect(redirectURL);
    }
}
