package local.javastudy.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TASK")
public class Task extends AbsEntity {
	@Column(name="TITLE", length=128)
	private String title;
	
	@Column(name="CONTENT", length=1024)
	private String content;
	
	@Column(name="ORD", nullable=false)
	private int ord = 0;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}
	
}
