package com.db.bts.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bts/api")
public class BtsApiController {

    @GetMapping()
    public ResponseEntity<Object> healthCheck() {
        return ResponseEntity.ok("OK");
    }

}
