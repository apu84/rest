package com.grey.matter.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ModelController {
  @GetMapping("/models")
  String getModels() {
    return "watch";
  }
}
