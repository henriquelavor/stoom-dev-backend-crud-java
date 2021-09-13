package br.com.stoom.addressapi.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import br.com.stoom.addressapi.dto.AddressDTO;
import br.com.stoom.addressapi.entities.Address;
import br.com.stoom.addressapi.repositories.AddressRepository;
import br.com.stoom.addressapi.services.exceptions.ResourceNotFoundException;

@Service
public class AddressService {

	@Value("${apikeygeocoding}")
	private String API_KEY;

	@Autowired
	private AddressRepository repository;

	@Transactional(readOnly = true)
	public List<AddressDTO> findAll() {
		List<Address> list = repository.findAll();

		List<AddressDTO> listDto = new ArrayList<>();
		for (Address adr : list) {
			listDto.add(new AddressDTO(adr));
		}
		return listDto;
	}

	@Transactional(readOnly = true)
	public AddressDTO findById(Long id) {
		Optional<Address> obj = repository.findById(id);
		Address entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
		return new AddressDTO(entity);
	}

	@Transactional
	public AddressDTO insert(AddressDTO dto) throws ApiException, InterruptedException, IOException {

		Address entity = new Address();
		entity.setStreetName(dto.getStreetName());
		entity.setNumber(dto.getNumber());
		entity.setComplement(dto.getComplement());
		entity.setNeighbourhood(dto.getNeighbourhood());
		entity.setCity(dto.getCity());
		entity.setState(dto.getState());
		entity.setCountry(dto.getCountry());
		entity.setZipcode(dto.getZipcode());

		// Update Latitude / Longitude
		if (dto.getLatitude() == null || dto.getLongitude() == null) {
			// Method get get LATITUDE Geocoding API do Google
			GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
			GeocodingResult[] results;
			results = GeocodingApi
					.geocode(context,dto.getStreetName() + "," + dto.getNumber() + "," + dto.getCity() + "," + dto.getState()).await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			if (dto.getLatitude() == null) {
				dto.setLatitude(Double.parseDouble(gson.toJson(results[0].geometry.location.lat)));
				entity.setLatitude(dto.getLatitude());
			} else {
				entity.setLatitude(dto.getLatitude());
			}

			if (dto.getLongitude() == null) {
				dto.setLongitude(Double.parseDouble(gson.toJson(results[0].geometry.location.lng)));
				entity.setLongitude(dto.getLongitude());
			} else {
				entity.setLatitude(dto.getLongitude());
			}
		}

		entity = repository.save(entity);

		return new AddressDTO(entity);
	}

	@Transactional
	public @Valid AddressDTO update(Long id, AddressDTO dto) throws ApiException, InterruptedException, IOException {
		try {
			Address entity = repository.getOne(id);
			entity.setStreetName(dto.getStreetName());
			entity.setNumber(dto.getNumber());
			entity.setComplement(dto.getComplement());
			entity.setNeighbourhood(dto.getNeighbourhood());
			entity.setCity(dto.getCity());
			entity.setState(dto.getState());
			entity.setCountry(dto.getCountry());
			entity.setZipcode(dto.getZipcode());

			// Update Latitude / Longitude
			if (dto.getLatitude() == null || dto.getLongitude() == null) {
				// Method get get LATITUDE Geocoding API do Google
				GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
				GeocodingResult[] results;
				results = GeocodingApi.geocode(context,dto.getStreetName() + "," + dto.getNumber() + "," + dto.getCity() + "," + dto.getState()).await();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();

				if (dto.getLatitude() == null) {
					dto.setLatitude(Double.parseDouble(gson.toJson(results[0].geometry.location.lat)));
					entity.setLatitude(dto.getLatitude());
				} else {
					entity.setLatitude(dto.getLatitude());
				}

				if (dto.getLongitude() == null) {
					dto.setLongitude(Double.parseDouble(gson.toJson(results[0].geometry.location.lng)));
					entity.setLongitude(dto.getLongitude());
				} else {
					entity.setLatitude(dto.getLongitude());
				}
			}

			entity = repository.save(entity);
			
			return new AddressDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

}
