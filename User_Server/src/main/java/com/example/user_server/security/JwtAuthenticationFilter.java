package com.example.user_server.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Những api không cần check
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response); // lọc và thoát
            return;
        }

        // Kiểm tra có sử dụng Authorization không?
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // lọc và thoát
            return;
        }

        // Lấy jwt từ request
        final String jwt = authHeader.substring(7);

        // Lấy username từ chuỗi jwt
        final String userEmail = jwtService.extractUsername(jwt);
        // Lấy UserDetails từ username
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

        // Thực hiện kiểm tra token
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Nếu token hợp lệ thì thêm vào thông tin cho Seturity Context
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response); // lọc và thoát
        return;
    }
}
