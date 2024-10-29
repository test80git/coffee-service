package com.example.module_mapper.dto.mapper;

import com.example.module_mapper.dto.Order;
import com.example.module_mapper.dto.OrderDTO;
import com.example.module_mapper.dto.Product;
import com.example.module_mapper.dto.ProductDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "name", target = "productName")
    ProductDTO productToProductDTO(Product product);

    @IterableMapping(elementTargetType = ProductDTO.class)
    List<ProductDTO> productsToProductDTOs(List<Product> products);

    @Mapping(source = "products", target = "productDTOs")
    OrderDTO orderToOrderDTO(Order order);
}
