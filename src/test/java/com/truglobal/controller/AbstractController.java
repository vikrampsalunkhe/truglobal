package com.truglobal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truglobal.dto.ResponseDto;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractController {


    @Autowired
    protected MockMvc mockMvc;

    protected static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static ResponseDto convertToResponseDto(String jsonString) {
        try {
            return new ObjectMapper().readValue(jsonString, ResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
