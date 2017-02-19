package appFormular.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Element {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	private String name;
	private String type;
	private String typeValue;
	private String validation;

	public Element() {
	}

	public Element(String name, String type, String typeValue, String validation) {
		this.name = name;
		this.type = type;
		this.typeValue = typeValue;
		this.validation = validation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

}