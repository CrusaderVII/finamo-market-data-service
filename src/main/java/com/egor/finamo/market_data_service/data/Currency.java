package com.egor.finamo.market_data_service.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Currency {

    @Id
    private String code;
    private String name;
    private int priority;
}
