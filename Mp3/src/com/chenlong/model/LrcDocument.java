package com.chenlong.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LrcDocument {

	private String author;
	private String title;
	private Queue<LrcLine> lines=new LinkedList<LrcLine>();
	
	public LrcDocument() {
		// TODO Auto-generated constructor stub
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Queue<LrcLine> getLines() {
		return lines;
	}

	public void setLines(Queue<LrcLine> lines) {
		this.lines = lines;
	}

}
