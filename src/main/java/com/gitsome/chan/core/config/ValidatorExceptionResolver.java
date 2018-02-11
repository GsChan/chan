package com.gitsome.chan.core.config;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 异常的拦截处理
 */
public class ValidatorExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception ex) {
        if (ex instanceof ConstraintViolationException || ex instanceof MethodArgumentNotValidException || ex instanceof BindException || ex instanceof MethodArgumentTypeMismatchException || ex instanceof HttpMessageNotReadableException) {
            handleValidationException(ex);
        }
        // Expose ModelAndView for chosen error viewcontroller.
        return super.doResolveException(request, response, handler, ex);
    }

    /**
     * 处理参数校验错误
     *
     * @param ex
     * @return
     * @throws java.io.IOException
     */
    protected void handleValidationException(Exception ex) {
        StringBuilder errMsg = new StringBuilder();
        // Spring Validation
        if (ConstraintViolationException.class.isAssignableFrom(ex.getClass())) {
            ConstraintViolationException exception = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
            if (violations != null) {
                for (Iterator<ConstraintViolation<?>> iterator = violations.iterator(); iterator.hasNext(); ) {
                    ConstraintViolation<?> next = iterator.next();
//                    errMsg.append(SR.format(WafResource.VALIDATOR_EXCEPTION, getParameterName(next), next.getMessage()));
                }
            }
        }
        // Spring Mvc Bind Validation
        else if (MethodArgumentNotValidException.class.isAssignableFrom(ex.getClass())) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            errMsg.append(transformBindingResult(exception.getBindingResult()));
        }
        // Spring Mvc Bind Validation
        else if (BindException.class.isAssignableFrom(ex.getClass())) {
            BindException exception = (BindException) ex;
            errMsg.append(transformBindingResult(exception));
        }
        // Spring Mvc Bind Validation
        else if (MethodArgumentTypeMismatchException.class.isAssignableFrom(ex.getClass())) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
//            errMsg.append(SR.format(WafResource.VALIDATOR_EXCEPTION, exception.getName(), exception.getValue()));
        }else if(HttpMessageNotReadableException.class.isAssignableFrom(ex.getClass())){
            HttpMessageNotReadableException exception = (HttpMessageNotReadableException) ex;
            errMsg.append(getNotReadableMsg(exception));
        }
//        throw new ArgumentValidationException(errMsg.toString());
    }

    /**
     * 转换BindingResult为验证的Result
     *
     * @param bindingResult
     * @return
     */
    private String transformBindingResult(BindingResult bindingResult) {
        StringBuilder errMsg = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (int i = 0; i < fieldErrors.size(); i++) {
            FieldError fieldError = fieldErrors.get(i);
//            errMsg.append(SR.format(WafResource.VALIDATOR_EXCEPTION, fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return errMsg.toString();
    }

    /**
     * 通过错误取得参数名称
     *
     * @param violation
     * @return 参数名称
     */
    private String getParameterName(ConstraintViolation<?> violation) {
        try {
            Path.MethodNode methodNode = null;
            Path.ParameterNode parameterNode = null;
            Path.PropertyNode propertyNode = null;
            Iterator<Path.Node> iterator = violation.getPropertyPath().iterator();
            while (iterator.hasNext()) {
                Path.Node node = iterator.next();
                if (ElementKind.METHOD.equals(node.getKind())) {
                    methodNode = (Path.MethodNode) node;
                } else if (ElementKind.PARAMETER.equals(node.getKind())) {
                    parameterNode = (Path.ParameterNode) node;
                } else if (ElementKind.PROPERTY.equals(node.getKind())) {
                    propertyNode = (Path.PropertyNode) node;
                }
            }
            if (propertyNode != null) {
                return propertyNode.getName();
            }
            if (methodNode == null || parameterNode == null) {
                return null;
            }
            Method method = violation.getRootBeanClass().getMethod(methodNode.getName(), methodNode.getParameterTypes().toArray(new Class[0]));
            LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            return parameterNames[parameterNode.getParameterIndex()];
        } catch (NoSuchMethodException e) {
            logger.warn("Get parameter error!", e);
        }
        return null;
    }

    /**
     * 不可读异常进入最底层读取信息, 抛出
     * @return
     */
    private String getNotReadableMsg(Exception ex){
        if(ex.getCause() == null){
            return ex.getMessage();
        }else {
            return getNotReadableMsg((Exception) ex.getCause());
        }
    }
}
