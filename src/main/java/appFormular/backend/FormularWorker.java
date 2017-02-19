package appFormular.backend;

import java.util.List;

import appFormular.entities.Element;
import appFormular.frontend.FormularElementsLayout;
import appFormular.frontend.FormularSearchLayout;

public class FormularWorker {

	private FormularSearchLayout searchLayout;
	private FormularElementsLayout elementsLayout;

	public FormularWorker(FormularSearchLayout searchLayout, FormularElementsLayout elementsLayout) {

		this.searchLayout = searchLayout;
		this.elementsLayout = elementsLayout;

	}

	public void loadFormular(Object id) {
		List<Element> elements = searchLayout.getListOfElementsFromFormular(id);
		elementsLayout.renderElements(elements);

	}

	public void updateFormular() {
		Object formularId = searchLayout.getFormularName().getValue();
		if (formularId != null) {
			List<String> elements = this.elementsLayout.getListOfElementsValues();
			searchLayout.setListOfElementsInFormular(formularId, elements);
		}
	}
}
