package com.seanroshan.vehicles.api.service.client.pricing.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.seanroshan.vehicles.api.service.client.pricing.entity.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricingControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void postPrice() throws Exception {
        mockMvc.perform(post(new URI("/prices"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new Price(null, "USD", new BigDecimal(1250), 1L)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void gerPrice() throws Exception {
        mockMvc.perform(get(new URI("/prices/1"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("vehicleId").value(1L));
    }

    @Test
    public void putPrice() throws Exception {
        mockMvc.perform(put(new URI("/prices/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new Price(1L, "USD", new BigDecimal(2500), 1L)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get(new URI("/prices/1"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("vehicleId").value(1L))
                .andExpect(jsonPath("price").value(2500));
    }


    @Test
    public void deletePrice() throws Exception {
        mockMvc.perform(delete(new URI("/prices/2"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(new URI("/prices/2"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
