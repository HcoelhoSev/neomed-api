package br.com.neomed.api.person.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonAddressId implements Serializable {

    private Long personId;
    private Long addressId;
}