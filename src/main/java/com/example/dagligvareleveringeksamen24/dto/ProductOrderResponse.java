package com.example.dagligvareleveringeksamen24.dto;

import com.example.dagligvareleveringeksamen24.entity.ProductOrder;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOrderResponse {

    private Long id;

    private int quantity;

    private ProductResponse product;

    private DeliveryResponse delivery;

    public ProductOrderResponse(ProductOrder productOrder) {
        this.id = productOrder.getId();
        this.quantity = productOrder.getQuantity();
        this.product = new ProductResponse(productOrder.getProduct());
        if (productOrder.getDelivery() != null) {
            this.delivery = new DeliveryResponse(productOrder.getDelivery());
        }
    }
}
