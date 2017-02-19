package appFormular.backend;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanContainer;

import appFormular.entities.Formular;

public class DataContainers {
	private JPAContainer<Formular> formularContainer;

	public DataContainers() {
		formularContainer = JPAContainerFactory.make(Formular.class, "test");
	}

	public void setFormularContainer(JPAContainer<Formular> formularContainer) {
		this.formularContainer = formularContainer;
	}

	public JPAContainer<Formular> getFormularContainer() {
		return formularContainer;
	}
}
