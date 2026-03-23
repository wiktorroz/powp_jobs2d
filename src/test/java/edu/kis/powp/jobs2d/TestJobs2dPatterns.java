package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DefaultDrawerFrame;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.adapter.DrawTheCorrectPattern;
import edu.kis.powp.jobs2d.drivers.adapter.LineDrawerAdapter;
import edu.kis.powp.jobs2d.events.SelectChangeVisibleOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;
import edu.kis.powp.jobs2d.drivers.adapter.AbstractDriverAdapter;
import edu.kis.powp.jobs2d.magicpresets.FiguresJane;


public class TestJobs2dPatterns {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
        //Zadanie 3.2 - Figure Joe 1. Wcześniej był listener, lecz wyoknywał on na sztywno 1 test. Teraz dodajemy 2 testy, a listenera zastępujemy lambdą.
        application.addTest("Figure Joe 1", (ActionEvent e) ->
                FiguresJoe.figureScript1(DriverFeature.getDriverManager().getCurrentDriver())
        );
        //Zadanie 3.3 - Figure Joe 2
        application.addTest("Figure Joe 2", (ActionEvent e) ->
                FiguresJoe.figureScript2(DriverFeature.getDriverManager().getCurrentDriver())
        );
        //Zadanie 3.4 - Figure Jane
        application.addTest("Figure Jane", (ActionEvent e) -> {
            AbstractDriverAdapter adapter = new AbstractDriverAdapter();
            FiguresJane.figureScript(adapter);
        });

    }

	/**
	 * Setup driver manager, and set default driver for application.
	 * 
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		Job2dDriver loggerDriver = new LoggerDriver();
		DriverFeature.addDriver("Logger Driver", loggerDriver);
        // Zadanie 3.2 - podstawowy adapter
        Job2dDriver basicDriver = new DrawTheCorrectPattern();
        DriverFeature.addDriver("Basic Simulator", basicDriver);
        // Zadanie 3.3 - adaptery z różnymi typami linii
        Job2dDriver specialLineDriver = new LineDrawerAdapter(LineFactory.getSpecialLine());
        DriverFeature.addDriver("Special Line", specialLineDriver);
        Job2dDriver dottedLineDriver = new LineDrawerAdapter(LineFactory.getDottedLine());
        DriverFeature.addDriver("Dotted Line", dottedLineDriver);
        // Ustaw domyślny driver
        DriverFeature.getDriverManager().setCurrentDriver(basicDriver);
        DriverFeature.updateDriverInfo();
	}

	/**
	 * Auxiliary routines to enable using Buggy Simulator.
	 * 
	 * @param application Application context.
	 */
	private static void setupDefaultDrawerVisibilityManagement(Application application) {
		DefaultDrawerFrame defaultDrawerWindow = DefaultDrawerFrame.getDefaultDrawerFrame();
		application.addComponentMenuElementWithCheckBox(DrawPanelController.class, "Default Drawer Visibility",
				new SelectChangeVisibleOptionListener(defaultDrawerWindow), true);
		defaultDrawerWindow.setVisible(true);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {
		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("2d jobs Visio");
				DrawerFeature.setupDrawerPlugin(app);


				DriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupLogger(app);

				app.setVisibility(true);
			}
		});
	}

}
