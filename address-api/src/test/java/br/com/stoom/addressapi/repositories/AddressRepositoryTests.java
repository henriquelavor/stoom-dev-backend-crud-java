package br.com.stoom.addressapi.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.stoom.addressapi.dto.AddressDTO;
import br.com.stoom.addressapi.entities.Address;

@DataJpaTest
public class AddressRepositoryTests {
	
	@Autowired
	private AddressRepository repository;
	
	@Test
	public void deleteObjectWhenIdExists() {
		long exintingId = 1L;
		
		repository.deleteById(exintingId);
		Optional<Address> result = repository.findById(exintingId);
		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void checkDeleteIdDoesNotExists() {
		long nonexintingId = 1000L;
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, ()-> {
			repository.deleteById(nonexintingId);
		});
	}
	
	
	@Test
	public void checkInsertAutoincrementIdNull() {
		Address address = this.insertAddress();
		address.setId(null);
		
		address = repository.save(address);
		
		Assertions.assertNotNull(address.getId());
	}
	
	
	public Address insertAddress() {
		Address address = new Address(1L, "Rua Antonio Augusto martins, 217, Bairro São francisco", "127", "casa", "atras da vivo", "Boa Vista", "Roraima", "Brasil", "69305-270", 0, 0);
		return address;
	}
	
	public AddressDTO insertAddressDTO() {
		Address address = insertAddress();
		return new AddressDTO(address);
	}
	
}
