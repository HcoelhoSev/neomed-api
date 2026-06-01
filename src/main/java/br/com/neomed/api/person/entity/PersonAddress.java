package br.com.neomed.api.person.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "person_addresses")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonAddress {

    @EmbeddedId
    private PersonAddressId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("addressId")
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "main", nullable = false)
    private Boolean main;
}