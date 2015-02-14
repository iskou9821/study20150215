package local.javastudy.persist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/*
 * DBに永続化するオブジェクトの基底クラス
 */
@MappedSuperclass
public class AbsEntity {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="CREATED_TIME", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name="UPDATED_TIME", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Version //楽観的ロックのために参照する指定。
	private Date updatedTime;

	@PrePersist
	protected void preparePersist() {
		createdTime = new Date();
	}
	
	@PreUpdate
	protected void prepareUpdate() {
		updatedTime = new Date();
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}
