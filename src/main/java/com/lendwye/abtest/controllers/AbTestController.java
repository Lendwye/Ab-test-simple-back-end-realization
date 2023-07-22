package com.lendwye.abtest.controllers;

import com.lendwye.abtest.models.DeviceTokenData;
import com.lendwye.abtest.models.Experiment;
import com.lendwye.abtest.repositories.AbTestRepository;
import com.lendwye.abtest.services.AbTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AbTestController {

    private final AbTestService abTestService;

    @Autowired
    public AbTestController(AbTestService abTestService)
    {
        this.abTestService = abTestService;
    }

    Logger logger = LoggerFactory.getLogger(AbTestController.class);

    @GetMapping("/api")
    public List<Experiment> returnExperiments(@RequestHeader("Device-Token") String deviceToken) {
        DeviceTokenData deviceTokenData;
        if(abTestService.repositoryContainsDeviceToken(Long.parseLong(deviceToken)))
        {
            deviceTokenData = abTestService.getDeviceTokenData(Long.parseLong(deviceToken));
        }
        else
        {
            deviceTokenData = abTestService.saveDeviceTokenData(Long.parseLong(deviceToken));
        }
        return List.of(new Experiment("button_color", deviceTokenData.getButtonColor()), new Experiment("price", deviceTokenData.getPurchaseCost().toString()));
    }
}
