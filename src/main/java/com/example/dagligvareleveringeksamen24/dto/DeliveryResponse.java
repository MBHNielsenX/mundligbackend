package com.example.dagligvareleveringeksamen24.dto;

import com.example.dagligvareleveringeksamen24.entity.Delivery;
import com.example.dagligvareleveringeksamen24.entity.Product;
import com.example.dagligvareleveringeksamen24.entity.ProductOrder;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryResponse {

        private Long id;

        private LocalDate deliveryDate;

        private String fromWarehouse;

        private String destination;

        private List<ProductOrder> productOrders;

        public DeliveryResponse(Delivery delivery) {
            this.id = delivery.getId();
            this.deliveryDate = delivery.getDeliveryDate();
            this.fromWarehouse = delivery.getFromWarehouse();
            this.destination = delivery.getDestination();
            this.productOrders = delivery.getProductOrders();

        }

}
