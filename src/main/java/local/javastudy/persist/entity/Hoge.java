package local.javastudy.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="hoge")
public class Hoge extends AbsEntity {
	@Column(name="MSG_1", length=512)
	private String msg1;
	@Column(name="MSG_2", length=512)
	private String msg2;
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
