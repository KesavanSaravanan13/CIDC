package com.TicketManagement.TrainTicket.config;

import com.TicketManagement.TrainTicket.dto.Response;
import com.TicketManagement.TrainTicket.entity.ResponseMessage;
import com.TicketManagement.TrainTicket.service.JWTService;
import com.TicketManagement.TrainTicket.service.MyUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final ApplicationContext context;

    @Getter
    @Setter
    private UserDetails userDetails;


    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = this.jwtService.extractUserName(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                setUserDetails(this.context.getBean(MyUserDetailsService.class).loadUserByUsername(username));
                if (this.jwtService.validateToken(token, this.userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(this.userDetails, null, this.userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Long errorCode = (long) response.getStatus();
            String errorStatus = String.valueOf(HttpStatus.BAD_REQUEST);
            ResponseMessage ResponseMessage = new ResponseMessage();
            ResponseMessage.setCode(errorCode);
            ResponseMessage.setMessage("Token Expired - " + e.getMessage());
            ResponseMessage.setStatus("error");
            ResponseMessage.setRequestedTime(Instant.now().toEpochMilli());
            ResponseMessage.setValidationErrors(errorStatus);
            Response response01 = new Response();
            response01.setUser(null);
            ResponseMessage.setResponse(null);

            ObjectMapper objectMapper = new ObjectMapper();

            String jsonResponse = objectMapper.writeValueAsString(ResponseMessage);

            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized access");
        }
    }
}
