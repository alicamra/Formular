package appFormular.frontend;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import appFormular.entities.Element;

public class AdministrationElementsLayout extends VerticalLayout {

	private List<ElementLayout> listOfElementLayouts;
	private List<Element> listOfElements;

	public AdministrationElementsLayout() {
		this.setMargin(new MarginInfo(false, true, true, true));
		listOfElementLayouts = new ArrayList<ElementLayout>();
		listOfElements = new ArrayList<Element>();
		addNewElement();
	}

	public List<Element> getListOfElements() {
		listOfElements.clear();
		for (ElementLayout elementLayout : listOfElementLayouts) {
			if (!elementLayout.isElementFieldEmpty()) {
				String name = elementLayout.getElementName().getValue().toString();
				String type = elementLayout.getType().getValue().toString();
				String typeValue = elementLayout.getTypeValue().getValue().toString();
				String validation = elementLayout.getValidation().getValue().toString();
				Element element = new Element(name, type, typeValue, validation);
				listOfElements.add(element);
			}
		}
		return listOfElements;

	}

	public void renderElements(Object id, List<Element> elements) {
		this.removeAllComponents();
		for (Element element : elements) {
			ElementLayout elementLayout = addNewElement();
			elementLayout.getElementName().setValue(element.getName());
			elementLayout.getType().setValue(element.getType());
			elementLayout.getTypeValue().setValue(element.getTypeValue());
			elementLayout.getValidation().setValue(element.getValidation());
		}
	}

	public ElementLayout addNewElement() {
		ElementLayout element = new ElementLayout();
		this.listOfElementLayouts.add(element);
		this.addComponent(element);
		return element;
	}

	private class ElementLayout extends HorizontalLayout {
		private Label element;
		private TextField elementName;
		private ComboBox type;
		private TextField typeValue;
		private ComboBox validation;

		public ElementLayout() {
			this.setSpacing(true);
			createComponents();
			addComponentsToLayout();
			createTypeValues();
			createValidationValues();
		}

		public boolean isElementFieldEmpty() {
			if (this.elementName.isEmpty() || this.type.isEmpty() || this.validation.isEmpty())
				return true;
			return false;
		}

		private void createComponents() {
			this.element = new Label();
			this.element.setCaption("Element ");
			this.elementName = new TextField();
			this.elementName.setNullSettingAllowed(false);
			this.type = new ComboBox();
			this.typeValue = new TextField();
			this.validation = new ComboBox();
		}

		private void addComponentsToLayout() {
			this.addComponent(element);
			this.addComponent(elementName);
			this.addComponent(type);
			this.addComponent(typeValue);
			this.addComponent(validation);
		}

		private void createTypeValues() {
			this.type.addItem("TextBox");
			this.type.addItem("CheckBox");
			this.type.setNullSelectionAllowed(false);
			this.type.setNullSelectionItemId(null);
		}

		private void createValidationValues() {
			this.validation.addItem("Boolean");
			this.validation.addItem("Mandatory");
			this.validation.addItem("None");
			this.validation.setNullSelectionAllowed(false);
			this.validation.setNullSelectionItemId(null);
		}

		public Label getElement() {
			return element;
		}

		public TextField getElementName() {
			return elementName;
		}

		public ComboBox getType() {
			return type;
		}

		public TextField getTypeValue() {
			return typeValue;
		}

		public ComboBox getValidation() {
			return validation;
		}
	}

}
