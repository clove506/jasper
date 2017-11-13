package com.youhujia.domain.context;

import com.youhujia.domain.annotation.CATest;
import com.youhujia.domain.context.catest.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author lilac
 * @date 13/11/2017
 */
@Component
public class JasperContextFactory {

    @Autowired
    private TestFactory testFactory;

    private static final String AUTHORIZATION_HALO_CONTEXT = "authorization_halo_context";

    public static JasperContext getJasperContextFromAOP() {
        Object contextRaw = RequestContextHolder.currentRequestAttributes().getAttribute(AUTHORIZATION_HALO_CONTEXT, RequestAttributes.SCOPE_REQUEST);
        if (contextRaw == null) {
            try {
                throw new Exception("用法有误！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new JasperContext();
        } else {
            return (JasperContext) contextRaw;
        }
    }

    public JasperContext buildJasperContextFromRequestContext(CATest contextAnnotation) {
        JasperContext context = getContextFromRequest();
        if (context == null) {
            context = new JasperContext();
            setJasperContext2Requset(context);
        }

        HttpServletRequest request = getCurrentServletRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();

        context.setURI(request.getRequestURI());
        context.setParameterMap(parameterMap);

        computeJasperContext(context, contextAnnotation);

        return context;
    }

    private void setJasperContext2Requset(JasperContext context) {
        RequestContextHolder.currentRequestAttributes().setAttribute(AUTHORIZATION_HALO_CONTEXT, context, RequestAttributes.SCOPE_REQUEST);
    }

    private void computeJasperContext(JasperContext context, CATest contextAnnotation) {
        testFactory.computeTestAOP(contextAnnotation, context);
    }

    private JasperContext getContextFromRequest() {
        Object contextRaw = RequestContextHolder.currentRequestAttributes().getAttribute(AUTHORIZATION_HALO_CONTEXT, RequestAttributes.SCOPE_REQUEST);
        if (contextRaw == null) {
            return null;
        } else {
            return (JasperContext) contextRaw;
        }
    }

    private HttpServletRequest getCurrentServletRequest() {

        final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        final HttpServletRequest request = attr.getRequest();

        return request;

    }
}
