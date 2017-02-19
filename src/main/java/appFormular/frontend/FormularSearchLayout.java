package appFormular.frontend;

import java.util.List;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import appFormular.backend.DataContainers;
import appFormular.entities.Element;
import appFormular.entities.Formular;

public class FormularSearchLayout extends HorizontalLayout {
	private Label formular;
	private ComboBox formularName;
	private Label version;
	private TextField versionValue;
	private Button load;

	private JPAContainer<Formular> formularContainer;

	public FormularSearchLayout(DataContainers dataContainers) {
		this.formularContainer = dataContainers.getFormularContainer();
		createComponents();
		addComponents();
		this.setSpacing(true);
		this.setMargin(true);
	}

	private void createComponents() {
		formular = new Label();
		formular.setCaption("Formular name: ");
		formularName = new ComboBox();
		formularName.setNullSelectionAllowed(false);
		formularName.setContainerDataSource(formularContainer);
		formularName.setItemCaptionPropertyId("name");
		version = new Label();
		version.setCaption("Version: ");
		versionValue = new TextField();
		load = new Button();
		load.setCaption("Load");
	}

	private void addComponents() {
		this.addComponent(formular);
		this.addComponent(formularName);
		this.addComponent(version);
		this.addComponent(versionValue);
		this.addComponent(load);
	}
	public List<Element> getListOfElementsFromFormular(Object id){
		Formular formular = this.formularContainer.getItem(id).getEntity();
		return formular.getElement();
	}
	
	public void setListOfElementsInFormular(Object id, List<String> elements){
		Formular formular =  this.formularContainer.getItem(id).getEntity();
		formular.setElementsValues(elements);
	}
	public Button getLoad() {
		return load;
	}
	public ComboBox getFormularName() {
		return formularName;
	}

}
