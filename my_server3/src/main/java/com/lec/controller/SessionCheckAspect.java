package com.lec.controller;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import com.lec.domain.Member;

@Configuration
@EnableAsync
@Aspect
public class SessionCheckAspect {

    @Autowired
    private HttpSession session;

    @Around("within(com.lec.controller.AdminController)")
    public Object checkSession(ProceedingJoinPoint joinPoint) throws Throwable {
    	Object loginss = session.getAttribute("loginss");
        if (loginss == null) {
            return "redirect:login_admin";
        } else {
            Member member = (Member) loginss;
            String role = member.getRole();
            if ("USER".equals(role)) {
                return "redirect:no_access";
            } else {
                return joinPoint.proceed();
            }
        }
    }
}