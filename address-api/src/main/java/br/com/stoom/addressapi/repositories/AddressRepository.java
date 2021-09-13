package br.com.stoom.addressapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stoom.addressapi.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
