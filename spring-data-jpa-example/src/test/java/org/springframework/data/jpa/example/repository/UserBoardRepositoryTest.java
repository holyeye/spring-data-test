package org.springframework.data.jpa.example.repository;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.example.domain.Board;
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
public class UserBoardRepositoryTest {

	@Autowired UserRepository repository;
	
	@PersistenceContext EntityManager em;

	@Before
	public void init() {
	}

	/**
	 * Tests inserting a user and asserts it can be loaded again.
	 */
	@Test
	public void testPage() {

		User save = repository.save(createUser("AA"));
		em.flush();
		em.clear();

		User findUser = repository.findOne(save.getId());
		
		System.out.println(findUser.getUsername());
		Set<Board> boards = findUser.getBoards();
		for (Board board : boards) {
			System.out.println(board.getTitle());
		}

	}

	private User createUser(String userName) {
		User user = new User();
		user.setUsername(userName);
		Board board = new Board();
		board.setTitle("hello title");
		user.addBoard(board);
		return user;
	}

}
