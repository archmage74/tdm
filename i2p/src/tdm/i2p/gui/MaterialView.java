package tdm.i2p.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tdm.i2p.db.Importer;

public class MaterialView {

	Display display;
	
	private String protimeName;
	private int hpos;
	private HashMap<String, Button> materials;
	private Collection<String> profileMaterials;
	private Collection<String> surfaceMaterials;
	
	private Importer importer;
	
	public MaterialView(Display display) {
		this.display = display;
	}

	public Shell show() {
		Shell shell = new Shell(display);
		fillShell(shell);
		shell.pack();
		shell.open();
		return shell;
	}
	
	private void fillShell(Shell shell) {
		shell.setText("Materialenauswahl");

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.marginTop = 20;
		gridLayout.marginBottom = 20;
		gridLayout.marginLeft = 20;
		gridLayout.marginRight = 20;

		shell.setLayout(gridLayout);

		createMaterialList(shell);
		createProfileMaterialList(shell);
		createSurfaceMaterialList(shell);
		createImportButton(shell);
	}

	private void createMaterialList(Shell shell) {
		for (Map.Entry<String, Button> mat : materials.entrySet()) {
			
			GridData buttonData = new GridData();
			buttonData.horizontalAlignment = SWT.END;

			Button button = new Button(shell, SWT.CHECK);
			button.setSelection(true);
			button.setLayoutData(buttonData);

			mat.setValue(button);
			button.pack();
			
			Text text = new Text(shell, SWT.NONE);
			text.setText(mat.getKey());
			text.pack();
		}
	}
	
	private void createProfileMaterialList(Shell shell) {
		Group profileGroup = new Group(shell, SWT.NONE);
		GridData profileData = new GridData();
		profileData.verticalAlignment = SWT.BEGINNING;
		profileData.grabExcessHorizontalSpace = true;
		profileGroup.setLayoutData(profileData);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginTop = 2;
		gridLayout.marginBottom = 2;
		gridLayout.marginLeft = 2;
		gridLayout.marginRight = 2;
		profileGroup.setLayout(gridLayout);
		profileGroup.setText("Profile");

		for (String mat : profileMaterials) {
			Text text = new Text(profileGroup, SWT.NONE);
			text.setText(mat);
			text.pack();
		}
		
		profileGroup.pack();
	}
	
	private void createSurfaceMaterialList(Shell shell) {
		Group surfaceGroup = new Group(shell, SWT.NONE);
		GridData surfaceData = new GridData();
		surfaceData.verticalAlignment = SWT.BEGINNING;
		surfaceData.grabExcessHorizontalSpace = true;
		surfaceGroup.setLayoutData(surfaceData);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginTop = 2;
		gridLayout.marginBottom = 2;
		gridLayout.marginLeft = 2;
		gridLayout.marginRight = 2;
		surfaceGroup.setLayout(gridLayout);
		surfaceGroup.setText("Beläge");
		
		for (String mat : surfaceMaterials) {
			Text text = new Text(surfaceGroup, SWT.NONE);
			text.setText(mat);
			text.pack();
		}
		
		surfaceGroup.pack();
	}
	
	private void createImportButton(final Shell shell) {

		GridData importButtonData = new GridData();
		importButtonData.horizontalAlignment = GridData.BEGINNING;
		importButtonData.grabExcessHorizontalSpace = false;
		importButtonData.horizontalSpan = 2;
		
		Button importButton = new Button(shell, SWT.NONE);
		importButton.setText("Importieren");
		importButton.setLayoutData(importButtonData);
		
		importButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Collection<String> mats = getSelectedMaterials(); 
				importer.writeToProtime(protimeName, hpos, mats);
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		importButton.pack();
	}

	public Collection<String> getSelectedMaterials() {
		HashSet<String> mats = new HashSet<String>();
		for (Map.Entry<String, Button> mat : materials.entrySet()) {
			if (mat.getValue().getSelection()) {
				mats.add(mat.getKey());
			}
		}
		return mats;
	}
	
	public void setMaterials(Collection<String> materialList) {
		this.materials = new HashMap<String, Button>();
		for (String mat : materialList) {
			this.materials.put(mat, null);
		}
	}

	public String getProtimeName() {
		return protimeName;
	}

	public void setProtimeName(String protimeName) {
		this.protimeName = protimeName;
	}

	public Importer getImporter() {
		return importer;
	}

	public void setImporter(Importer importer) {
		this.importer = importer;
	}

	public int getHpos() {
		return hpos;
	}

	public void setHpos(int hpos) {
		this.hpos = hpos;
	}

	public Collection<String> getProfileMaterials() {
		return profileMaterials;
	}

	public void setProfileMaterials(Collection<String> profileMaterials) {
		this.profileMaterials = new ArrayList<String>(profileMaterials);
	}

	public Collection<String> getSurfaceMaterials() {
		return surfaceMaterials;
	}

	public void setSurfaceMaterials(Collection<String> surfaceMaterials) {
		this.surfaceMaterials = new ArrayList<String>(surfaceMaterials);
	}

}