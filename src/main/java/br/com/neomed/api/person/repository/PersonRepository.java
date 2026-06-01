package br.com.neomed.api.person.repository;

import br.com.neomed.api.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}