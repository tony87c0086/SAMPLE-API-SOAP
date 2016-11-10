package com.uknowho.sample.soap.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This Catalogue define entity for catalogue table.
 * 
 * Created date <08-Sep-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Entity
@Table(name="catalogue")
public class Catalogue extends GenericEntity<Catalogue> implements Serializable {

	private static final long serialVersionUID = -7905306769233275236L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "catalogue_id", nullable = false)
	private Integer catalogueID;
	@Column(name = "catalogue_name")
	private String catalogueName;
	@Column(name = "parent_id")
	private Integer parentID;
	@Column(name = "type_id")
	private Integer typeID;
	@Column(name = "type_name")
	private String typeName;
	@Column(name = "group_id")
	private Integer groupID;
	@Column(name = "group_name")
	private String groupName;
	@Column(name = "sort_order")
	private Integer sortOrder;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "active")
	private Boolean active;
	
	public Integer getCatalogueID() {
		return catalogueID;
	}

	public void setCatalogueID(Integer catalogueID) {
		this.catalogueID = catalogueID;
	}

	public String getCatalogueName() {
		return catalogueName;
	}

	public void setCatalogueName(String catalogueName) {
		this.catalogueName = catalogueName;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Catalogue{");
		sb.append("catalogueID:");
		sb.append(catalogueID);
		sb.append(", catalogueName:");
		sb.append(catalogueName);
		sb.append(", parentID:");
		sb.append(parentID);
		sb.append(", typeID:");
		sb.append(typeID);
		sb.append(", typeName:");
		sb.append(typeName);
		sb.append(", groupID:");
		sb.append(groupID);
		sb.append(", groupName:");
		sb.append(groupName);
		sb.append(", sortOrder:");
		sb.append(sortOrder);
		sb.append(", createdDate:");
		sb.append(createdDate);
		sb.append(", active:");
		sb.append(active);
		sb.append("}");
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Catalogue clone() {
		Catalogue entity = new Catalogue();
		
		entity.setCatalogueID(this.catalogueID);
		entity.setCatalogueName(this.catalogueName);
		entity.setParentID(this.parentID);
		entity.setTypeID(this.typeID);
		entity.setTypeName(this.typeName);
		entity.setGroupID(this.groupID);
		entity.setGroupName(this.groupName);
		entity.setSortOrder(this.sortOrder);
		entity.setCreatedDate(this.createdDate);
		entity.setActive(this.active);
		
		return entity;
	}

}
