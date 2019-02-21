package com.hb.acadia.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hb.acadia.model.user.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleServiceTest extends AbstractApplicationTest {

	@BeforeClass
	public static void startTest() {
		log.info("");
		log.info(
				"******************************************************* STARTING ROLE TESTS ***************************************");
		log.info("");
	}

	@Before
	public void createData() {

		log.info("");
		log.info(
				"********************************************************* CREATING DATAS ***************************************");
		log.info("");

		/* Create roles in db */
		Role role1 = new Role();
		role1.setRoleName("ROLE_CUSTOMER");
		roleService.createRole(role1);

		Role role2 = new Role();
		role2.setRoleName("ROLE_ADMIN");
		roleService.createRole(role2);

	}

	@After
	public void deleteDataAfterTest() {
		log.info("");
		log.info(
				"********************************************************* DELETING DATAS ***************************************");
		log.info("");

		// delete all roles
		roleService.deleteAll();

	}

	@Test
	public void test_createRole() {

	}

	/**
	 * Test method getting all roles
	 */
	@Test
	public void test_getRoles() {
		
			assertEquals("ROLE_CUSTOMER", roleService.getRoles().get(0).getRoleName());
			assertEquals("ROLE_ADMIN", roleService.getRoles().get(1).getRoleName());
		
	}

	/**
	 * Test method deleting a role
	 */
	@Test
	public void test_deleteRole() {
		int numberOfRoleBeforeDelete = (int)roleRepository.count();
		List<Role> roles = roleService.getRoles();
		roleService.deleteRole(roles.get(0));
		int numberOfRoleAfterDelete = (int)roleRepository.count();
		
		assertTrue(numberOfRoleAfterDelete==(numberOfRoleBeforeDelete-1));
		
		
	}

	/**
	 * Test method deleting all roles
	 */
	@Test
	public void test_deleteRoles() {
		int numberOfRoleBeforeDelete = (int)roleRepository.count();
		roleService.deleteAll();
		int numberOfRoleAfterDelete = (int)roleRepository.count();
		
		assertTrue(numberOfRoleAfterDelete!=numberOfRoleBeforeDelete);
		assertTrue(numberOfRoleAfterDelete==0);
	}
}
