package cs.capstone.powerhousing.configs;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public AuthSuccessHandler(String defaultTargetUrl){
        super.setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        boolean isUser = authentication.getAuthorities().stream()
//                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"));
        super.onAuthenticationSuccess(request, response, chain, authentication);
    }


}


