package br.com.neomed.api.person.repository;

import br.com.neomed.api.person.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}