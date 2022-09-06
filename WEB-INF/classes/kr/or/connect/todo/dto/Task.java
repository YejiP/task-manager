package kr.or.connect.todo.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task {
	int bigint;
	String title;
	String name;
	int sequence;
	String type;
	String regdate;
	
	public Task(int bigint, String title, String name, int sequence, String type, String regdate) {
		super();
		this.bigint = bigint;
		this.title = title;
		this.name = name;
		this.sequence = sequence;
		this.type = type;
		this.regdate = regdate;
;
	}
	public Task(String title, String name, int sequence){
		super();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String timeStamp = simpleDateFormat.format(new Date());
		this.title = title;
		this.name = name;
		this.sequence = sequence;
		this.type = "todo";
		this.regdate =timeStamp;
	}
	public int getBigint() {
		return bigint;
	}

	public void setBigint(int bigint) {
		this.bigint = bigint;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Task [bigint=" + bigint + ", title=" + title + ", name=" + name + ", sequence=" + sequence + ", type="
				+ type + ", regdate=" + regdate + "]";
	}
	
	/*
	  CREATE TABLE todo ( 
	  id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT, 
	  title VARCHAR(255) NOT NULL, 
	  name VARCHAR(100) NOT NULL, 
	  sequence INT(1) NOT NULL, 
	  type VARCHAR(20) DEFAULT 'TODO',
	  regdate DATETIME DEFAULT NOW(), 
	  PRIMARY KEY (id));
	*/
}
