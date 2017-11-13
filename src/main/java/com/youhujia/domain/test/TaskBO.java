package com.youhujia.domain.test;

import com.youhujia.domain.annotation.CATest;
import com.youhujia.domain.context.JasperContext;
import com.youhujia.domain.context.JasperContextFactory;
import com.youhujia.domain.object.CTest;
import org.springframework.stereotype.Component;

/**
 * Created by lilac on 13/11/2017.
 */
@Component
public class TaskBO {

    @CATest(needStatus = true)
    public CTest queryTaskById(Long id) {
        JasperContext jasperContext = JasperContextFactory.getJasperContextFromAOP();
        if (jasperContext.getcTest() == null) {
            return null;
        }
        return jasperContext.getcTest();
    }
}
