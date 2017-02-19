package appFormular.frontend;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import appFormular.backend.DataContainers;
import appFormular.backend.FormularWorker;

public class FormularTabLayout extends VerticalLayout{
	
	private FormularSearchLayout searchFormular;
	private FormularElementsLayout formularElements;
	private FormularSaveLayout saveFormular;
	private DataContainers dataContainers;
	private FormularWorker worker;
	
	public FormularTabLayout(DataContainers dataContainers) {
		this.setWidth("80%");
		
		createComponents(dataContainers);
		addComponentsToLayout();
		setLoadButtonClickListener();
		this.setHeight("100%");
	}
	
	private void createComponents(DataContainers dataContainers){
		this.dataContainers = dataContainers;
		searchFormular = new FormularSearchLayout(this.dataContainers);
		formularElements = new FormularElementsLayout();
		this.worker = new FormularWorker(searchFormular, formularElements);
		saveFormular = new FormularSaveLayout(this.worker);
		
	}
	private void setLoadButtonClickListener(){
		searchFormular.getLoad().addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Object id = searchFormular.getFormularName().getValue();
				
				worker.loadFormular(id);
				
			}
		});
		
	}
	private void addComponentsToLayout(){
		this.addComponent(searchFormular);
		this.addComponent(formularElements);
		this.addComponent(saveFormular);	
		this.setComponentAlignment(saveFormular, Alignment.BOTTOM_CENTER);
	}
}
