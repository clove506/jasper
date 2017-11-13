package com.youhujia.domain.context.catest;

import com.youhujia.domain.annotation.CATest;
import com.youhujia.domain.context.JasperContext;
import com.youhujia.domain.object.CTest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Created by lilac on 13/11/2017.
 */
@Component
public class TestFactory {

    public void computeTestAOP(CATest caTest, JasperContext context) {
//        if (checkTestInfoAlreadyExists(context, caTest)) {
//            return;
//        }

        computeActual(caTest, context);
    }

    private void computeActual(CATest caTest, JasperContext context) {
        CTest cTest = this.getCtestBasicInfo(caTest);
        BeanUtils.copyProperties(cTest, context.getcTest());
    }

    private boolean checkTestInfoAlreadyExists(JasperContext context, CATest caTest) {
        if (context.getcTest().getNickName() == null) {
            return false;
        }

        if (caTest.needStatus()) {
            if (context.getcTest().getStatus() == null) {
                return false;
            }
        }
        return true;
    }

    public CTest getCtestBasicInfo(CATest caTest) {
        CTest cTest = new CTest();
        if (!caTest.needStatus()) {
            cTest.setStatus(0L);
        } else {
            cTest.setStatus(1L);
        }
        cTest.setNickName("Jasper");
        cTest.setPersonId(700L);
        return cTest;
    }
}
