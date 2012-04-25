package org.springframework.data.jpa.example.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;


/**
 * Sample user class.
 * 
 * @author Oliver Gierke
 */
@Entity
public class Board extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -3017498653877420093L;

    private String title;
    
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


    
}
