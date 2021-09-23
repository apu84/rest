package com.grey.matter.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grey.matter.rest.model.ModelDescriptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class RestApplicationTests {

  @Autowired
  private MockMvc mMockMvc;

  @Test
  void getModels() throws Exception {
    mMockMvc
        .perform(get("/models"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].name").value("watch"));
  }

  @Test
  void postModel() throws Exception {
    var xyz = ModelDescriptor.builder().name("xyz").label("XYZ").build();
    mMockMvc.perform(post("/models")
        .contentType(MediaType.APPLICATION_JSON)
        .content(convertObjectToJsonBytes(xyz)))
        .andExpect(status().isOk());
  }

  @Test
  void failedPostModel() throws Exception {
    var xyz = ModelDescriptor.builder().name("watch").label("Watch").build();
    mMockMvc.perform(post("/models")
            .contentType(MediaType.APPLICATION_JSON)
            .content(convertObjectToJsonBytes(xyz)))
            .andExpect(status().is4xxClientError());
  }

  public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.writeValueAsBytes(object);
  }

}
