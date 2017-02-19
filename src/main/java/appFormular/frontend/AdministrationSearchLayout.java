package appFormular.frontend;

import java.util.List;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import appFormular.backend.DataContainers;
import appFormular.entities.Element;
import appFormular.entities.Formular;

public class AdministrationSearchLayout extends HorizontalLayout {

	private Label formularName;
	private TextField formularNameEntry;
	private Button search;
	private JPAContainer<Formular> formularContainer;

	public AdministrationSearchLayout(DataContainers dataContainers) {
		this.formularContainer = dataContainers.getFormularContainer();
		this.setMargin(true);
		this.setSpacing(true);
		createAndAddComponets();
	}

	public Object getIdIfFormularExists(String name) {
		if (this.formularContainer.size() > 0)
			for (Object id : this.formularContainer.getItemIds()) {
				if (this.formularContainer.getItem(id).getEntity().getName().equals(name))
					return id;
			}
		return null;
	}

	public TextField getFormularNameEntry() {
		return formularNameEntry;
	}

	public JPAContainer<Formular> getFormularContainer() {
		return formularContainer;
	}

	public void createNewFormular(String name, List<Element> listOfElements) {
		Formular formular = new Formular(name, listOfElements);
		this.formularContainer.addEntity(formular);
		Notification.show("New formular is created!");
	}

	public boolean updateExistingFormular(Object id, String name, List<Element> listOfElements) {
		Formular formular = this.formularContainer.getItem(id).getEntity();
		List<Element> elements = formular.getElement();
		if (elements.size() == listOfElements.size()) {
			for (int i = 0; i < elements.size(); ++i) {
				elements.get(i).setName(listOfElements.get(i).getName());
				elements.get(i).setType(listOfElements.get(i).getType());
				elements.get(i).setValidation(listOfElements.get(i).getValidation());
			}
			Notification.show("Formular is updated!");
			return true;
		}
		Notification.show("Number of elements in formular is changed. Please, rename formular!");
		return false;
	}

	public List<Element> getListOfElementsForFormular(Object formularId) {
		return this.formularContainer.getItem(formularId).getEntity().getElement();
	}

	public Button getSearch() {
		return search;
	}

	private void createAndAddComponets() {
		this.formularName = createFormularName();
		this.formularNameEntry = createNameEntry();
		this.search = createSearchButton();

		this.addComponent(formularName);
		this.addComponent(formularNameEntry);
		this.addComponent(search);
	}

	private Label createFormularName() {
		Label formularName = new Label();
		formularName.setCaption("Formular Name: ");
		return formularName;
	}

	private TextField createNameEntry() {
		TextField formularNameEntry = new TextField();
		formularNameEntry.setWidth("150%");
		return formularNameEntry;
	}

	private Button createSearchButton() {
		Button search = new Button();
		search.setCaption("Search");

		return search;
	}
}
