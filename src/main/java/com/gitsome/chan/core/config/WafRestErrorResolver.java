package com.gitsome.chan.core.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现对异常进行处理，以 JSON 响应异常信息
 *
 * @author vime
 * @since 0.9.6
 */

public class WafRestErrorResolver implements HandlerExceptionResolver {


    /**
     * Try to resolve the given exception that got thrown during handler execution,
     * returning a {@link ModelAndView} that represents a specific error page if appropriate.
     * <p>The returned {@code ModelAndView} may be {@linkplain ModelAndView#isEmpty() empty}
     * to indicate that the exception has been resolved successfully but that no view
     * should be rendered, for instance by setting a status code.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the executed handler, or {@code null} if none chosen at the
     *                 time of the exception (for example, if multipart resolution failed)
     * @param ex       the exception that got thrown during handler execution
     * @return a corresponding {@code ModelAndView} to forward to, or {@code null}
     * for default processing
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        WafException wafException ;
        if(ex instanceof WafException){
            wafException = (WafException)ex;
        }else{
            //如果抛出的不是系统自定义异常则重新构造一个系统错误异常。
            wafException = new WafException("Exception/Can not catch","系统错误，请与系统管理 员联系！");
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code", wafException.getError().getCode());
        modelAndView.addObject("message", wafException.getError().getMessage());
        modelAndView.addObject("cause", wafException.getError().getCause());
        modelAndView.addObject("detail", wafException.getError().getDetail());
//        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
//        modelAndView.setViewName("error");

        return modelAndView;
    }



    public boolean process(Throwable throwable, HttpServletRequest request, HttpServletResponse response) {
        return this.resolver(throwable, request, response);
    }

    public boolean resolver(Throwable throwable, HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(throwable);
        Assert.notNull(request);
        Assert.notNull(response);
//
//        ResponseEntity<ResponseErrorMessage> responseEntity = process(throwable, request);
//        response.setStatus(responseEntity.getStatusCode().value());
//        if (response.getStatus()>=HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//            //只打印服务端错误
////            logger.error(responseEntity.getBody().toString(), throwable);
//        }
//
//        HttpHeaders headers = responseEntity.getHeaders();
//        if (headers != null) {
//            for (Map.Entry<String, String> entry : headers.toSingleValueMap().entrySet()) {
//                response.setHeader(entry.getKey(), entry.getValue());
//            }
//        }
//        try {
//            Response r = new Response(responseEntity.getBody());
////            response.setContentType(WafConstants.APPLICATION_JSON_UTF8);
//            response.setContentType("application/json;charset=UTF-8");
//            PrintWriter writer = response.getWriter();
////            writer.print(WafJsonMapper.toJson(r));
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return true;
    }



    private static class Response {
        public Response(ResponseErrorMessage errorMessage) {
            hostId = errorMessage.getHostId();
            requestId = errorMessage.getRequestId();
            serverTime = errorMessage.getServerTime();
            code = errorMessage.getCode();
            message = errorMessage.getMessage();
//            if (WafContext.isDebugMode()) {
                detail = errorMessage.getDetail();
                cause = errorMessage.getCause();
//            }
        }

        public String hostId;
        public String requestId;
        public Date serverTime;
        public String code;
        public String message;
        public String detail;
        public ResponseErrorMessage cause;
    }
}
