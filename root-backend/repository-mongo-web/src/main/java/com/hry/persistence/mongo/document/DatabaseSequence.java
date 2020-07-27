package com.hry.persistence.mongo.document;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class DatabaseSequence {
	
	@Id
    private String id;
    private long seq;

	@Override
	public String toString() {
		return "DatabaseSequence [id=" + id + ", seq=" + seq + "]";
	}  
}
