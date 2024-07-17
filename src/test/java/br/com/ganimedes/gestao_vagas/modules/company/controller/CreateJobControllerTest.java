package br.com.ganimedes.gestao_vagas.modules.company.controller;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.ganimedes.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.ganimedes.gestao_vagas.utils.TestUtils;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {
  
  private  MockMvc mockMvc;

  @Autowired
  private WebApplicationContext context;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(SecurityMockMvcConfigurers.springSecurity())
      .build();

  }

  @Test
  public void should_be_able_to_create_a_job() throws Exception {
      var createJobDTO = CreateJobDTO.builder()
        .description("Vaga para pessoa desenvolvedora júnior")
        .level("JUNIOR")
        .benefits("GymPass, Plano de saúde")
        .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/company/job")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtils.objectToJSON(createJobDTO))
        .header("Authorization", TestUtils.generateToken(UUID.fromString("2f0b8f0b-1c0f-4c8c-9f0b-0b1c0f2f0b0b"))))
        .andExpect(MockMvcResultMatchers.status().isOk());
        
  }

 
}
