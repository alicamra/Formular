package appFormular.frontend;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Validatable;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import appFormular.entities.Element;

public class FormularElementsLayout extends VerticalLayout {
	private List<ElementLayout> listOfElementLayout;
	private List<String> listOfElements;

	public FormularElementsLayout() {
		this.setMargin(new MarginInfo(false, true, true, true));
		this.listOfElementLayout = new ArrayList<ElementLayout>();
		this.listOfElements = new ArrayList<String>();
	}

	public void renderElements(List<Element> elements) {
		removeAllComponents();
		listOfElementLayout.clear();
		for (Element element : elements) {
			ElementLayout elementLayout = new ElementLayout(element);
			listOfElementLayout.add(elementLayout);
			addComponent(elementLayout);
		}
	}

	public List<String> getListOfElementsValues() {
		listOfElements.clear();
		for (ElementLayout elementLayout : listOfElementLayout) {
			String value = String.valueOf(((AbstractField<String>) elementLayout.getType()).getValue());
			listOfElements.add(value);
		}
		return listOfElements;

	}

	private class ElementLayout extends HorizontalLayout {

		private Label elementName;
		private AbstractField type;

		public ElementLayout(Element element) {
			createComponents(element);
			setValidation(type, element);
			setSpacing(true);
			addComponent(this.elementName);
			addComponent(this.type);
		}

		private void createComponents(Element element) {

			this.elementName = new Label();
			this.elementName.setCaption(element.getName());

			String elType = element.getType();
			switch (elType) {
			case "TextBox":
				TextField textField = new TextField();
				textField.setValue(element.getTypeValue());
				setValidation(textField, element);
				this.type = textField;
				break;
			case "CheckBox":
				CheckBox checkBox = new CheckBox();
				checkBox.setValue(validateInputValue(element.getTypeValue()));
				setValidation(checkBox, element);
				this.type = checkBox;
				break;
			default:
				// TODO Handle error
				break;
			}
		}

		private boolean validateInputValue(String value) {
			if (value.equalsIgnoreCase("true"))
				return true;
			return false;
		}

		public void setValidation(AbstractField field, Element element) {
			if (element.getValidation().equals("Mandatory")) {
				field.setRequired(true);
			}
		}

		public Component getType() {
			return type;
		}
	}
}