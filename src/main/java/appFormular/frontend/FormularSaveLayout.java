package appFormular.frontend;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import appFormular.backend.FormularWorker;

public class FormularSaveLayout extends VerticalLayout {
	private Button save;
	private FormularWorker worker;
	
	public FormularSaveLayout(FormularWorker worker){
		this.worker = worker;
		this.setMargin(true);
		createSaveComponent();
		this.setWidth("80%");
		this.addComponent(save);
		this.setComponentAlignment(save, Alignment.BOTTOM_RIGHT);
	}
	private void createSaveComponent(){
		save = new Button();
		save.setCaption("Save Formular");
		save.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				worker.updateFormular();
				Notification.show("Formular is updated!");
			}
		});
		
	}
	
	

}
