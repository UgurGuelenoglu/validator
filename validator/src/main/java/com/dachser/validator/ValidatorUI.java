package com.dachser.validator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class ValidatorUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1303492645736050924L;

	/**
	 * Constants for messages
	 */
	private final static String TO_SHORT_MESSAGE = "Die NVE/SSCC Nummer ist zu kurz.";
	private final static String TO_LONG_MESSAGE = "Die NVE/SSCC Nummer ist zu lang.";
	private final static String NO_NVE_INPUT_MESSAGE = "Bitte geben Sie eine NVE Nummer ein.";
	private final static String CONTAINS_IN_LIST_MESSAGE = "Die NVE/SSCC Nummer ist bereits in der Liste vorhanden.";
	private final static String SAVED_CORRECTLY_MESSAGE = "Die NVE/SCCS wurde gespeichert.";
	private final static String NOT_VALID_MESSAGE = "Die eingegebene NVE/SSCC ist nicht valide.\nVersuchen Sie es erneut.";

	/**
	 * Components for UI
	 */
	private Label lb_header;
	private Grid<NVENumber> gr_nveNumbers = new Grid<>(NVENumber.class);
	private TextField tf_nveNumber;
	private Button bt_checkNveNumber;
	private List<NVENumber> nveNumbersList = new ArrayList<NVENumber>();
	private VerticalLayout verticalLayout = new VerticalLayout();
	private HorizontalLayout horizonmtalLayout = new HorizontalLayout();

	private INVENumber iNVENumber = new NVENumber();

	/**
	 * Inital method for the user interface
	 */
	@Override
	protected void init(VaadinRequest vaadinRequest) {

		lb_header = new Label();
		lb_header.setCaptionAsHtml(true);
		lb_header.setCaption("<h2>Nummer der Versandeinheit (NVE/SSCC) Validator</h2>");
		lb_header.setSizeFull();

		tf_nveNumber = new TextField();
		tf_nveNumber.setCaption("18-stellige NVE/SSCC hier eingeben:");

		bt_checkNveNumber = new Button("Validieren und Speichern");
		bt_checkNveNumber.addClickListener(e -> addNVENummerIfCorrect(tf_nveNumber.getValue()));
		bt_checkNveNumber.setHeight(60, Unit.PIXELS);

		horizonmtalLayout.addComponents(tf_nveNumber, bt_checkNveNumber);
		verticalLayout.addComponents(lb_header, horizonmtalLayout, gr_nveNumbers);

		setContent(verticalLayout);
	}
	
	/**
	 * Adds the NVE number if it is not in the list and if it is valid.
	 * 
	 * @param nveNumberText
	 */
	private void addNVENummerIfCorrect(String nveNumberText) {
		NVENumber nveNumber = new NVENumber();
		nveNumber.setNveNumber(nveNumberText);

		if (!checkLengthOfNVENumber(nveNumberText)) {
			
		} else if (nveNumbersList.contains(nveNumber)) {
			Notification.show(CONTAINS_IN_LIST_MESSAGE, Type.WARNING_MESSAGE);
		} else if (iNVENumber.checkNVENumber(nveNumberText)) {
			nveNumbersList.add(nveNumber);
			gr_nveNumbers.setItems(nveNumbersList);
			Notification.show(SAVED_CORRECTLY_MESSAGE, Type.TRAY_NOTIFICATION);
		} else {
			Notification.show(NOT_VALID_MESSAGE, Type.WARNING_MESSAGE);
		}
	}

	/**
	 * Checks the length of the input NVE Number
	 * @param nveNumberText
	 * @return <code>true</code> if NVE number is to short or to long, <code>false if it is ok</code>
	 */
	private boolean checkLengthOfNVENumber(String nveNumberText) {

		if(nveNumberText.length() == 0) {
			Notification.show(NO_NVE_INPUT_MESSAGE, Type.WARNING_MESSAGE);
			return false;
		}else if (nveNumberText.length() - 1 < 17) {
			Notification.show(TO_SHORT_MESSAGE, Type.WARNING_MESSAGE);
			return false;
		} else if (nveNumberText.length() - 1 > 17) {
			Notification.show(TO_LONG_MESSAGE, Type.WARNING_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	@WebServlet(urlPatterns = "/*", name = "ValidatorUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = ValidatorUI.class, productionMode = false)
	public static class ValidatorUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7245844553782883097L;
	}

}
