package org.springframework.data.jpa.example.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.example.domain.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * Intergration test showing the basic usage of {@link UserRepository}.
 * 
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:repository-context.xml")
@Transactional
public class UserRepositorySamplePage {

    @Autowired
    UserRepository repository;

    @Test
    public void testPage(){
    	
        repository.save(createUser("AA"));
        repository.save(createUser("AB"));
        repository.save(createUser("AC"));
    	
    	Page<User> pageUser = repository.findAll(new PageRequest(0, 2));
    	System.out.println(pageUser.getTotalElements());
    	List<User> content = pageUser.getContent();
    	for (User user : content) {
			System.out.println(user);
		}
    }
    
    @Test
    public void testPagreCond(){
    	
    	repository.save(createUser("AA"));
    	repository.save(createUser("AB"));
    	repository.save(createUser("ABC"));
    	repository.save(createUser("AC"));
    	
    	Page<User> pageUser = repository.findByUsernameLike("%AB%",new PageRequest(0, 2));
    	assertThat(pageUser.getTotalElements(), is((long)2));
    }

	private User createUser(String userName) {
		User user = new User();
        user.setUsername(userName);
		return user;
	}

    /**
     * Tests inserting a user and asserts it can be loaded again.
     */
    @Test
    public void testInsert() {

        User user = new User();
        user.setUsername("username");

        user = repository.save(user);

        assertEquals(user, repository.findOne(user.getId()));
    }


    @Test
    public void foo() {

        User user = new User();
        user.setUsername("foobar");
        user.setLastname("lastname");

        user = repository.save(user);

        List<User> users = repository.findByLastname("lastname");

        assertNotNull(users);
        assertTrue(users.contains(user));

        User reference = repository.findByTheUsersName("foobar");
        assertEquals(user, reference);
    }


    /**
     * Test invocation of custom method.
     */
    @Test
    public void testCustomMethod() {

        User user = new User();
        user.setUsername("username");

        user = repository.save(user);

        List<User> users = repository.myCustomBatchOperation();

        assertNotNull(users);
        assertTrue(users.contains(user));
    }
}
