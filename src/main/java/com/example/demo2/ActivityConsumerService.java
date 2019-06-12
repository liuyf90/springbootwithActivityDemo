package com.example.demo2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘宇飞 on 2019-06-12.
 */

@RestController
@RequestMapping("/activityService")
public interface ActivityConsumerService {
    /**
     * 流程demo
     *
     * @return
     */
    @RequestMapping(value = "/startActivityDemo", method = RequestMethod.GET)
    public boolean startActivityDemo();


}