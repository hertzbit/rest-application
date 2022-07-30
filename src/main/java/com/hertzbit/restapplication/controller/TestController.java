package com.hertzbit.restapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/api/test")
public class TestController {

    @GetMapping("/multiValue")
    public ResponseEntity<List<String>> returnAllMultiValueQueryParam (@RequestParam("params")
                                                                           List<String> queryParamsList) {

        List<String> responseList = new ArrayList<>();
        for (String eachString : queryParamsList) {
            responseList.add(eachString.toUpperCase());
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
