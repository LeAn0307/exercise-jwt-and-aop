package com.example.exercise.interceptor;

import com.example.exercise.service.Impl.DepartmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Component
public class ProductServiceInterceptor implements HandlerInterceptor {
    private final Logger LOGGER =  LoggerFactory.getLogger(ProductServiceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("Pre Handle method is Calling");
        Date date = new Date();
        long timeMilli = date.getTime();
        request.setAttribute("startTime",timeMilli);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Date date = new Date();
        long timeBefore = (long) request.getAttribute("startTime");
        long timeMilli = date.getTime();
        timeMilli-=timeBefore;
        LOGGER.info("Execution: "+ timeMilli+"ms");
        LOGGER.info("Post Handle method is Calling");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("Request and Response is completed");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}