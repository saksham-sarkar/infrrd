package com.example.demo.controller;

import com.example.demo.service.APIService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Controller
@Tag(name = "APIs", description = "The APIs for start and end.")
@RestController
public class APIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIController.class);

    final APIService apiService;

    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(value = "/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> start() throws ExecutionException, InterruptedException {
        Random rand = new Random();
        int max=100;
        int min=1;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        LOGGER.info("Number :::::::::: " + randomNum);
        Future<String> futureResult =  apiService.startMethod(randomNum);
        String result = futureResult.get();
        return Collections.singletonMap("response", result);
    }

    @GetMapping(value = "/end", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> end() throws ExecutionException, InterruptedException {
        Future<String> futureResult =  apiService.endMethod();
        String result = futureResult.get();
        return Collections.singletonMap("response", result);
    }

}
