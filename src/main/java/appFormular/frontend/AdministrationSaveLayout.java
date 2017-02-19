package appFormular.frontend;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

import appFormular.backend.AdministrationWorker;

public class AdministrationSaveLayout extends VerticalLayout {

	private Button saveFormular;
	private Button addElement;
	private AdministrationWorker worker;

	public AdministrationSaveLayout(AdministrationWorker worker) {
		this.worker = worker;
		HorizontalLayout buttons = addButtons();
		this.addComponent(buttons);
		this.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
		this.setSpacing(true);
		this.setMargin(true);
	}

	private Button createSaveButton() {
		Button save = new Button();
		save.setCaption("Save Formular");
		save.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				worker.saveFormular();
			}
		});
		return save;
	}

	private HorizontalLayout addButtons() {
		HorizontalLayout buttons = new HorizontalLayout();
		saveFormular = createSaveButton();
		addElement = addNewElement();
		buttons.addComponent(addElement);
		buttons.addComponent(saveFormular);
		buttons.setSpacing(true);
		return buttons;
	}

	private Button addNewElement() {
		Button addElement = new Button();
		addElement.setCaption("Add Element");
		addElement.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				worker.addNewElement();
			}
		});
		return addElement;
	}

	public Button getAddElement() {
		return addElement;
	}

	public Button getSaveFormular() {
		return saveFormular;
	}

}
