package com.grey.matter.rest.api;

import com.grey.matter.rest.model.ModelDescriptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class ModelController {
  @GetMapping("/models")
  List<ModelDescriptor> getModels() {
    var model = ModelDescriptor.builder().name("watch").label("Watch").build();
    return List.of(model);
  }
}
