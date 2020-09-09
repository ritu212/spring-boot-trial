package com.spring.trialproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.trialproject.dto.UserDto;
import com.spring.trialproject.model.User;
import com.spring.trialproject.repository.UserRepository;
import com.spring.trialproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TrialProjectController.class)
class TrialProjectControllerTest {

    private MockMvc mockMvc;
    private UserDto userDto;
    private User user;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    UserService userService;

//    @Mock
//    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;

//    @InjectMocks
//    TrialProjectController trialProjectController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userDto = new UserDto();
        userDto.setUserName("user1");
        userDto.setPhoneNumber(98721787928L);
        userDto.setPassword("password");
        user = new User();
        user.setId(1L);
        user.setUserName("user1");
        user.setPhoneNumber(98721787928L);
        user.setPassword("password");
    }

    @Test
    public void testAddUserWithStatus200() throws Exception {
        String body = objectMapper.writeValueAsString(userDto);
        String response = objectMapper.writeValueAsString(user);
        given(userService.saveUserDetails(modelMapper.map(userDto, User.class))).willAnswer(x->x.getArgument(0));
        mockMvc.perform(post("/addUser")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(body)).andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().string(response));
    }
}