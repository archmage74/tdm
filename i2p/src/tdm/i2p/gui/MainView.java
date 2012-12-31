package tdm.i2p.gui;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tdm.i2p.IDebug;
import tdm.i2p.db.Importer;
import tdm.i2p.db.OrderValidator;

public class MainView {

	private Display display;
	
	private Text protimeNameField;
	private Text imosNameField;
	private Text hposField;
	private Text messageBox;
	
	private Importer importer;

	public MainView(Display display) {
		this.display = display;
	}
	
	public Shell show() {
		Shell shell = new Shell(display);
		fillShell(shell);
		if (IDebug.DEBUG) {
			prefillDebugData();
		}
		shell.pack();
		shell.open();
		
		importer = new Importer();
		importer.setMessageBox(messageBox);

		return shell;
	}
	
	private void fillShell(Shell shell) {
		shell.setText("i2p");
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.marginTop = 20;
		gridLayout.marginBottom = 20;
		gridLayout.marginLeft = 20;
		gridLayout.marginRight = 20;

		shell.setLayout(gridLayout);
		
		// betriebsauftrag_no (Protime)
		GridData protimeNameLabelData = new GridData();
		protimeNameLabelData.horizontalAlignment = SWT.END;
		protimeNameLabelData.grabExcessHorizontalSpace = false;
				
		Label protimeNameLabel = new Label(shell, SWT.NONE);
		protimeNameLabel.setText("Protime Name:");
		protimeNameLabel.setLayoutData(protimeNameLabelData);
		protimeNameLabel.pack();
		
		GridData protimeNameTextData = new GridData();
		protimeNameTextData.horizontalAlignment = GridData.FILL;
		protimeNameTextData.grabExcessHorizontalSpace = true;
		protimeNameTextData.minimumWidth = 200;

		protimeNameField = new Text(shell, SWT.NONE);
		protimeNameField.setText("");
		protimeNameField.setLayoutData(protimeNameTextData);
		protimeNameField.pack();

		// proadmin name (IMOS)
		GridData imosNameLabelData = new GridData();
		imosNameLabelData.horizontalAlignment = GridData.END;
		imosNameLabelData.grabExcessHorizontalSpace = false;
		
		Label imosNameLabel = new Label(shell, SWT.NONE);
		imosNameLabel.setText("IMOS Name:");
		imosNameLabel.setLayoutData(imosNameLabelData);
		imosNameLabel.pack();
		
		GridData imosNameTextData = new GridData();
		imosNameTextData.horizontalAlignment = GridData.FILL;
		imosNameTextData.grabExcessHorizontalSpace = true;
		imosNameTextData.minimumWidth = 200;

		imosNameField = new Text(shell, SWT.NONE);
		imosNameField.setText("");
		imosNameField.setLayoutData(imosNameTextData);
		imosNameField.pack();
		
		// hpos field
		GridData hposLabelData = new GridData();
		hposLabelData.horizontalAlignment = GridData.END;
		hposLabelData.grabExcessHorizontalSpace = false;
		
		Label hposLabel = new Label(shell, SWT.NONE);
		hposLabel.setText("HPOS:");
		hposLabel.setLayoutData(hposLabelData);
		hposLabel.pack();
		
		GridData hposTextData = new GridData();
		hposTextData.horizontalAlignment = GridData.FILL;
		hposTextData.grabExcessHorizontalSpace = true;
		hposTextData.minimumWidth = 200;

		hposField = new Text(shell, SWT.NONE);
		hposField.setText("");
		hposField.setLayoutData(hposTextData);
		hposField.pack();
		
		createReadButton(shell, protimeNameField, imosNameField, hposField);
		
		// message box
		GridData messageBoxData = new GridData();
		messageBoxData.horizontalAlignment = GridData.FILL;
		messageBoxData.grabExcessHorizontalSpace = true;
		messageBoxData.grabExcessVerticalSpace = true;
		messageBoxData.horizontalSpan = 2;
		messageBoxData.minimumWidth = 400;
		messageBoxData.minimumHeight = 100;
		
		messageBox = new Text(shell, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		messageBox.setEditable(false);
		messageBox.setLayoutData(messageBoxData);
		messageBox.pack();

	}

	private void createReadButton(Shell shell, final Text protimeNameField, final Text imosNameField, final Text hposField) {
		// button
		GridData startButtonData = new GridData();
		startButtonData.horizontalAlignment = GridData.BEGINNING;
		startButtonData.grabExcessHorizontalSpace = false;
		startButtonData.horizontalSpan = 2;
		
		Button startButton = new Button(shell, SWT.NONE);
		startButton.setText("Einlesen");
		startButton.setLayoutData(startButtonData);
		
		startButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				messageBox.setText("");
				String protimeName = protimeNameField.getText();
				String imosName = imosNameField.getText();
				boolean ordersValid = validateOrders(protimeName, imosName);

				String hpos = hposField.getText();
				boolean hposValid = validateHpos(hpos);
				
				if (ordersValid && hposValid) {
					int hposInt = Integer.parseInt(hposField.getText());
					importer.readFromImos(imosName);
					openMaterialsSelection(protimeName, hposInt);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		startButton.pack();
	}
	
	private void openMaterialsSelection(String protimeName, int hpos) {
		MaterialView materialView = new MaterialView(display);
		materialView.setMaterials(importer.getMainMaterials());
		materialView.setProfileMaterials(importer.getProfileMaterials());
		materialView.setSurfaceMaterials(importer.getSurfaceMaterials());
		materialView.setProtimeName(protimeName);
		materialView.setHpos(hpos);
		materialView.setImporter(importer);
		materialView.show();
	}

	private boolean validateOrders(String protimeName, String imosName) {
		boolean valid = true;
		try {
			boolean protimeValid = OrderValidator.validateProtime(protimeName);
			if (protimeValid == false) {
				messageBox.append("Protime Name ist nicht gueltig! (Gross/Kleinschreibung beachten)\n");
				valid = false;
			}
		} catch (SQLException e) {
			messageBox.append("Fehler beim Aufbau zur Protime Datenbank: " + e.getMessage() + "\n");
			valid = false;
		}
		
		try {
			boolean imosValid = OrderValidator.validateImos(imosName);
			if (imosValid == false) {
				messageBox.append("IMOS Name ist nicht gueltig!\n");
				valid = false;
			}
		} catch (SQLException e) {
			messageBox.append("Fehler beim Aufbau zur Imos Datenbank: " + e.getMessage() + "\n");
			valid = false;
		}
		return valid;
	}
	
	private boolean validateHpos(String hpos) {
		if (hpos == null || hpos.equalsIgnoreCase("")) {
			messageBox.append("HPOS nicht gueltig!\n");
			return false;
		}
		if (hpos.length() > 3) {
			messageBox.append("HPOS darf nicht mehr als 3 Stellen haben!\n");
			return false;
		}
		try {
			Integer.parseInt(hpos);
		} catch (NumberFormatException e) {
			messageBox.append("HPOS nicht gueltig!\n");
			return false;
		}
		return true;
	}

	private void prefillDebugData() {
		protimeNameField.setText(IDebug.PROTIME_NAME);
		imosNameField.setText(IDebug.IMOS_NAME);
		hposField.setText(IDebug.HPOS);
	}

}
