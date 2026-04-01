package com.drivewealth.configflow.controller;

import com.drivewealth.configflow.model.ConfigEntry;
import com.drivewealth.configflow.service.ConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConfigService configService;

    @Test
    void getConfig_returns200_whenFound() throws Exception {
        ConfigEntry entry = new ConfigEntry("feature.newCheckout", "true", 10, "tester", 1L);
        when(configService.get("feature.newCheckout")).thenReturn(entry);

        mockMvc.perform(get("/admin/config/feature.newCheckout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("feature.newCheckout"))
                .andExpect(jsonPath("$.value").value("true"))
                .andExpect(jsonPath("$.rolloutPercent").value(10));
    }

    @Test
    void getConfig_returns404_whenNotFound() throws Exception {
        when(configService.get("missing.key")).thenReturn(null);

        mockMvc.perform(get("/admin/config/missing.key"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listAll_returns200_withEntries() throws Exception {
        ConfigEntry entry = new ConfigEntry("feature.newCheckout", "true", 10, "tester", 1L);
        when(configService.getAll()).thenReturn(List.of(entry));

        mockMvc.perform(get("/admin/configs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].key").value("feature.newCheckout"));
    }

    @Test
    void upsert_returns200_withUpdatedEntry() throws Exception {
        ConfigEntry entry = new ConfigEntry("feature.newCheckout", "true", 50, "tester", 1L);
        when(configService.upsert("feature.newCheckout", "true", 50, "tester")).thenReturn(entry);

        mockMvc.perform(post("/admin/config/feature.newCheckout")
                        .contentType(APPLICATION_JSON)
                        .content("{\"value\":\"true\",\"rolloutPercent\":50,\"updatedBy\":\"tester\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rolloutPercent").value(50));
    }

    @Test
    void upsert_usesDefaults_whenFieldsMissing() throws Exception {
        ConfigEntry entry = new ConfigEntry("feature.newCheckout", "true", 0, "console", 1L);
        when(configService.upsert("feature.newCheckout", "true", 0, "console")).thenReturn(entry);

        mockMvc.perform(post("/admin/config/feature.newCheckout")
                        .contentType(APPLICATION_JSON)
                        .content("{\"value\":\"true\"}"))
                .andExpect(status().isOk());
    }
}
