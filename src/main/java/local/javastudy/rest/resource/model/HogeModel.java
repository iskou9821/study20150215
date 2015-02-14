package local.javastudy.rest.resource.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HogeModel {
	private Long id;
	private String msg1;
	private String msg2;
	public HogeModel() {
		super();
	}
	public HogeModel(Long id, String msg1, String msg2) {
		super();
		this.id = id;
		this.msg1 = msg1;
		this.msg2 = msg2;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMsg1() {
		return msg1;
	}
	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}
	public String getMsg2() {
		return msg2;
	}
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	
}
