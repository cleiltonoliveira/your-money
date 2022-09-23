package com.yourmoney.web.test.controller;

import com.yourmoney.domain.model.types.Category;
import com.yourmoney.web.controller.expense.dto.ExpenseRequestDto;
import com.yourmoney.web.test.MongoDBTestContainerConfig;
import com.yourmoney.web.test.SetupWebTest;
import com.yourmoney.web.test.helper.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SetupWebTest
@Slf4j
public class ExpenseControllerTest extends MongoDBTestContainerConfig {

    @Autowired
    private MockMvc mockMvc;

    private ExpenseRequestDto buildRequestModel() {
        var model = new ExpenseRequestDto();
        model.setAmount(BigDecimal.valueOf(100));
        model.setDate(LocalDate.of(2022, 8, 10));
        model.setCategory(Category.ALIMENTACAO);
        model.setDescription("Churrasco do final de semana");
        return model;
    }

    @Test
    @DisplayName("create should be success and return status code 201")
    public void createShouldReturnStatusCode201() throws Exception {
        var model = buildRequestModel();
        mockMvc.perform(post("/api/v1/despesas")
                        .content(JsonHelper.asJsonString(model))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value(model.getDescription()))
                .andExpect(jsonPath("$.amount").value(model.getAmount()))
                .andExpect(jsonPath("$.date").value(model.getDate().toString()))
                .andDo(result -> log.info("RESPONSE PAYLOAD "+result.getResponse().getContentAsString()));
    }
}
