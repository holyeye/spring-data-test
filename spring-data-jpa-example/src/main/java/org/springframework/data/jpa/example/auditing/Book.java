package org.springframework.data.jpa.example.auditing;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
public class Book extends AbstractAuditable<Book, Long> {

	private String titie;
}
