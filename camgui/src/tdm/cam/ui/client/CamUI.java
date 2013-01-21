package tdm.cam.ui.client;

import java.util.ArrayList;
import java.util.Collection;

import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProject;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CamUI implements EntryPoint {

	private RootPanel rootPanel;
	private WindowSize windowSize;
	
	private Panel mainPanel;
	
	private ListBox partListBox;

	private Collection<IDisplayPart> partDisplays = new ArrayList<IDisplayPart>();
	private DrawPartSketchCmd drawPartSketchCmd;
	
	public void onModuleLoad() {
		// TODO get window size
		rootPanel = RootPanel.get("main");
		windowSize = WindowSize.getInstance(); 
		
		rootPanel.add(createMainPanel());
	}
	
	public void displayProject(ImosProject project) {
		partListBox.clear();
		for (ImosPart part : project.getParts()) {
			partListBox.addItem(part.getBarcode());
		}
		drawPartSketchCmd.setParts(project.getParts());
	}
	
	private Panel createMainPanel() {
		mainPanel = new HorizontalPanel();
		mainPanel.add(createProjectPanel());
		mainPanel.add(createPartDetailsPanel());

		createDrawPartSketchCmd();
		
		return mainPanel;
	}
	
	private Panel createPartDetailsPanel() {
		VerticalPanel panel = new VerticalPanel();
		panel.add(createSketchPanel());
		panel.add(createPartStatsPanel());
		return panel;
	}
	
	private Panel createPartStatsPanel() {
		SimplePanel panel = new SimplePanel();
		PartStatsView partStatsView = new PartStatsView(panel);
		partDisplays.add(partStatsView);
		
		return panel;
	}
	
	private void createDrawPartSketchCmd() {
		drawPartSketchCmd = new DrawPartSketchCmd(partListBox, partDisplays);
		partListBox.addChangeHandler(drawPartSketchCmd);
	}

	private Panel createSketchPanel() {
		VerticalPanel panel = new VerticalPanel();
		Canvas sketchCanvas = Canvas.createIfSupported();
		sketchCanvas.setStyleName(CssStyles.SKETCH_CANVAS);
		sketchCanvas.setWidth("1080px");
		sketchCanvas.setHeight("420px");
		SketchView sketch = new SketchView(sketchCanvas);
		partDisplays.add(sketch);
		panel.add(sketchCanvas);
		return panel;
	}

	private Panel createProjectPanel() {
		VerticalPanel panel = new VerticalPanel();
		panel.add(createProjectSelection());
		panel.add(createPartListBox());
		return panel;
	}
	
	private Widget createProjectSelection() {
		HorizontalPanel panel = new HorizontalPanel();

		panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		Label orderIdLabel = new Label("OrderId:");
		panel.add(orderIdLabel);
		
		TextBox orderIdTextBox = new TextBox();
		panel.add(orderIdTextBox);
		
		Button loadProjectButton = new Button("Projekt laden");
		panel.add(loadProjectButton);
		
		LoadProjectCmd loadProjectCommand = new LoadProjectCmd(this, orderIdTextBox);
		loadProjectButton.addClickHandler(loadProjectCommand);

		return panel;
	}

	private ListBox createPartListBox() {
		partListBox = new ListBox();
		partListBox.setHeight(windowSize.getHeight() - 250 + "px");
		partListBox.setWidth("300px");
		partListBox.setVisibleItemCount(20);
		return partListBox;
	}

}
