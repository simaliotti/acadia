package com.hb.acadia.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.hb.acadia.model.Address;

public class AddressServiceTest extends AbstractApplicationTest {

	@Test
	public void test_createAddress() {
		Address address = new Address();
		address.setCity("Toulouse");
		address.setCountry("France");
		address.setCp("31000");
		address.setNumber(24);
		address.setRoad("République");
		address.setRoadType("Avenue");

		Address createdAddress = addressService.createAddress(address);

		assertEquals("Toulouse", createdAddress.getCity());
		assertEquals("France", createdAddress.getCountry());
		assertEquals("31000", createdAddress.getCp());
		assertEquals(24, createdAddress.getNumber());
		assertEquals("République", createdAddress.getRoad());
		assertEquals("Avenue", createdAddress.getRoadType());
		assertThat(createdAddress.getId(), notNullValue());

		addressRepository.deleteAll();
	}

	@Test
	public void test_deleteAddress() {
		Address address = new Address();
		address.setCity("Toulouse");
		address.setCountry("France");
		address.setCp("31000");
		address.setNumber(24);
		address.setRoad("République");
		address.setRoadType("Avenue");
		addressService.createAddress(address);
		
	
		assertEquals(1, (int)addressRepository.count());
		
		/* delete Address */
		addressRepository.delete(address);
		assertEquals(0, (int)addressRepository.count());
		
	}
}
