package com.example.module_mapper.service;

import com.example.module_mapper.dto.Order;
import com.example.module_mapper.dto.OrderDTO;
import com.example.module_mapper.dto.Product;
import com.example.module_mapper.dto.ProductDTO;
import com.example.module_mapper.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    public ProductDTO getMapperProductToProductDTO(Product product) {
        ProductDTO productDTO = orderMapper.productToProductDTO(product);
        return productDTO;
    }

    public List<ProductDTO> getMapperProductsToProductDTOs(List<Product> products) {
        List<ProductDTO> productsDTO = orderMapper.productsToProductDTOs(products);
        return productsDTO;
    }

    public OrderDTO getMapperOrderToOrderDTO(Order order) {
        OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);
        return orderDTO;
    }
}
