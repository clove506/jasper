package com.youhujia.domain.test;

import com.youhujia.domain.object.CTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lilac on 13/11/2017.
 */
@RestController
public class TestController {

    @Autowired
    private TaskBO taskBO;

    @RequestMapping(value = "/api/tasks/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> queryTaskById(@PathVariable("id") Long id) {

        CTest test = taskBO.queryTaskById(id);
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if (test != null) {
            sb.append(test.getNickName());
            sb.append(test.getPersonId());
            sb.append(test.getStatus());
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);

    }
}

