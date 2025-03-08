package mingjie.kahoot.userservice.config;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mingjie.kahoot.userservice.service.JwtService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
//    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.jwtService = jwtService;
//        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }


    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        // Via request and response object, you can read data and add additional data to request and response
        // filterChain.doFilter() will call the next filter


        // JWT is sent from client in header "Authorization: Bearer <token>"
        // Server only keep the encrypting salt
        // 1. Client register to server (send request) <payload>
        // 2. Server salt encrypt <payload> and send JWT (<header>.<payload>.<signature>)
        // 3. Client sent <header>.<payload>.<signature> to server
        // 4. Server re-calculate the signature based on salt. If verified, what the client says is acceptable

        final String authHeader = request.getHeader("Authorization");

        final String jwt;

        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUserEmail(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);


            if (jwtService.isTokenValid(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());


                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                SecurityContextHolder.getContext().setAuthentication((authenticationToken));
            }
        }


        filterChain.doFilter(request, response);
    }
}
