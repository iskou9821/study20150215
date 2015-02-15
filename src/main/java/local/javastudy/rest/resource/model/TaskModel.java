package local.javastudy.rest.resource.model;

import javax.xml.bind.annotation.XmlRootElement;

import local.javastudy.persist.entity.Task;

@XmlRootElement
public class TaskModel {
	private Long id;
	private String title;
	private String content;
	private int ord;
	public TaskModel() {
		super();
	}
	public TaskModel(Task task) {
		super();
		this.id = task.getId();
		this.title = task.getTitle();
		this.content = task.getContent();
		this.ord = task.getOrd();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
