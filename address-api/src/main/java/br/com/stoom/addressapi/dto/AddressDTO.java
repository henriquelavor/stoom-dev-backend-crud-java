package br.com.stoom.addressapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.stoom.addressapi.entities.Address;

public class AddressDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; // required
	
	@NotBlank(message = "Campo obrigatório")
	private String streetName; // required
	
	@NotBlank(message = "Campo obrigatório")
	private String number;// required
	
	private String complement;
	
	@NotBlank(message = "Campo obrigatório")
	private String neighbourhood; // required
	
	@NotBlank(message = "Campo obrigatório")
	private String city; // required
	
	@NotBlank(message = "Campo obrigatório")
	private String state; // required
	
	@NotBlank@NotBlank(message = "Campo obrigatório")
	private String country; // required
	
	@NotBlank(message = "Campo obrigatório")
	private String zipcode; // required
	
	private Double latitude;
	private Double longitude;

	public AddressDTO() {
	}

	public AddressDTO(Long id, String streetName, String number, String complement, String neighbourhood, String city,
			String state, String country, String zipcode, Double latitude, Double longitude) {
		this.id = id;
		this.streetName = streetName;
		this.number = number;
		this.complement = complement;
		this.neighbourhood = neighbourhood;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public AddressDTO(Address entity) {
		this.id = entity.getId();
		this.streetName = entity.getStreetName();
		this.number = entity.getNumber();
		this.complement = entity.getComplement();
		this.neighbourhood = entity.getNeighbourhood();
		this.city = entity.getCity();
		this.state = entity.getState();
		this.country = entity.getCountry();
		this.zipcode = entity.getZipcode();
		this.latitude = entity.getLatitude();
		this.longitude = entity.getLongitude();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
