package com.github.sfidencio.exemplo.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sfidencio.exemplo.application.dto.ProductRequest;
import com.github.sfidencio.exemplo.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ModelMapper modelMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.openMocks(this);
        ProductController productController = new ProductController(productService, modelMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    @DisplayName("Should create product successfully")
    public void shouldCreateProductSuccessfully() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setDescription("Test Product");
        productRequest.setPrice(new BigDecimal(7.99));
        productRequest.setStock(new BigDecimal(200));

        ObjectMapper objectMapper = new ObjectMapper();
        String productRequestJson = objectMapper.writeValueAsString(productRequest);


        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should not create product when description is null")
    public void shouldNotCreateProductWhenDescriptionIsNull() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setPrice(new BigDecimal(7.99));
        productRequest.setStock(new BigDecimal(200));

        ObjectMapper objectMapper = new ObjectMapper();
        String productRequestJson = objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should not create product when price less than 1.99")
    public void shouldNotCreateProductWhenPriceLessThan199() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setDescription("Test Product");
        productRequest.setPrice(new BigDecimal(1.98));
        productRequest.setStock(new BigDecimal(200));

        ObjectMapper objectMapper = new ObjectMapper();
        String productRequestJson = objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestJson))
                .andExpect(status().isBadRequest());
    }

}