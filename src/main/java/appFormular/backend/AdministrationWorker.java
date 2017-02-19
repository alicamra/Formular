package appFormular.backend;

import java.util.List;

import appFormular.entities.Element;
import appFormular.frontend.AdministrationElementsLayout;
import appFormular.frontend.AdministrationSearchLayout;

public class AdministrationWorker {

	private AdministrationElementsLayout elementsLayout;
	private AdministrationSearchLayout searchLayout;

	public AdministrationWorker(AdministrationElementsLayout elementsLayout, AdministrationSearchLayout searchLayout) {
		this.elementsLayout = elementsLayout;
		this.searchLayout = searchLayout;
	}

	public void addNewElement() {
		this.elementsLayout.addNewElement();
	}

	public void saveFormular() {
		String name = searchLayout.getFormularNameEntry().getValue();
		if (!name.equals("")) {
			List<Element> listOfElements = this.elementsLayout.getListOfElements();
			Object formularId = this.searchLayout.getIdIfFormularExists(name);
			if (formularId != null && listOfElements.size()>0) {
				this.searchLayout.updateExistingFormular(formularId, name, listOfElements);
				return;
			}
			this.searchLayout.createNewFormular(name, listOfElements);
		}
		
	}

	public void renderFormularElements(Object id) {
		List<Element> elements = searchLayout.getListOfElementsForFormular(id);
		this.elementsLayout.renderElements(id, elements);
	}
}
