package com.youhujia.domain.aop;

import com.youhujia.domain.annotation.CATest;
import com.youhujia.domain.context.JasperContextFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by lilac on 13/11/2017.
 */
@Aspect
@Component
public class AuthorizationContextCompleteAOP {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JasperContextFactory jasperContextFactory;

    @Before("@annotation(com.youhujia.domain.annotation.CATest)")
    public void testContextAdvice(JoinPoint call) throws Throwable {
        jasperContextAdviceContentMaker(call, CATest.class);

    }

    private void jasperContextAdviceContentMaker(JoinPoint call, Class<CATest> caTestClass) throws Exception {
        String where = "AuthorizationContextCompleteAOP->HaloContextAdviceContentMaker";

        try {
            CATest t = getHaloAOPAnnotation(call, caTestClass);
            jasperContextFactory.buildJasperContextFromRequestContext(t);
        } catch (Exception e) {
            logger.error(where + " unknown error", e); //todo
            throw new Exception("AOP Build Fail check terminal for detail");
        }
    }

    private CATest getHaloAOPAnnotation(JoinPoint call, Class<CATest> annotationClass) {
        MethodSignature signature = (MethodSignature) call.getSignature();
        Method method = signature.getMethod();

        return method.getAnnotation(annotationClass);
    }


}
