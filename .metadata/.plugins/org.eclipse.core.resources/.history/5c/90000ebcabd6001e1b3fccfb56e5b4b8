package com.javateam.STDProject.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Data
@Table(name = "board") // 테이블 매핑.
public class StudentVO {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private int seq;

	@Column(name = "title")
	private String title;
	
	@Column(name = "writer")
	private String writer;

	@Column(name = "content")
	private String content;

	@Column(name = "regdate")
	private Date regDate;

	@Column(name = "cnt")
	private int cnt;	
	
//	@Column(name = "ch1")
//	private String ch1;
//	
//	@Column(name = "ch1")
//	private String ch2Text;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
//	public String getCh1() {
//		return ch1;
//	}
//	public void setCh1(String ch1) {
//		this.ch1 = ch1;
//	}
//	public String getCh2Text() {
//		return ch2Text;
//	}
//	public void setCh2Text(String ch2Text) {
//		this.ch2Text = ch2Text;
//	}
	
	

}
