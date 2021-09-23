package com.grey.matter.rest.api;

import com.grey.matter.rest.model.ModelDescriptor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
public class ModelController {
  private final List<ModelDescriptor> models = new ArrayList<>();

  public ModelController() {
    var model = ModelDescriptor.builder().name("watch").label("Watch").build();
    models.add(model);
  }

  @GetMapping("/models")
  List<ModelDescriptor> getModels() {
    return models;
  }

  @PostMapping(path = "/models", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ModelDescriptor> addModel(@Validated @RequestBody ModelDescriptor pModelDescriptor) {
    if (models.stream().anyMatch(model -> model.getName().equalsIgnoreCase(pModelDescriptor.getName()))) {
      return ResponseEntity.badRequest().build();
    }
    models.add(pModelDescriptor);
    return ResponseEntity.of(Optional.of(pModelDescriptor));
  }
}
