package appFormular.frontend;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import appFormular.backend.DataContainers;
import appFormular.backend.AdministrationWorker;

public class AdministrationTabLayout extends VerticalLayout {

	private AdministrationSearchLayout searchHeader;
	private AdministrationElementsLayout elementsLayout;
	private AdministrationSaveLayout saveFooter;
	private AdministrationWorker worker;
	private DataContainers dataContainers;

	public AdministrationTabLayout(DataContainers dataContainers) {
		this.dataContainers = dataContainers;
		this.setCaption("Administration");

		this.setWidth("80%");
		createComponents();

		this.addComponent(searchHeader);
		setSearchButtonClickListener();

	}

	private void createComponents() {
		searchHeader = new AdministrationSearchLayout(dataContainers);
		elementsLayout = new AdministrationElementsLayout();
		worker = new AdministrationWorker(elementsLayout, searchHeader);
		saveFooter = new AdministrationSaveLayout(worker);

	}

	private void setSearchButtonClickListener() {
		searchHeader.getSearch().addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				String name = searchHeader.getFormularNameEntry().getValue();
				Object formularId = searchHeader.getIdIfFormularExists(name);
				if (formularId != null) {
					addComponent(elementsLayout);
					addComponent(saveFooter);
					worker.renderFormularElements(formularId);
				} else {
					elementsLayout.removeAllComponents();
					elementsLayout.addNewElement();
					addComponent(elementsLayout);
					addComponent(saveFooter);
					setComponentAlignment(saveFooter, Alignment.BOTTOM_CENTER);
				}
			}
		});
	}

}
