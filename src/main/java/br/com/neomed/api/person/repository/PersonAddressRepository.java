package br.com.neomed.api.person.repository;

import br.com.neomed.api.person.entity.PersonAddress;
import br.com.neomed.api.person.entity.PersonAddressId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonAddressRepository extends JpaRepository<PersonAddress, PersonAddressId> {

    List<PersonAddress> findByPersonId(Long personId);
}