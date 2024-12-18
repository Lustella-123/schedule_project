package com.sparta.schedule.Lv7.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/", "/user/signup", "/login", "/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if (isAuthenticationRequired(requestURI)) {
                HttpSession session = httpRequest.getSession(false);

                if (session == null || session.getAttribute("sessionKey") == null) {
                    log.info("인증되지 않은 요청: {}", requestURI);
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("로그인 필터 오류", e);
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류");
        }
    }


    private boolean isAuthenticationRequired(String requestURI) {
        for (String whiteListPath : WHITE_LIST) {
            if (requestURI.equals(whiteListPath)) {
                return false;
            }
        }
        return true;
    }
}