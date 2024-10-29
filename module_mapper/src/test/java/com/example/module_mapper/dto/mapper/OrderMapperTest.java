package com.example.module_mapper.dto.mapper;

import com.example.module_mapper.dto.Order;
import com.example.module_mapper.dto.OrderDTO;
import com.example.module_mapper.dto.Product;
import com.example.module_mapper.dto.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMapperTest {

    @Autowired
    private OrderMapperImpl orderMapper;

    private Product product;
    private Product product2;
    private ProductDTO productDTO;
    private ProductDTO productDTO2;

    private Order order;

    private OrderDTO orderDTO;

    @BeforeEach
    void setUpProduct() {
        //given
        product = Product.builder()
                .productId(1L)
                .name("Antenna")
                .build();
    }

    @BeforeEach
    void setUpProductDTO() {
        //given
        productDTO = ProductDTO.builder()
                .productId(1L)
                .productName("Antenna")
                .build();
    }

    @BeforeEach
    void setUpOrder() {
        //given
        product = Product.builder()
                .productId(1L)
                .name("BikeCycle")
                .build();

        product2 = Product.builder()
                .productId(2L)
                .name("Auto")
                .build();

        order = Order.builder()
                .orderId(1L)
                .products(List.of(product, product2))
                .build();
    }

    @BeforeEach
    void setUpOrderDTO() {
        //given
        productDTO = ProductDTO.builder()
                .productId(1L)
                .productName("BikeCycle")
                .build();

        productDTO2 = ProductDTO.builder()
                .productId(2L)
                .productName("Auto")
                .build();

        orderDTO = OrderDTO.builder()
                .orderId(1L)
                .productDTOs(List.of(productDTO, productDTO2))
                .build();
    }

    @Test
    void productToProductDTO() {
        ProductDTO mapper = orderMapper.productToProductDTO(product);

        assertNotNull(mapper);
        assertEquals(productDTO.getProductId(), mapper.getProductId());
        assertEquals(productDTO.getProductName(), mapper.getProductName());
    }

    @Test
    void productsToProductDTOs() {
        //when
        List<ProductDTO> mapper = orderMapper.productsToProductDTOs(List.of(product,
                product2));
        //then
        assertNotNull(mapper);
        assertEquals(2, mapper.size());
        assertEquals(product.getProductId(), mapper.get(0).getProductId());
        assertEquals(product2.getProductId(), mapper.get(1).getProductId());
    }

    @Test
    void orderToOrderDTO() {
        //when
        OrderDTO mapper = orderMapper.orderToOrderDTO(order);
        //then
        assertNotNull(mapper);
        assertEquals(orderDTO, mapper);
    }
}