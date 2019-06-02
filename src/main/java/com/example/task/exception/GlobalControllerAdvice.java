package com.example.task.exception;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Global exception handler
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    public static final String DEFAULT_ERROR_VIEW = "error";
    
    @ExceptionHandler(Exception.class)
    public ModelAndView defaultError(HttpServletRequest req, Exception e)
            throws Exception {
        
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        
        return getModel(req, e);
    }
    
    private ModelAndView getModel(HttpServletRequest req, Throwable e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("path", req.getRequestURL());
        mav.addObject("message", e.getMessage());
        mav.addObject("timestamp", new Date());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
    
}
