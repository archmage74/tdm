package tdm.cam.ui.client;

import java.util.ArrayList;
import java.util.Collection;

import tdm.cam.model.math.RotationMatrixFactory;
import tdm.cam.ui.client.prj.Project;
import tdm.cam.ui.client.sketch.BackDrillingFilter;
import tdm.cam.ui.client.sketch.HorizontalDrillingFilter;
import tdm.cam.ui.client.sketch.SketchView;
import tdm.cam.ui.client.sketch.transform.FrontCoordinateTransformerFactory;

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

	private static final int PROJECT_WIDTH = 250;
	private static final int STATS_WIDTH = 250;
	private static final int SKETCH_WIDTH = 972;
	private static final int SKETCH_HEIGHT = 378;

	private RotationMatrixFactory rotationMatrixFactory = new RotationMatrixFactory();
	
	private RootPanel rootPanel;
	private WindowSize windowSize;
	
	private Panel panel;
	
	private ListBox partListBox;

	private Project project = new Project();
	private Collection<IDisplayPart> partDisplays = new ArrayList<IDisplayPart>();
	private Collection<IDisplayProject> projectDisplays = new ArrayList<IDisplayProject>();
	private DrawPartCmd drawPartCmd;
	private LoadProjectCmd loadProjectCmd;
	private RotatePartCmd rotatePartCmd;
	
	public void onModuleLoad() {
		// TODO get window size
		rootPanel = RootPanel.get("main");
		windowSize = WindowSize.getInstance(); 
		
		rootPanel.add(createMainPanel());
	}
	
	private Panel createMainPanel() {
		panel = new HorizontalPanel();
		panel.add(createProjectPanel());
		panel.add(createPartStatsPanel());
		panel.add(createSketchPanel());

		createDrawPartSketchCmd();
		createRotatePartCmd();
		
		return panel;
	}
	
	private Panel createPartStatsPanel() {
		SimplePanel panel = new SimplePanel();
		panel.setWidth(STATS_WIDTH + "px");
		PartStatsView partStatsView = new PartStatsView(panel);
		partDisplays.add(partStatsView);
		
		return panel;
	}
	
	// TODO add IOC
	private void createRotatePartCmd() {
		if (drawPartCmd == null) {
			throw new RuntimeException("drawPartCmd must be initialized");
		}
		rotatePartCmd = new RotatePartCmd(project, drawPartCmd);
		partListBox.addKeyDownHandler(rotatePartCmd);
	}
	
	// TODO add IOC
	private void createDrawPartSketchCmd() {
		drawPartCmd = new DrawPartCmd(partListBox, partDisplays);
		projectDisplays.add(drawPartCmd);
		partListBox.addChangeHandler(drawPartCmd);
	}

	private Panel createSketchPanel() {
		VerticalPanel panel = new VerticalPanel();
		panel.setWidth(SKETCH_WIDTH + "px");
		panel.add(createFrontSketchPanel());
		panel.add(createBackSketchPanel());
		return panel;
	}
	
	private Widget createFrontSketchPanel() {
		Canvas sketchCanvas = Canvas.createIfSupported();
		sketchCanvas.setStyleName(CssStyles.SKETCH_CANVAS);
		sketchCanvas.setWidth(SKETCH_WIDTH + "px");
		sketchCanvas.setHeight(SKETCH_HEIGHT + "px");
		SketchView sketch = new SketchView(sketchCanvas);
		sketch.addDrillingFilter(new BackDrillingFilter());
		sketch.addDrillingFilter(new HorizontalDrillingFilter());
		sketch.setTransformerFactory(new FrontCoordinateTransformerFactory());
		partDisplays.add(sketch);
		return sketchCanvas;
	}

	private Widget createBackSketchPanel() {
		Canvas sketchCanvas = Canvas.createIfSupported();
		sketchCanvas.setStyleName(CssStyles.SKETCH_CANVAS);
		sketchCanvas.setWidth(SKETCH_WIDTH + "px");
		sketchCanvas.setHeight(SKETCH_HEIGHT + "px");
		SketchView sketch = new SketchView(sketchCanvas);
		sketch.setViewRotation(rotationMatrixFactory.createYRotationInDegrees(180));
		sketch.addDrillingFilter(new BackDrillingFilter());
		sketch.addDrillingFilter(new HorizontalDrillingFilter());
		sketch.setTransformerFactory(new FrontCoordinateTransformerFactory());
		partDisplays.add(sketch);
		return sketchCanvas;
	}

	private Panel createProjectPanel() {
		VerticalPanel panel = new VerticalPanel();
		panel.setWidth(PROJECT_WIDTH + "px");
		panel.add(createProjectSelection());
		panel.add(createPartListBox());
		panel.add(createExportTlf());
		return panel;
	}
	
	private Widget createExportTlf() {
		Button exportTlfButton = new Button(".");
		ExportTlfCmd exportTlfCmd = new ExportTlfCmd(exportTlfButton);
		projectDisplays.add(exportTlfCmd);
		return exportTlfButton;
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
		
		loadProjectCmd = new LoadProjectCmd(this.project, orderIdTextBox, projectDisplays);
		loadProjectButton.addClickHandler(loadProjectCmd);

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
