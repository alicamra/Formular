package appFormular.frontend;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import appFormular.backend.DataContainers;

@Theme("mytheme")
public class MainUI extends UI {
	
	private static final String PERSISTENCE_UNIT_NAME = "test";
	private static EntityManagerFactory factory;
	private DataContainers dataContainers;

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		dataContainers = new DataContainers();
		final VerticalLayout layout = new VerticalLayout();

		TabSheet tabsheet = new TabSheet();
		tabsheet.setHeight("100%");

		AdministrationTabLayout administration = new AdministrationTabLayout(dataContainers);
		administration.setCaption("Administration");
		tabsheet.addTab(administration);

		FormularTabLayout formular = new FormularTabLayout(dataContainers);
		formular.setCaption("Formular");
		tabsheet.addTab(formular);

		layout.addComponent(tabsheet);
		layout.setMargin(true);
		layout.setSpacing(true);

		setContent(layout);

	}

	@WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	public static class MainUIServlet extends VaadinServlet {
	}
}
