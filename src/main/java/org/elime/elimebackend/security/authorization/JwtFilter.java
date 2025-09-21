package org.elime.elimebackend.security.authorization;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.enumerators.Token;
import org.elime.elimebackend.security.authentication.JwtHandler;
import org.elime.elimebackend.service.implementations.UserDetailServiceImpl;
import org.elime.elimebackend.service.interfaces.BlacklistService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@NonNullApi
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtHandler jwtHandler;
    private final BlacklistService blacklistService;
    private final UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = jwtHandler.getJwtFromRequest(request, Token.ACCESS);

        if(!jwt.equals("invalid") && !blacklistService.isTokenBlackListed(jwt) && jwtHandler.isValidAccessToken(jwt)) {
            String emailFromToken = jwtHandler.getEmailFromToken(jwt);
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetailService.loadUserByUsername(emailFromToken);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    customUserDetails, null, customUserDetails.getAuthorities()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
