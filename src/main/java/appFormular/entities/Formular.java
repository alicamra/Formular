package appFormular.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Formular {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	private String name;
	@OneToMany
	private List<Element> elements;

	public Formular() {
		this.elements = new ArrayList<Element>();
	}

	public Formular(String name, List<Element> elements) {
		this.name = name;
		this.elements = elements;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Element> getElement() {
		return elements;
	}

	public void setElement(List<Element> elements) {
		this.elements = elements;
	}

	public void setElementsValues(List<String> elementsValues) {
		int i = 0;
		if (elementsValues.size() == elements.size())
			for (Element element : elements) {
				System.out.println("Formular value: " + elementsValues.get(i).toString());
				element.setName(elementsValues.get(i).toString());
				++i;
			}
		//TODO handle exception for index out of bounds
	}
}
