package org.springframework.data.jpa.example.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;


/**
 * Sample user class.
 * 
 * @author Oliver Gierke
 */
@Entity
@NamedQuery(name = "User.findByTheUsersName", query = "from User u where u.username = ?")
public class User extends AbstractPersistable<Long> {

    private static final long serialVersionUID = -2952735933715107252L;

    @Column(unique = true)
    private String username;

    private String firstname;
    private String lastname;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn
    private Set<Board> boards = new HashSet<Board>();


    public User() {

        this(null);
    }
    

    /**
     * Creates a new user instance.
     */
    public User(Long id) {

        this.setId(id);
    }



	/**
     * Returns the username.
     * 
     * @return
     */
    public String getUsername() {

        return username;
    }


    /**
     * @param username the username to set
     */
    public void setUsername(String username) {

        this.username = username;
    }


    /**
     * @return the firstname
     */
    public String getFirstname() {

        return firstname;
    }


    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }


    /**
     * @return the lastname
     */
    public String getLastname() {

        return lastname;
    }


    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    

	public Set<Board> getBoards() {
		return boards;
	}


	public void setBoards(Set<Board> boards) {
		this.boards = boards;
	}
	
	public void addBoard(Board board){
		boards.add(board);
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + ", getUsername()=" + getUsername() + ", getFirstname()="
				+ getFirstname() + ", getLastname()=" + getLastname() + ", getId()=" + getId() + ", isNew()=" + isNew() + ", toString()=" + super.toString()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + "]";
	}
    
    
    
    
}
