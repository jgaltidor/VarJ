/*
 * @(#)Toolkit.java	1.221 06/04/07
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.awt;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.awt.event.*;
import java.awt.peer.*;
import java.awt.*;
import java.awt.im.InputMethodHighlight;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.net.URL;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;

import java.util.EventListener;
import java.util.Map;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.List;
import java.util.ArrayList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import sun.awt.DebugHelper;
import sun.awt.HeadlessToolkit;
import sun.awt.NullComponentPeer;
import sun.security.util.SecurityConstants;

import sun.util.CoreResourceBundleControl;

/**
 * This class is the abstract superclass of all actual
 * implementations of the Abstract Window Toolkit. Subclasses of
 * <code>Toolkit</code> are used to bind the various components
 * to particular native toolkit implementations.
 * <p>
 * Many GUI operations may be performed asynchronously.  This
 * means that if you set the state of a component, and then 
 * immediately query the state, the returned value may not yet
 * reflect the requested change.  This includes, but is not
 * limited to:
 * <ul>
 * <li>Scrolling to a specified position.
 * <br>For example, calling <code>ScrollPane.setScrollPosition</code>
 *     and then <code>getScrollPosition</code> may return an incorrect
 *     value if the original request has not yet been processed.
 * <p>
 * <li>Moving the focus from one component to another.
 * <br>For more information, see
 * <a href="http://java.sun.com/docs/books/tutorial/uiswing/misc/focus.html#transferTiming">Timing
 * Focus Transfers</a>, a section in
 * <a href="http://java.sun.com/docs/books/tutorial/uiswing/">The Swing
 * Tutorial</a>.
 * <p>
 * <li>Making a top-level container visible.
 * <br>Calling <code>setVisible(true)</code> on a <code>Window</code>,
 *     <code>Frame</code> or <code>Dialog</code> may occur
 *     asynchronously.
 * <p>
 * <li>Setting the size or location of a top-level container.
 * <br>Calls to <code>setSize</code>, <code>setBounds</code> or
 *     <code>setLocation</code> on a <code>Window</code>, 
 *     <code>Frame</code> or <code>Dialog</code> are forwarded
 *     to the underlying window management system and may be
 *     ignored or modified.  See {@link java.awt.Window} for
 *     more information.
 * </ul>
 * <p>
 * Most applications should not call any of the methods in this
 * class directly. The methods defined by <code>Toolkit</code> are
 * the "glue" that joins the platform-independent classes in the
 * <code>java.awt</code> package with their counterparts in
 * <code>java.awt.peer</code>. Some methods defined by
 * <code>Toolkit</code> query the native operating system directly.
 *
 * @version 	1.203, 12/19/03
 * @author	Sami Shaio
 * @author	Arthur van Hoff
 * @author	Fred Ecks
 * @since       JDK1.0
 */
public abstract class  Toolkit {

    /**
     * Creates this toolkit's implementation of the <code>Desktop</code>
     * using the specified peer interface.
     * @param     target the desktop to be implemented
     * @return    this toolkit's implementation of the <code>Desktop</code>
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.Desktop
     * @see       java.awt.peer.DesktopPeer
     * @since 1.6
     */
    protected abstract DesktopPeer createDesktopPeer(Desktop target)
      throws HeadlessException;


    /**
     * Creates this toolkit's implementation of <code>Button</code> using
     * the specified peer interface.
     * @param     target the button to be implemented.
     * @return    this toolkit's implementation of <code>Button</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.Button
     * @see       java.awt.peer.ButtonPeer
     */
    protected abstract ButtonPeer createButton(Button target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>TextField</code> using
     * the specified peer interface.
     * @param     target the text field to be implemented.
     * @return    this toolkit's implementation of <code>TextField</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.TextField
     * @see       java.awt.peer.TextFieldPeer
     */
    protected abstract TextFieldPeer createTextField(TextField target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>Label</code> using
     * the specified peer interface.
     * @param     target the label to be implemented.
     * @return    this toolkit's implementation of <code>Label</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.Label
     * @see       java.awt.peer.LabelPeer
     */
    protected abstract LabelPeer createLabel(Label target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>List</code> using
     * the specified peer interface.
     * @param     target the list to be implemented.
     * @return    this toolkit's implementation of <code>List</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.List
     * @see       java.awt.peer.ListPeer
     */
    protected abstract ListPeer createList(java.awt.List target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>Checkbox</code> using
     * the specified peer interface.
     * @param     target the check box to be implemented.
     * @return    this toolkit's implementation of <code>Checkbox</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.Checkbox
     * @see       java.awt.peer.CheckboxPeer
     */
    protected abstract CheckboxPeer createCheckbox(Checkbox target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>Scrollbar</code> using
     * the specified peer interface.
     * @param     target the scroll bar to be implemented.
     * @return    this toolkit's implementation of <code>Scrollbar</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.Scrollbar
     * @see       java.awt.peer.ScrollbarPeer
     */
    protected abstract ScrollbarPeer createScrollbar(Scrollbar target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>ScrollPane</code> using
     * the specified peer interface.
     * @param     target the scroll pane to be implemented.
     * @return    this toolkit's implementation of <code>ScrollPane</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.ScrollPane
     * @see       java.awt.peer.ScrollPanePeer
     * @since     JDK1.1
     */
    protected abstract ScrollPanePeer createScrollPane(ScrollPane target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>TextArea</code> using
     * the specified peer interface.
     * @param     target the text area to be implemented.
     * @return    this toolkit's implementation of <code>TextArea</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.TextArea
     * @see       java.awt.peer.TextAreaPeer
     */
    protected abstract TextAreaPeer createTextArea(TextArea target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>Choice</code> using
     * the specified peer interface.
     * @param     target the choice to be implemented.
     * @return    this toolkit's implementation of <code>Choice</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.Choice
     * @see       java.awt.peer.ChoicePeer
     */
    protected abstract ChoicePeer createChoice(Choice target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>Frame</code> using
     * the specified peer interface.
     * @param     target the frame to be implemented.
     * @return    this toolkit's implementation of <code>Frame</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.Frame
     * @see       java.awt.peer.FramePeer
     */
    protected abstract FramePeer createFrame(Frame target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>Canvas</code> using
     * the specified peer interface.
     * @param     target the canvas to be implemented.
     * @return    this toolkit's implementation of <code>Canvas</code>.
     * @see       java.awt.Canvas
     * @see       java.awt.peer.CanvasPeer
     */
    protected abstract CanvasPeer 	createCanvas(Canvas target);

    /**
     * Creates this toolkit's implementation of <code>Panel</code> using
     * the specified peer interface.
     * @param     target the panel to be implemented.
     * @return    this toolkit's implementation of <code>Panel</code>.
     * @see       java.awt.Panel
     * @see       java.awt.peer.PanelPeer
     */
    protected abstract PanelPeer  	createPanel(Panel target);

    /**
     * Creates this toolkit's implementation of <code>Window</code> using
     * the specified peer interface.
     * @param     target the window to be implemented.
     * @return    this toolkit's implementation of <code>Window</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.Window
     * @see       java.awt.peer.WindowPeer
     */
    protected abstract WindowPeer createWindow(Window target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>Dialog</code> using
     * the specified peer interface.
     * @param     target the dialog to be implemented.
     * @return    this toolkit's implementation of <code>Dialog</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.Dialog
     * @see       java.awt.peer.DialogPeer
     */
    protected abstract DialogPeer createDialog(Dialog target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>MenuBar</code> using
     * the specified peer interface.
     * @param     target the menu bar to be implemented.
     * @return    this toolkit's implementation of <code>MenuBar</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.MenuBar
     * @see       java.awt.peer.MenuBarPeer
     */
    protected abstract MenuBarPeer createMenuBar(MenuBar target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>Menu</code> using
     * the specified peer interface.
     * @param     target the menu to be implemented.
     * @return    this toolkit's implementation of <code>Menu</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.Menu
     * @see       java.awt.peer.MenuPeer
     */
    protected abstract MenuPeer createMenu(Menu target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>PopupMenu</code> using
     * the specified peer interface.
     * @param     target the popup menu to be implemented.
     * @return    this toolkit's implementation of <code>PopupMenu</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.PopupMenu
     * @see       java.awt.peer.PopupMenuPeer
     * @since     JDK1.1
     */
    protected abstract PopupMenuPeer createPopupMenu(PopupMenu target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>MenuItem</code> using
     * the specified peer interface.
     * @param     target the menu item to be implemented.
     * @return    this toolkit's implementation of <code>MenuItem</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.MenuItem
     * @see       java.awt.peer.MenuItemPeer
     */
    protected abstract MenuItemPeer createMenuItem(MenuItem target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>FileDialog</code> using
     * the specified peer interface.
     * @param     target the file dialog to be implemented.
     * @return    this toolkit's implementation of <code>FileDialog</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.FileDialog
     * @see       java.awt.peer.FileDialogPeer
     */
    protected abstract FileDialogPeer createFileDialog(FileDialog target)
        throws HeadlessException;

    /**
     * Creates this toolkit's implementation of <code>CheckboxMenuItem</code> using
     * the specified peer interface.
     * @param     target the checkbox menu item to be implemented.
     * @return    this toolkit's implementation of <code>CheckboxMenuItem</code>.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.CheckboxMenuItem
     * @see       java.awt.peer.CheckboxMenuItemPeer
     */
    protected abstract CheckboxMenuItemPeer createCheckboxMenuItem(
        CheckboxMenuItem target) throws HeadlessException;

    /**
     * Obtains this toolkit's implementation of helper class for 
     * <code>MouseInfo</code> operations.
     * @return    this toolkit's implementation of  helper for <code>MouseInfo</code>
     * @throws    UnsupportedOperationException if this operation is not implemented
     * @see       java.awt.peer.MouseInfoPeer
     * @see       java.awt.MouseInfo
     * @since 1.5
     */
    protected MouseInfoPeer getMouseInfoPeer() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private static LightweightPeer lightweightMarker;

    /**
     * Creates a peer for a component or container.  This peer is windowless
     * and allows the Component and Container classes to be extended directly
     * to create windowless components that are defined entirely in java.
     *
     * @param target The Component to be created.
     */
    protected LightweightPeer createComponent(Component target) {
        if (lightweightMarker == null) {
            lightweightMarker = new NullComponentPeer();
        }
        return lightweightMarker;
    }

    /**
     * Creates this toolkit's implementation of <code>Font</code> using
     * the specified peer interface.
     * @param     name the font to be implemented
     * @param     style the style of the font, such as <code>PLAIN</code>,
     *            <code>BOLD</code>, <code>ITALIC</code>, or a combination
     * @return    this toolkit's implementation of <code>Font</code>
     * @see       java.awt.Font
     * @see       java.awt.peer.FontPeer
     * @see       java.awt.GraphicsEnvironment#getAllFonts
     * @deprecated  see java.awt.GraphicsEnvironment#getAllFonts
     */
    @Deprecated
    protected abstract FontPeer getFontPeer(String name, int style);

    // The following method is called by the private method
    // <code>updateSystemColors</code> in <code>SystemColor</code>.

    /**
     * Fills in the integer array that is supplied as an argument
     * with the current system color values.
     *
     * @param     systemColors an integer array.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since     JDK1.1
     */
    protected void loadSystemColors(int[] systemColors)
        throws HeadlessException {
    }

    /**
     * Controls whether the layout of Containers is validated dynamically
     * during resizing, or statically, after resizing is complete.
     * Note that this feature is not supported on all platforms, and
     * conversely, that this feature cannot be turned off on some platforms.
     * On platforms where dynamic layout during resize is not supported
     * (or is always supported), setting this property has no effect.
     * Note that this feature can be set or unset as a property of the
     * operating system or window manager on some platforms.  On such
     * platforms, the dynamic resize property must be set at the operating
     * system or window manager level before this method can take effect.
     * This method does not change the underlying operating system or
     * window manager support or settings.  The OS/WM support can be
     * queried using getDesktopProperty("awt.dynamicLayoutSupported").
     *
     * @param     dynamic  If true, Containers should re-layout their
     *            components as the Container is being resized.  If false,
     *            the layout will be validated after resizing is finished.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     *            returns true
     * @see       #isDynamicLayoutSet()
     * @see       #isDynamicLayoutActive()
     * @see       #getDesktopProperty(String propertyName)
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since     1.4
     */
    public void setDynamicLayout(boolean dynamic)
        throws HeadlessException {
    }

    /**
     * Returns whether the layout of Containers is validated dynamically
     * during resizing, or statically, after resizing is complete.
     * Note: this method returns the value that was set programmatically;
     * it does not reflect support at the level of the operating system
     * or window manager for dynamic layout on resizing, or the current
     * operating system or window manager settings.  The OS/WM support can
     * be queried using getDesktopProperty("awt.dynamicLayoutSupported").
     *
     * @return    true if validation of Containers is done dynamically,
     *            false if validation is done after resizing is finished.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     *            returns true
     * @see       #setDynamicLayout(boolean dynamic)
     * @see       #isDynamicLayoutActive()
     * @see       #getDesktopProperty(String propertyName)
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since     1.4
     */
    protected boolean isDynamicLayoutSet()
        throws HeadlessException {
        if (this != Toolkit.getDefaultToolkit()) {
            return Toolkit.getDefaultToolkit().isDynamicLayoutSet();
        } else {
            return false;
        }
    }

    /**
     * Returns whether dynamic layout of Containers on resize is
     * currently active (both set programmatically, and supported
     * by the underlying operating system and/or window manager).
     * The OS/WM support can be queried using 
     * getDesktopProperty("awt.dynamicLayoutSupported").
     *
     * @return    true if dynamic layout of Containers on resize is
     *            currently active, false otherwise.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     *            returns true
     * @see       #setDynamicLayout(boolean dynamic)
     * @see       #isDynamicLayoutSet()
     * @see       #getDesktopProperty(String propertyName)
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since     1.4
     */
    public boolean isDynamicLayoutActive()
        throws HeadlessException {
        if (this != Toolkit.getDefaultToolkit()) {
            return Toolkit.getDefaultToolkit().isDynamicLayoutActive();
        } else {
            return false;
        }
    }

    /**
     * Gets the size of the screen.  On systems with multiple displays, the
     * primary display is used.  Multi-screen aware display dimensions are
     * available from <code>GraphicsConfiguration</code> and
     * <code>GraphicsDevice</code>.
     * @return    the size of this toolkit's screen, in pixels.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsConfiguration#getBounds
     * @see       java.awt.GraphicsDevice#getDisplayMode
     * @see       java.awt.GraphicsEnvironment#isHeadless
     */
    public abstract Dimension getScreenSize()
        throws HeadlessException;

    /**
     * Returns the screen resolution in dots-per-inch.
     * @return    this toolkit's screen resolution, in dots-per-inch.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     */
    public abstract int getScreenResolution()
        throws HeadlessException;

    /**
     * Gets the insets of the screen.
     * @param     gc a <code>GraphicsConfiguration</code>
     * @return    the insets of this toolkit's screen, in pixels.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since     1.4
     */
    public Insets getScreenInsets(GraphicsConfiguration gc)
        throws HeadlessException {
        if (this != Toolkit.getDefaultToolkit()) {
            return Toolkit.getDefaultToolkit().getScreenInsets(gc);
        } else {
            return new Insets(0, 0, 0, 0);
        }
    }
    
    /**
     * Determines the color model of this toolkit's screen.
     * <p>
     * <code>ColorModel</code> is an abstract class that
     * encapsulates the ability to translate between the
     * pixel values of an image and its red, green, blue,
     * and alpha components.
     * <p>
     * This toolkit method is called by the
     * <code>getColorModel</code> method
     * of the <code>Component</code> class.
     * @return    the color model of this toolkit's screen.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.image.ColorModel
     * @see       java.awt.Component#getColorModel
     */
    public abstract ColorModel getColorModel()
        throws HeadlessException;

    /**
     * Returns the names of the available fonts in this toolkit.<p>
     * For 1.1, the following font names are deprecated (the replacement
     * name follows):
     * <ul>
     * <li>TimesRoman (use Serif)
     * <li>Helvetica (use SansSerif)
     * <li>Courier (use Monospaced)
     * </ul><p>
     * The ZapfDingbats fontname is also deprecated in 1.1 but the characters
     * are defined in Unicode starting at 0x2700, and as of 1.1 Java supports
     * those characters.
     * @return    the names of the available fonts in this toolkit.
     * @deprecated see {@link java.awt.GraphicsEnvironment#getAvailableFontFamilyNames()}
     * @see java.awt.GraphicsEnvironment#getAvailableFontFamilyNames()
     */
    @Deprecated
    public abstract String[] getFontList();

    /**
     * Gets the screen device metrics for rendering of the font.
     * @param     font   a font
     * @return    the screen metrics of the specified font in this toolkit
     * @deprecated  As of JDK version 1.2, replaced by the <code>Font</code>
     *		method <code>getLineMetrics</code>.
     * @see java.awt.font.LineMetrics
     * @see java.awt.Font#getLineMetrics
     * @see java.awt.GraphicsEnvironment#getScreenDevices
     */
    @Deprecated
    public abstract FontMetrics getFontMetrics(Font font);

    /**
     * Synchronizes this toolkit's graphics state. Some window systems
     * may do buffering of graphics events.
     * <p>
     * This method ensures that the display is up-to-date. It is useful
     * for animation.
     */
    public abstract void sync();

    /**
     * The default toolkit.
     */
    private static Toolkit toolkit;

    /**
     * Used internally by the assistive technologies functions; set at
     * init time and used at load time
     */
    private static String atNames;

    /**
     * Initializes properties related to assistive technologies.
     * These properties are used both in the loadAssistiveProperties()
     * function below, as well as other classes in the jdk that depend
     * on the properties (such as the use of the screen_magnifier_present
     * property in Java2D hardware acceleration initialization).  The
     * initialization of the properties must be done before the platform-
     * specific Toolkit class is instantiated so that all necessary
     * properties are set up properly before any classes dependent upon them
     * are initialized.
     */
    private static void initAssistiveTechnologies() {

	// Get accessibility properties 
        final String sep = File.separator;
        final Properties properties = new Properties();


	atNames = (String)java.security.AccessController.doPrivileged(
	    new java.security.PrivilegedAction() {
	    public Object run() {

		// Try loading the per-user accessibility properties file.
		try {
		    File propsFile = new File(
		      System.getProperty("user.home") +
		      sep + ".accessibility.properties");
		    FileInputStream in =
			new FileInputStream(propsFile);

                    // Inputstream has been buffered in Properties class
		    properties.load(in);
		    in.close();
		} catch (Exception e) {
		    // Per-user accessibility properties file does not exist
		}

		// Try loading the system-wide accessibility properties
		// file only if a per-user accessibility properties
		// file does not exist or is empty.
		if (properties.size() == 0) {
		    try {
			File propsFile = new File(
			    System.getProperty("java.home") + sep + "lib" +
			    sep + "accessibility.properties");
			FileInputStream in =
			    new FileInputStream(propsFile);
			
			// Inputstream has been buffered in Properties class
			properties.load(in);
			in.close();
		    } catch (Exception e) {
			// System-wide accessibility properties file does 
			// not exist;
		    }
		}
		
		// Get whether a screen magnifier is present.  First check
		// the system property and then check the properties file.
		String magPresent = System.getProperty("javax.accessibility.screen_magnifier_present");
		if (magPresent == null) {
		    magPresent = properties.getProperty("screen_magnifier_present", null);
		    if (magPresent != null) {
			System.setProperty("javax.accessibility.screen_magnifier_present", magPresent);
		    }
		}

		// Get the names of any assistive technolgies to load.  First 
		// check the system property and then check the properties 
		// file.
		String classNames = System.getProperty("javax.accessibility.assistive_technologies");
		if (classNames == null) {
		    classNames = properties.getProperty("assistive_technologies", null);
		    if (classNames != null) {
			System.setProperty("javax.accessibility.assistive_technologies", classNames);
		    }
		}
		return classNames;
	    }
	});
    }

    /**
     * Loads additional classes into the VM, using the property
     * 'assistive_technologies' specified in the Sun reference
     * implementation by a line in the 'accessibility.properties'
     * file.  The form is "assistive_technologies=..." where
     * the "..." is a comma-separated list of assistive technology
     * classes to load.  Each class is loaded in the order given
     * and a single instance of each is created using
     * Class.forName(class).newInstance().  All errors are handled
     * via an AWTError exception.
     *
     * <p>The assumption is made that assistive technology classes are supplied
     * as part of INSTALLED (as opposed to: BUNDLED) extensions or specified
     * on the class path
     * (and therefore can be loaded using the class loader returned by
     * a call to <code>ClassLoader.getSystemClassLoader</code>, whose
     * delegation parent is the extension class loader for installed
     * extensions).
     */
    private static void loadAssistiveTechnologies() {
	// Load any assistive technologies
        if (atNames != null) {
	    ClassLoader cl = ClassLoader.getSystemClassLoader();
            StringTokenizer parser = new StringTokenizer(atNames," ,");
	    String atName;
            while (parser.hasMoreTokens()) {
		atName = parser.nextToken();
                try {
		    Class clazz;
		    if (cl != null) {
			clazz = cl.loadClass(atName);
		    } else {
			clazz = Class.forName(atName);
		    }
		    clazz.newInstance();
                } catch (ClassNotFoundException e) {
                    throw new AWTError("Assistive Technology not found: "
			    + atName);
                } catch (InstantiationException e) {
                    throw new AWTError("Could not instantiate Assistive"
			    + " Technology: " + atName);
                } catch (IllegalAccessException e) {
                    throw new AWTError("Could not access Assistive"
			    + " Technology: " + atName);
                } catch (Exception e) {
                    throw new AWTError("Error trying to install Assistive"
			    + " Technology: " + atName + " " + e);
                }
            }
        }
    }

    /**
     * Gets the default toolkit.
     * <p>
     * If a system property named <code>"java.awt.headless"</code> is set
     * to <code>true</code> then the headless implementation
     * of <code>Toolkit</code> is used.
     * <p>
     * If there is no <code>"java.awt.headless"</code> or it is set to
     * <code>false</code> and there is a system property named
     * <code>"awt.toolkit"</code>,
     * that property is treated as the name of a class that is a subclass
     * of <code>Toolkit</code>;
     * otherwise the default platform-specific implementation of
     * <code>Toolkit</code> is used.
     * <p>
     * Also loads additional classes into the VM, using the property
     * 'assistive_technologies' specified in the Sun reference
     * implementation by a line in the 'accessibility.properties'
     * file.  The form is "assistive_technologies=..." where
     * the "..." is a comma-separated list of assistive technology
     * classes to load.  Each class is loaded in the order given
     * and a single instance of each is created using
     * Class.forName(class).newInstance().  This is done just after
     * the AWT toolkit is created.  All errors are handled via an
     * AWTError exception.
     * @return    the default toolkit.
     * @exception  AWTError  if a toolkit could not be found, or
     *                 if one could not be accessed or instantiated.
     */
    public static synchronized Toolkit getDefaultToolkit() {
        if (toolkit == null) {
            try {
                // We disable the JIT during toolkit initialization.  This
                // tends to touch lots of classes that aren't needed again
                // later and therefore JITing is counter-productiive.
                java.lang.Compiler.disable();
                
                java.security.AccessController.doPrivileged(
                        new java.security.PrivilegedAction() {
                    public Object run() {
                        String nm = null;
                        Class cls = null;
                        try {
                            nm = System.getProperty("awt.toolkit", "sun.awt.X11.XToolkit");
                            try {
                                cls = Class.forName(nm);
                            } catch (ClassNotFoundException e) {
                                ClassLoader cl = ClassLoader.getSystemClassLoader();
                                if (cl != null) {
                                    try {
                                        cls = cl.loadClass(nm);
                                    } catch (ClassNotFoundException ee) {
                                        throw new AWTError("Toolkit not found: " + nm);
                                    }
                                }
                            }
                            if (cls != null) {
                                toolkit = (Toolkit)cls.newInstance();
                                if (GraphicsEnvironment.isHeadless()) {
                                    toolkit = new HeadlessToolkit(toolkit);
                                }
                            }
                        } catch (InstantiationException e) {
                            throw new AWTError("Could not instantiate Toolkit: " + nm);
                        } catch (IllegalAccessException e) {
                            throw new AWTError("Could not access Toolkit: " + nm);
                        }
                        return null;
                    }
                });
                loadAssistiveTechnologies();
            } finally {
                // Make sure to always re-enable the JIT.
                java.lang.Compiler.enable();
            }
        }
        return toolkit;
    }

    /**
     * Returns an image which gets pixel data from the specified file,
     * whose format can be either GIF, JPEG or PNG.
     * The underlying toolkit attempts to resolve multiple requests
     * with the same filename to the same returned Image.
     * <p>
     * Since the mechanism required to facilitate this sharing of
     * <code>Image</code> objects may continue to hold onto images
     * that are no longer in use for an indefinite period of time,
     * developers are encouraged to implement their own caching of
     * images by using the {@link #createImage(java.lang.String) createImage}
     * variant wherever available.
     * If the image data contained in the specified file changes,
     * the <code>Image</code> object returned from this method may
     * still contain stale information which was loaded from the
     * file after a prior call.
     * Previously loaded image data can be manually discarded by
     * calling the {@link Image#flush flush} method on the
     * returned <code>Image</code>.
     * <p>
     * This method first checks if there is a security manager installed.
     * If so, the method calls the security manager's
     * <code>checkRead</code> method with the file specified to ensure
     * that the access to the image is allowed.
     * @param     filename   the name of a file containing pixel data
     *                         in a recognized file format.
     * @return    an image which gets its pixel data from
     *                         the specified file.
     * @throws SecurityException  if a security manager exists and its
     *                            checkRead method doesn't allow the operation.
     * @see #createImage(java.lang.String)
     */
    public abstract Image getImage(String filename);

    /**
     * Returns an image which gets pixel data from the specified URL.
     * The pixel data referenced by the specified URL must be in one
     * of the following formats: GIF, JPEG or PNG.
     * The underlying toolkit attempts to resolve multiple requests
     * with the same URL to the same returned Image.
     * <p>
     * Since the mechanism required to facilitate this sharing of
     * <code>Image</code> objects may continue to hold onto images
     * that are no longer in use for an indefinite period of time,
     * developers are encouraged to implement their own caching of
     * images by using the {@link #createImage(java.net.URL) createImage}
     * variant wherever available.
     * If the image data stored at the specified URL changes,
     * the <code>Image</code> object returned from this method may
     * still contain stale information which was fetched from the
     * URL after a prior call.
     * Previously loaded image data can be manually discarded by
     * calling the {@link Image#flush flush} method on the
     * returned <code>Image</code>.
     * <p>
     * This method first checks if there is a security manager installed.
     * If so, the method calls the security manager's
     * <code>checkPermission</code> method with the
     * url.openConnection().getPermission() permission to ensure
     * that the access to the image is allowed. For compatibility
     * with pre-1.2 security managers, if the access is denied with
     * <code>FilePermission</code> or <code>SocketPermission</code>,
     * the method throws the <code>SecurityException</code>
     * if the corresponding 1.1-style SecurityManager.checkXXX method
     * also denies permission.
     * @param     url   the URL to use in fetching the pixel data.
     * @return    an image which gets its pixel data from
     *                         the specified URL.
     * @throws SecurityException  if a security manager exists and its
     *                            checkPermission method doesn't allow
     *                            the operation.
     * @see #createImage(java.net.URL)
     */
    public abstract Image getImage(URL url);

    /**
     * Returns an image which gets pixel data from the specified file.
     * The returned Image is a new object which will not be shared
     * with any other caller of this method or its getImage variant.
     * <p>
     * This method first checks if there is a security manager installed.
     * If so, the method calls the security manager's
     * <code>checkRead</code> method with the specified file to ensure
     * that the image creation is allowed.
     * @param     filename   the name of a file containing pixel data
     *                         in a recognized file format.
     * @return    an image which gets its pixel data from
     *                         the specified file.
     * @throws SecurityException  if a security manager exists and its
     *                            checkRead method doesn't allow the operation.
     * @see #getImage(java.lang.String)
     */
    public abstract Image createImage(String filename);

    /**
     * Returns an image which gets pixel data from the specified URL.
     * The returned Image is a new object which will not be shared
     * with any other caller of this method or its getImage variant.
     * <p>
     * This method first checks if there is a security manager installed.
     * If so, the method calls the security manager's
     * <code>checkPermission</code> method with the
     * url.openConnection().getPermission() permission to ensure
     * that the image creation is allowed. For compatibility
     * with pre-1.2 security managers, if the access is denied with
     * <code>FilePermission</code> or <code>SocketPermission</code>,
     * the method throws <code>SecurityException</code>
     * if the corresponding 1.1-style SecurityManager.checkXXX method
     * also denies permission.
     * @param     url   the URL to use in fetching the pixel data.
     * @return    an image which gets its pixel data from
     *                         the specified URL.
     * @throws SecurityException  if a security manager exists and its
     *                            checkPermission method doesn't allow
     *                            the operation.
     * @see #getImage(java.net.URL)
     */
    public abstract Image createImage(URL url);

    /**
     * Prepares an image for rendering.
     * <p>
     * If the values of the width and height arguments are both
     * <code>-1</code>, this method prepares the image for rendering
     * on the default screen; otherwise, this method prepares an image
     * for rendering on the default screen at the specified width and height.
     * <p>
     * The image data is downloaded asynchronously in another thread,
     * and an appropriately scaled screen representation of the image is
     * generated.
     * <p>
     * This method is called by components <code>prepareImage</code>
     * methods.
     * <p>
     * Information on the flags returned by this method can be found
     * with the definition of the <code>ImageObserver</code> interface.

     * @param     image      the image for which to prepare a
     *                           screen representation.
     * @param     width      the width of the desired screen
     *                           representation, or <code>-1</code>.
     * @param     height     the height of the desired screen
     *                           representation, or <code>-1</code>.
     * @param     observer   the <code>ImageObserver</code>
     *                           object to be notified as the
     *                           image is being prepared.
     * @return    <code>true</code> if the image has already been
     *                 fully prepared; <code>false</code> otherwise.
     * @see       java.awt.Component#prepareImage(java.awt.Image,
     *                 java.awt.image.ImageObserver)
     * @see       java.awt.Component#prepareImage(java.awt.Image,
     *                 int, int, java.awt.image.ImageObserver)
     * @see       java.awt.image.ImageObserver
     */
    public abstract boolean prepareImage(Image image, int width, int height,
					 ImageObserver observer);

    /**
     * Indicates the construction status of a specified image that is
     * being prepared for display.
     * <p>
     * If the values of the width and height arguments are both
     * <code>-1</code>, this method returns the construction status of
     * a screen representation of the specified image in this toolkit.
     * Otherwise, this method returns the construction status of a
     * scaled representation of the image at the specified width
     * and height.
     * <p>
     * This method does not cause the image to begin loading.
     * An application must call <code>prepareImage</code> to force
     * the loading of an image.
     * <p>
     * This method is called by the component's <code>checkImage</code>
     * methods.
     * <p>
     * Information on the flags returned by this method can be found
     * with the definition of the <code>ImageObserver</code> interface.
     * @param     image   the image whose status is being checked.
     * @param     width   the width of the scaled version whose status is
     *                 being checked, or <code>-1</code>.
     * @param     height  the height of the scaled version whose status
     *                 is being checked, or <code>-1</code>.
     * @param     observer   the <code>ImageObserver</code> object to be
     *                 notified as the image is being prepared.
     * @return    the bitwise inclusive <strong>OR</strong> of the
     *                 <code>ImageObserver</code> flags for the
     *                 image data that is currently available.
     * @see       java.awt.Toolkit#prepareImage(java.awt.Image,
     *                 int, int, java.awt.image.ImageObserver)
     * @see       java.awt.Component#checkImage(java.awt.Image,
     *                 java.awt.image.ImageObserver)
     * @see       java.awt.Component#checkImage(java.awt.Image,
     *                 int, int, java.awt.image.ImageObserver)
     * @see       java.awt.image.ImageObserver
     */
    public abstract int checkImage(Image image, int width, int height,
				   ImageObserver observer);

    /**
     * Creates an image with the specified image producer.
     * @param     producer the image producer to be used.
     * @return    an image with the specified image producer.
     * @see       java.awt.Image
     * @see       java.awt.image.ImageProducer
     * @see       java.awt.Component#createImage(java.awt.image.ImageProducer)
     */
    public abstract Image createImage(ImageProducer producer);

    /**
     * Creates an image which decodes the image stored in the specified
     * byte array.
     * <p>
     * The data must be in some image format, such as GIF or JPEG,
     * that is supported by this toolkit.
     * @param     imagedata   an array of bytes, representing
     *                         image data in a supported image format.
     * @return    an image.
     * @since     JDK1.1
     */
    public Image createImage(byte[] imagedata) {
	return createImage(imagedata, 0, imagedata.length);
    }

    /**
     * Creates an image which decodes the image stored in the specified
     * byte array, and at the specified offset and length.
     * The data must be in some image format, such as GIF or JPEG,
     * that is supported by this toolkit.
     * @param     imagedata   an array of bytes, representing
     *                         image data in a supported image format.
     * @param     imageoffset  the offset of the beginning
     *                         of the data in the array.
     * @param     imagelength  the length of the data in the array.
     * @return    an image.
     * @since     JDK1.1
     */
    public abstract Image createImage(byte[] imagedata,
				      int imageoffset,
				      int imagelength);

    /**
     * Gets a <code>PrintJob</code> object which is the result of initiating
     * a print operation on the toolkit's platform.
     * <p>
     * Each actual implementation of this method should first check if there 
     * is a security manager installed. If there is, the method should call
     * the security manager's <code>checkPrintJobAccess</code> method to
     * ensure initiation of a print operation is allowed. If the default
     * implementation of <code>checkPrintJobAccess</code> is used (that is,
     * that method is not overriden), then this results in a call to the
     * security manager's <code>checkPermission</code> method with a <code>
     * RuntimePermission("queuePrintJob")</code> permission.
     *
     * @param	frame the parent of the print dialog. May not be null.
     * @param	jobtitle the title of the PrintJob. A null title is equivalent
     *		to "".
     * @param	props a Properties object containing zero or more properties.
     *		Properties are not standardized and are not consistent across
     *		implementations. Because of this, PrintJobs which require job
     *		and page control should use the version of this function which
     *		takes JobAttributes and PageAttributes objects. This object
     *		may be updated to reflect the user's job choices on exit. May
     *		be null.
     *
     * @return	a <code>PrintJob</code> object, or <code>null</code> if the
     *		user cancelled the print job.
     * @throws	NullPointerException if frame is null.  This exception is
     *          always thrown when GraphicsEnvironment.isHeadless() returns
     *          true.
     * @throws	SecurityException if this thread is not allowed to initiate a
     *		print job request
     * @see     java.awt.GraphicsEnvironment#isHeadless
     * @see	java.awt.PrintJob
     * @see	java.lang.RuntimePermission
     * @since	JDK1.1
     */
    public abstract PrintJob getPrintJob(Frame frame, String jobtitle,
					 Properties props);

    /**
     * Gets a <code>PrintJob</code> object which is the result of initiating
     * a print operation on the toolkit's platform.
     * <p>
     * Each actual implementation of this method should first check if there 
     * is a security manager installed. If there is, the method should call
     * the security manager's <code>checkPrintJobAccess</code> method to
     * ensure initiation of a print operation is allowed. If the default
     * implementation of <code>checkPrintJobAccess</code> is used (that is,
     * that method is not overriden), then this results in a call to the
     * security manager's <code>checkPermission</code> method with a <code>
     * RuntimePermission("queuePrintJob")</code> permission.
     *
     * @param	frame the parent of the print dialog. May be null if and only
     *		if jobAttributes is not null and jobAttributes.getDialog()
     *		returns	JobAttributes.DialogType.NONE or
     *		JobAttributes.DialogType.COMMON.
     * @param	jobtitle the title of the PrintJob. A null title is equivalent
     *		to "".
     * @param	jobAttributes a set of job attributes which will control the
     *		PrintJob. The attributes will be updated to reflect the user's
     *		choices as outlined in the JobAttributes documentation. May be
     *		null.
     * @param	pageAttributes a set of page attributes which will control the
     *		PrintJob. The attributes will be applied to every page in the
     *		job. The attributes will be updated to reflect the user's
     *		choices as outlined in the PageAttributes documentation. May be
     *		null.
     *
     * @return	a <code>PrintJob</code> object, or <code>null</code> if the
     *		user cancelled the print job.
     * @throws	NullPointerException if frame is null and either jobAttributes
     *		is null or jobAttributes.getDialog() returns
     *		JobAttributes.DialogType.NATIVE.
     * @throws  IllegalArgumentException if pageAttributes specifies differing
     *          cross feed and feed resolutions. Also if this thread has
     *          access to the file system and jobAttributes specifies
     *          print to file, and the specified destination file exists but
     *          is a directory rather than a regular file, does not exist but
     *          cannot be created, or cannot be opened for any other reason.
     *          However in the case of print to file, if a dialog is also
     *          requested to be displayed then the user will be given an
     *          opportunity to select a file and proceed with printing.
     *          The dialog will ensure that the selected output file
     *          is valid before returning from this method.
     *          <p>   
     *          This exception is always thrown when GraphicsEnvironment.isHeadless()
     *          returns true.
     * @throws	SecurityException if this thread is not allowed to initiate a
     *		print job request, or if jobAttributes specifies print to file,
     *		and this thread is not allowed to access the file system
     * @see	java.awt.PrintJob
     * @see     java.awt.GraphicsEnvironment#isHeadless
     * @see	java.lang.RuntimePermission
     * @see	java.awt.JobAttributes
     * @see	java.awt.PageAttributes
     * @since	1.3
     */
    public PrintJob getPrintJob(Frame frame, String jobtitle,
				JobAttributes jobAttributes,
				PageAttributes pageAttributes) {
        // Override to add printing support with new job/page control classes

        if (GraphicsEnvironment.isHeadless()) {
            throw new IllegalArgumentException();
        }

	if (this != Toolkit.getDefaultToolkit()) {
	    return Toolkit.getDefaultToolkit().getPrintJob(frame, jobtitle,
							   jobAttributes,
							   pageAttributes);
	} else {
	    return getPrintJob(frame, jobtitle, null);
	}
    }

    /**
     * Emits an audio beep.
     * @since     JDK1.1
     */
    public abstract void beep();

    /**
     * Gets the singleton instance of the system Clipboard which interfaces
     * with clipboard facilities provided by the native platform. This 
     * clipboard enables data transfer between Java programs and native
     * applications which use native clipboard facilities.
     * <p>
     * In addition to any and all formats specified in the flavormap.properties
     * file, or other file specified by the <code>AWT.DnD.flavorMapFileURL
     * </code> Toolkit property, text returned by the system Clipboard's <code>
     * getTransferData()</code> method is available in the following flavors:
     * <ul>
     * <li>DataFlavor.stringFlavor</li>
     * <li>DataFlavor.plainTextFlavor (<b>deprecated</b>)</li>
     * </ul>
     * As with <code>java.awt.datatransfer.StringSelection</code>, if the
     * requested flavor is <code>DataFlavor.plainTextFlavor</code>, or an
     * equivalent flavor, a Reader is returned. <b>Note:</b> The behavior of
     * the system Clipboard's <code>getTransferData()</code> method for <code>
     * DataFlavor.plainTextFlavor</code>, and equivalent DataFlavors, is
     * inconsistent with the definition of <code>DataFlavor.plainTextFlavor
     * </code>. Because of this, support for <code>
     * DataFlavor.plainTextFlavor</code>, and equivalent flavors, is
     * <b>deprecated</b>.
     * <p>
     * Each actual implementation of this method should first check if there
     * is a security manager installed. If there is, the method should call
     * the security manager's <code>checkSystemClipboardAccess</code> method
     * to ensure it's ok to to access the system clipboard. If the default
     * implementation of <code>checkSystemClipboardAccess</code> is used (that
     * is, that method is not overriden), then this results in a call to the
     * security manager's <code>checkPermission</code> method with an <code>
     * AWTPermission("accessClipboard")</code> permission.
     * 
     * @return    the system Clipboard
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.datatransfer.Clipboard
     * @see       java.awt.datatransfer.StringSelection
     * @see       java.awt.datatransfer.DataFlavor#stringFlavor
     * @see       java.awt.datatransfer.DataFlavor#plainTextFlavor
     * @see       java.io.Reader
     * @see       java.awt.AWTPermission
     * @since     JDK1.1
     */
    public abstract Clipboard getSystemClipboard()
        throws HeadlessException;

    /**
     * Gets the singleton instance of the system selection as a
     * <code>Clipboard</code> object. This allows an application to read and
     * modify the current, system-wide selection.
     * <p>
     * An application is responsible for updating the system selection whenever
     * the user selects text, using either the mouse or the keyboard.
     * Typically, this is implemented by installing a
     * <code>FocusListener</code> on all <code>Component</code>s which support
     * text selection, and, between <code>FOCUS_GAINED</code> and
     * <code>FOCUS_LOST</code> events delivered to that <code>Component</code>,
     * updating the system selection <code>Clipboard</code> when the selection
     * changes inside the <code>Component</code>. Properly updating the system
     * selection ensures that a Java application will interact correctly with
     * native applications and other Java applications running simultaneously
     * on the system. Note that <code>java.awt.TextComponent</code> and
     * <code>javax.swing.text.JTextComponent</code> already adhere to this
     * policy. When using these classes, and their subclasses, developers need
     * not write any additional code.
     * <p>
     * Some platforms do not support a system selection <code>Clipboard</code>.
     * On those platforms, this method will return <code>null</code>. In such a
     * case, an application is absolved from its responsibility to update the
     * system selection <code>Clipboard</code> as described above.
     * <p>
     * Each actual implementation of this method should first check if there
     * is a <code>SecurityManager</code> installed. If there is, the method
     * should call the <code>SecurityManager</code>'s
     * <code>checkSystemClipboardAccess</code> method to ensure that client
     * code has access the system selection. If the default implementation of
     * <code>checkSystemClipboardAccess</code> is used (that is, if the method
     * is not overridden), then this results in a call to the
     * <code>SecurityManager</code>'s <code>checkPermission</code> method with
     * an <code>AWTPermission("accessClipboard")</code> permission.
     * 
     * @return the system selection as a <code>Clipboard</code>, or
     *         <code>null</code> if the native platform does not support a
     *         system selection <code>Clipboard</code>
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     *            returns true
     *
     * @see java.awt.datatransfer.Clipboard
     * @see java.awt.event.FocusListener
     * @see java.awt.event.FocusEvent#FOCUS_GAINED
     * @see java.awt.event.FocusEvent#FOCUS_LOST
     * @see TextComponent
     * @see javax.swing.text.JTextComponent
     * @see AWTPermission
     * @see GraphicsEnvironment#isHeadless
     * @since 1.4
     */
    public Clipboard getSystemSelection() throws HeadlessException {
        if (this != Toolkit.getDefaultToolkit()) {
            return Toolkit.getDefaultToolkit().getSystemSelection();
        } else {
            GraphicsEnvironment.checkHeadless();
            return null;
        }
    }

    /**
     * Determines which modifier key is the appropriate accelerator
     * key for menu shortcuts.
     * <p>
     * Menu shortcuts, which are embodied in the
     * <code>MenuShortcut</code> class, are handled by the
     * <code>MenuBar</code> class.
     * <p>
     * By default, this method returns <code>Event.CTRL_MASK</code>.
     * Toolkit implementations should override this method if the
     * <b>Control</b> key isn't the correct key for accelerators.
     * @return    the modifier mask on the <code>Event</code> class
     *                 that is used for menu shortcuts on this toolkit.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.MenuBar
     * @see       java.awt.MenuShortcut
     * @since     JDK1.1
     */
    public int getMenuShortcutKeyMask() throws HeadlessException {
        return Event.CTRL_MASK;
    }

    /**
     * Returns whether the given locking key on the keyboard is currently in
     * its "on" state.
     * Valid key codes are
     * {@link java.awt.event.KeyEvent#VK_CAPS_LOCK VK_CAPS_LOCK},
     * {@link java.awt.event.KeyEvent#VK_NUM_LOCK VK_NUM_LOCK},
     * {@link java.awt.event.KeyEvent#VK_SCROLL_LOCK VK_SCROLL_LOCK}, and
     * {@link java.awt.event.KeyEvent#VK_KANA_LOCK VK_KANA_LOCK}.
     *
     * @exception java.lang.IllegalArgumentException if <code>keyCode</code>
     * is not one of the valid key codes
     * @exception java.lang.UnsupportedOperationException if the host system doesn't
     * allow getting the state of this key programmatically, or if the keyboard
     * doesn't have this key
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since 1.3
     */
    public boolean getLockingKeyState(int keyCode)
        throws UnsupportedOperationException {
        if (! (keyCode == KeyEvent.VK_CAPS_LOCK || keyCode == KeyEvent.VK_NUM_LOCK ||
               keyCode == KeyEvent.VK_SCROLL_LOCK || keyCode == KeyEvent.VK_KANA_LOCK)) {
            throw new IllegalArgumentException("invalid key for Toolkit.getLockingKeyState");
        }
        throw new UnsupportedOperationException("Toolkit.getLockingKeyState");
    }

    /**
     * Sets the state of the given locking key on the keyboard.
     * Valid key codes are
     * {@link java.awt.event.KeyEvent#VK_CAPS_LOCK VK_CAPS_LOCK},
     * {@link java.awt.event.KeyEvent#VK_NUM_LOCK VK_NUM_LOCK},
     * {@link java.awt.event.KeyEvent#VK_SCROLL_LOCK VK_SCROLL_LOCK}, and
     * {@link java.awt.event.KeyEvent#VK_KANA_LOCK VK_KANA_LOCK}.
     * <p>
     * Depending on the platform, setting the state of a locking key may
     * involve event processing and therefore may not be immediately
     * observable through getLockingKeyState.
     *
     * @exception java.lang.IllegalArgumentException if <code>keyCode</code>
     * is not one of the valid key codes
     * @exception java.lang.UnsupportedOperationException if the host system doesn't
     * allow setting the state of this key programmatically, or if the keyboard
     * doesn't have this key
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since 1.3
     */
    public void setLockingKeyState(int keyCode, boolean on)
        throws UnsupportedOperationException {
        if (! (keyCode == KeyEvent.VK_CAPS_LOCK || keyCode == KeyEvent.VK_NUM_LOCK ||
               keyCode == KeyEvent.VK_SCROLL_LOCK || keyCode == KeyEvent.VK_KANA_LOCK)) {
            throw new IllegalArgumentException("invalid key for Toolkit.setLockingKeyState");
        }
        throw new UnsupportedOperationException("Toolkit.setLockingKeyState");
    }

    /**
     * Give native peers the ability to query the native container
     * given a native component (eg the direct parent may be lightweight).
     */
    protected static Container getNativeContainer(Component c) {
	return c.getNativeContainer();
    }

    /**
     * Creates a new custom cursor object.
     * If the image to display is invalid, the cursor will be hidden (made
     * completely transparent), and the hotspot will be set to (0, 0). 
     *
     * <p>Note that multi-frame images are invalid and may cause this
     * method to hang.
     *
     * @param cursor the image to display when the cursor is actived
     * @param hotSpot the X and Y of the large cursor's hot spot; the
     *   hotSpot values must be less than the Dimension returned by
     *   <code>getBestCursorSize</code>
     * @param     name a localized description of the cursor, for Java Accessibility use
     * @exception IndexOutOfBoundsException if the hotSpot values are outside
     *   the bounds of the cursor
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since     1.2
     */
    public Cursor createCustomCursor(Image cursor, Point hotSpot, String name)
        throws IndexOutOfBoundsException, HeadlessException
    {
        // Override to implement custom cursor support.
        if (this != Toolkit.getDefaultToolkit()) {
	    return Toolkit.getDefaultToolkit().
	        createCustomCursor(cursor, hotSpot, name);
	} else {
	    return new Cursor(Cursor.DEFAULT_CURSOR);
	}
    }

    /**
     * Returns the supported cursor dimension which is closest to the desired
     * sizes.  Systems which only support a single cursor size will return that
     * size regardless of the desired sizes.  Systems which don't support custom
     * cursors will return a dimension of 0, 0. <p>
     * Note:  if an image is used whose dimensions don't match a supported size
     * (as returned by this method), the Toolkit implementation will attempt to
     * resize the image to a supported size.
     * Since converting low-resolution images is difficult,
     * no guarantees are made as to the quality of a cursor image which isn't a
     * supported size.  It is therefore recommended that this method
     * be called and an appropriate image used so no image conversion is made.
     *
     * @param     preferredWidth the preferred cursor width the component would like
     * to use.
     * @param     preferredHeight the preferred cursor height the component would like
     * to use.
     * @return    the closest matching supported cursor size, or a dimension of 0,0 if
     * the Toolkit implementation doesn't support custom cursors.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since     1.2
     */
    public Dimension getBestCursorSize(int preferredWidth,
        int preferredHeight) throws HeadlessException {
        // Override to implement custom cursor support.
        if (this != Toolkit.getDefaultToolkit()) {
	    return Toolkit.getDefaultToolkit().
	        getBestCursorSize(preferredWidth, preferredHeight);
	} else {
	    return new Dimension(0, 0);
	}
    }

    /**
     * Returns the maximum number of colors the Toolkit supports in a custom cursor
     * palette.<p>
     * Note: if an image is used which has more colors in its palette than
     * the supported maximum, the Toolkit implementation will attempt to flatten the
     * palette to the maximum.  Since converting low-resolution images is difficult,
     * no guarantees are made as to the quality of a cursor image which has more
     * colors than the system supports.  It is therefore recommended that this method
     * be called and an appropriate image used so no image conversion is made.
     *
     * @return    the maximum number of colors, or zero if custom cursors are not
     * supported by this Toolkit implementation.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since     1.2
     */
    public int getMaximumCursorColors() throws HeadlessException {
        // Override to implement custom cursor support.
        if (this != Toolkit.getDefaultToolkit()) {
	    return Toolkit.getDefaultToolkit().getMaximumCursorColors();
	} else {
	    return 0;
	}
    }
    
    /**
     * Returns whether Toolkit supports this state for
     * <code>Frame</code>s.  This method tells whether the <em>UI
     * concept</em> of, say, maximization or iconification is
     * supported.  It will always return false for "compound" states
     * like <code>Frame.ICONIFIED|Frame.MAXIMIZED_VERT</code>.
     * In other words, the rule of thumb is that only queries with a
     * single frame state constant as an argument are meaningful.
     *
     * @param state one of named frame state constants.
     * @return <code>true</code> is this frame state is supported by
     *     this Toolkit implementation, <code>false</code> otherwise.
     * @exception HeadlessException
     *     if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * @see	java.awt.Frame#setExtendedState
     * @since	1.4
     */
    public boolean isFrameStateSupported(int state) 
	throws HeadlessException
    {
        if (this != Toolkit.getDefaultToolkit()) {
	    return Toolkit.getDefaultToolkit().
		isFrameStateSupported(state);
	} else {
	    return (state == Frame.NORMAL); // others are not guaranteed
	}
    }

    /**
     * Support for I18N: any visible strings should be stored in
     * sun.awt.resources.awt.properties.  The ResourceBundle is stored
     * here, so that only one copy is maintained.
     */
    private static ResourceBundle resources;

    /**
     * Initialize JNI field and method ids
     */
    private static native void initIDs();

    /**
     * WARNING: This is a temporary workaround for a problem in the
     * way the AWT loads native libraries. A number of classes in the
     * AWT package have a native method, initIDs(), which initializes
     * the JNI field and method ids used in the native portion of
     * their implementation.
     *
     * Since the use and storage of these ids is done by the
     * implementation libraries, the implementation of these method is
     * provided by the particular AWT implementations (for example, 
     * "Toolkit"s/Peer), such as Motif, Microsoft Windows, or Tiny. The
     * problem is that this means that the native libraries must be
     * loaded by the java.* classes, which do not necessarily know the
     * names of the libraries to load. A better way of doing this
     * would be to provide a separate library which defines java.awt.*
     * initIDs, and exports the relevant symbols out to the
     * implementation libraries.
     *
     * For now, we know it's done by the implementation, and we assume
     * that the name of the library is "awt".  -br.
     *
     * If you change loadLibraries(), please add the change to
     * java.awt.image.ColorModel.loadLibraries(). Unfortunately,
     * classes can be loaded in java.awt.image that depend on
     * libawt and there is no way to call Toolkit.loadLibraries()
     * directly.  -hung
     */
    private static boolean loaded = false;
    static void loadLibraries() {
	if (!loaded) {
	    java.security.AccessController.doPrivileged(
			  new sun.security.action.LoadLibraryAction("awt"));
	    loaded = true;
        }
    }

    static {
	java.security.AccessController.doPrivileged(
				 new java.security.PrivilegedAction() {
	    public Object run() {
		try {
		    resources =
			ResourceBundle.getBundle("sun.awt.resources.awt", 
						 CoreResourceBundleControl.getRBControlInstance());
		} catch (MissingResourceException e) {
		    // No resource file; defaults will be used.
		}
		return null;
	    }
	});

	// ensure that the proper libraries are loaded
        loadLibraries();
	initAssistiveTechnologies();
        if (!GraphicsEnvironment.isHeadless()) {
            initIDs();
        }
    }

    /**
     * Gets a property with the specified key and default.
     * This method returns defaultValue if the property is not found.
     */
    public static String getProperty(String key, String defaultValue) {
        if (resources != null) {
	    try {
	        return resources.getString(key);
	    }
	    catch (MissingResourceException e) {}
        }

	return defaultValue;
    }

    /**
     * Get the application's or applet's EventQueue instance.
     * Depending on the Toolkit implementation, different EventQueues
     * may be returned for different applets.  Applets should
     * therefore not assume that the EventQueue instance returned
     * by this method will be shared by other applets or the system.
     * 
     * <p>First, if there is a security manager, its 
     * <code>checkAwtEventQueueAccess</code> 
     * method is called. 
     * If  the default implementation of <code>checkAwtEventQueueAccess</code> 
     * is used (that is, that method is not overriden), then this results in
     * a call to the security manager's <code>checkPermission</code> method
     * with an <code>AWTPermission("accessEventQueue")</code> permission.
     * 
     * @return    the <code>EventQueue</code> object
     * @throws  SecurityException
     *          if a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkAwtEventQueueAccess}</code>
     *          method denies access to the <code>EventQueue</code>
     * @see     java.awt.AWTPermission
    */
    public final EventQueue getSystemEventQueue() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
	  security.checkAwtEventQueueAccess();
        }
        return getSystemEventQueueImpl();
    }

    /**
     * Gets the application's or applet's <code>EventQueue</code>
     * instance, without checking access.  For security reasons,
     * this can only be called from a <code>Toolkit</code> subclass. 
     * @return the <code>EventQueue</code> object
     */
    protected abstract EventQueue getSystemEventQueueImpl();

    /* Accessor method for use by AWT package routines. */
    static EventQueue getEventQueue() {
        return getDefaultToolkit().getSystemEventQueueImpl();
    }

    /**
     * Creates the peer for a DragSourceContext.
     * Always throws InvalidDndOperationException if
     * GraphicsEnvironment.isHeadless() returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     */
    public abstract DragSourceContextPeer createDragSourceContextPeer(DragGestureEvent dge) throws InvalidDnDOperationException;

    /**
     * Creates a concrete, platform dependent, subclass of the abstract
     * DragGestureRecognizer class requested, and associates it with the
     * DragSource, Component and DragGestureListener specified. 
     *
     * subclasses should override this to provide their own implementation
     *
     * @param abstractRecognizerClass The abstract class of the required recognizer
     * @param ds		      The DragSource
     * @param c			      The Component target for the DragGestureRecognizer
     * @param srcActions	      The actions permitted for the gesture
     * @param dgl		      The DragGestureListener
     *
     * @return the new object or null.  Always returns null if
     * GraphicsEnvironment.isHeadless() returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     */
    public <T extends DragGestureRecognizer> T
	createDragGestureRecognizer(Class<T> abstractRecognizerClass,
				    DragSource ds, Component c, int srcActions,
				    DragGestureListener dgl)
    {
	return null;
    }

    /**
     * Obtains a value for the specified desktop property.
     *
     * A desktop property is a uniquely named value for a resource that
     * is Toolkit global in nature. Usually it also is an abstract 
     * representation for an underlying platform dependent desktop setting.
     * For more information on desktop properties supported by the AWT see
     * <a href="doc-files/DesktopProperties.html">AWT Desktop Properties</a>.
     */
    public final synchronized Object getDesktopProperty(String propertyName) {
        // This is a workaround for headless toolkits.  It would be
        // better to override this method but it is declared final.
        // "this instanceof" syntax defeats polymorphism.
        // --mm, 03/03/00
        if (this instanceof HeadlessToolkit) {
            return ((HeadlessToolkit)this).getUnderlyingToolkit()
                .getDesktopProperty(propertyName);
        }

	if (desktopProperties.isEmpty()) {
	    initializeDesktopProperties();
	}

        Object value;

        // This property should never be cached
        if (propertyName.equals("awt.dynamicLayoutSupported")) {
            value = lazilyLoadDesktopProperty(propertyName);
            return value;
        }

	value = desktopProperties.get(propertyName);

	if (value == null) {
	    value = lazilyLoadDesktopProperty(propertyName);

	    if (value != null) {
		setDesktopProperty(propertyName, value);
	    }
	}

        /* for property "awt.font.desktophints" */
        if (value instanceof RenderingHints) {
            value = ((RenderingHints)value).clone();
        }

	return value;
    }

    /**
     * Sets the named desktop property to the specified value and fires a
     * property change event to notify any listeners that the value has changed. 
     */
    protected final void setDesktopProperty(String name, Object newValue) {
        // This is a workaround for headless toolkits.  It would be
        // better to override this method but it is declared final.
        // "this instanceof" syntax defeats polymorphism.
        // --mm, 03/03/00
        if (this instanceof HeadlessToolkit) {
            ((HeadlessToolkit)this).getUnderlyingToolkit()
                .setDesktopProperty(name, newValue);
            return;
        }
        Object oldValue;

        synchronized (this) {
            oldValue = desktopProperties.get(name);
            desktopProperties.put(name, newValue);
        }

        desktopPropsSupport.firePropertyChange(name, oldValue, newValue);
    }

    /**
     * an opportunity to lazily evaluate desktop property values.
     */
    protected Object lazilyLoadDesktopProperty(String name) {
	return null;
    }

    /**
     * initializeDesktopProperties
     */
    protected void initializeDesktopProperties() {
    }

    /**
     * Adds the specified property change listener for the named desktop 
     * property.  
     * If pcl is null, no exception is thrown and no action is performed.
     *
     * @param 	name The name of the property to listen for
     * @param	pcl The property change listener
     * @since	1.2
     */
    public synchronized void addPropertyChangeListener(String name, PropertyChangeListener pcl) {
	if (pcl == null) {
	    return;
	}
	desktopPropsSupport.addPropertyChangeListener(name, pcl);
    }

    /**
     * Removes the specified property change listener for the named 
     * desktop property. 
     * If pcl is null, no exception is thrown and no action is performed.
     *
     * @param 	name The name of the property to remove
     * @param	pcl The property change listener
     * @since	1.2
     */
    public synchronized void removePropertyChangeListener(String name, PropertyChangeListener pcl) {
	if (pcl == null) {
	    return;
	}
	desktopPropsSupport.removePropertyChangeListener(name, pcl);
    }

    /**
     * Returns an array of all the property change listeners
     * registered on this toolkit.
     *
     * @return all of this toolkit's <code>PropertyChangeListener</code>s
     *         or an empty array if no property change 
     *         listeners are currently registered
     *
     * @since 1.4
     */
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return desktopPropsSupport.getPropertyChangeListeners();
    }

    /**
     * Returns an array of all the <code>PropertyChangeListener</code>s
     * associated with the named property.
     *
     * @param  propertyName the named property
     * @return all of the <code>PropertyChangeListener</code>s associated with
     *         the named property or an empty array if no such listeners have
     *         been added
     * @since 1.4
     */
    public synchronized PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
        return desktopPropsSupport.getPropertyChangeListeners(propertyName);
    }

    protected final Map<String,Object> desktopProperties
	= new HashMap<String,Object>();
    protected final PropertyChangeSupport desktopPropsSupport = new PropertyChangeSupport(this);

    /**
     * Returns whether the always-on-top mode is supported by this toolkit.
     * To detect whether the always-on-top mode is supported for a
     * particular Window, use {@link Window#isAlwaysOnTopSupported}.
     * @return <code>true</code>, if current toolkit supports the always-on-top mode,
     *     otherwise returns <code>false</code>
     * @see Window#isAlwaysOnTopSupported
     * @see Window#setAlwaysOnTop(boolean)
     * @since 1.6
     */
    public boolean isAlwaysOnTopSupported() {
        return true;
    }

    /**
     * Returns whether the given modality type is supported by this toolkit. If
     * a dialog with unsupported modality type is created, then
     * <code>Dialog.ModalityType.MODELESS</code> is used instead.
     *
     * @param modalityType modality type to be checked for support by this toolkit
     *
     * @return <code>true</code>, if current toolkit supports given modality
     *     type, <code>false</code> otherwise
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#getModalityType
     * @see java.awt.Dialog#setModalityType
     *
     * @since 1.6
     */
    public abstract boolean isModalityTypeSupported(Dialog.ModalityType modalityType);

    /**
     * Returns whether the given modal exclusion type is supported by this
     * toolkit. If an unsupported modal exclusion type property is set on a window,
     * then <code>Dialog.ModalExclusionType.NO_EXCLUDE</code> is used instead.
     *
     * @param modalExclusionType modal exclusion type to be checked for support by this toolkit
     *
     * @return <code>true</code>, if current toolkit supports given modal exclusion
     *     type, <code>false</code> otherwise
     *
     * @see java.awt.Dialog.ModalExclusionType
     * @see java.awt.Window#getModalExclusionType
     * @see java.awt.Window#setModalExclusionType
     *
     * @since 1.6
     */
    public abstract boolean isModalExclusionTypeSupported(Dialog.ModalExclusionType modalExclusionType);

    private static final DebugHelper dbg = DebugHelper.create(Toolkit.class);
    private static final int LONG_BITS = 64;
    private int[] calls = new int[LONG_BITS];
    private static volatile long enabledOnToolkitMask;
    private AWTEventListener eventListener = null;
    private WeakHashMap listener2SelectiveListener = new WeakHashMap();

    /*
     * Extracts a "pure" AWTEventListener from a AWTEventListenerProxy,
     * if the listener is proxied.
     */
    static private AWTEventListener deProxyAWTEventListener(AWTEventListener l) 
    {
        AWTEventListener localL = l;

        if (localL == null) {
            return null;
        }
        // if user passed in a AWTEventListenerProxy object, extract
        // the listener
        if (l instanceof AWTEventListenerProxy) {
            localL = (AWTEventListener)((AWTEventListenerProxy)l).getListener();
        }
        return localL;
    }

    /**
     * Adds an AWTEventListener to receive all AWTEvents dispatched
     * system-wide that conform to the given <code>eventMask</code>.
     * <p>
     * First, if there is a security manager, its <code>checkPermission</code> 
     * method is called with an 
     * <code>AWTPermission("listenToAllAWTEvents")</code> permission.
     * This may result in a SecurityException. 
     * <p>
     * <code>eventMask</code> is a bitmask of event types to receive.
     * It is constructed by bitwise OR-ing together the event masks
     * defined in <code>AWTEvent</code>.
     * <p>
     * Note:  event listener use is not recommended for normal
     * application use, but are intended solely to support special
     * purpose facilities including support for accessibility,
     * event record/playback, and diagnostic tracing.
     *
     * If listener is null, no exception is thrown and no action is performed.
     *
     * @param    listener   the event listener.
     * @param    eventMask  the bitmask of event types to receive
     * @throws SecurityException
     *        if a security manager exists and its 
     *        <code>checkPermission</code> method doesn't allow the operation.
     * @see      #removeAWTEventListener
     * @see      #getAWTEventListeners
     * @see      SecurityManager#checkPermission
     * @see      java.awt.AWTEvent
     * @see      java.awt.AWTPermission
     * @see      java.awt.event.AWTEventListener
     * @see      java.awt.event.AWTEventListenerProxy
     * @since    1.2
     */
    public void addAWTEventListener(AWTEventListener listener, long eventMask) {
        AWTEventListener localL = deProxyAWTEventListener(listener); 

        if (localL == null) {
            return;
        }
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
          security.checkPermission(SecurityConstants.ALL_AWT_EVENTS_PERMISSION);
        }
        synchronized (this) {
            SelectiveAWTEventListener selectiveListener =
            (SelectiveAWTEventListener)listener2SelectiveListener.get(localL);

            if (selectiveListener == null) {
                // Create a new selectiveListener.
                selectiveListener = new SelectiveAWTEventListener(localL,
                                                                 eventMask); 
                listener2SelectiveListener.put(localL, selectiveListener);
                eventListener = ToolkitEventMulticaster.add(eventListener,
                                                            selectiveListener);
            }
            // OR the eventMask into the selectiveListener's event mask.
            selectiveListener.orEventMasks(eventMask);
            
            enabledOnToolkitMask |= eventMask;
            
            long mask = eventMask;
            for (int i=0; i<LONG_BITS; i++) {
                // If no bits are set, break out of loop.
                if (mask == 0) {
                    break;
                }
                if ((mask & 1L) != 0) {  // Always test bit 0.
                    calls[i]++;
                }
                mask >>>= 1;  // Right shift, fill with zeros on left.
            }
        }
    }

    /**
     * Removes an AWTEventListener from receiving dispatched AWTEvents.
     * <p>
     * First, if there is a security manager, its <code>checkPermission</code> 
     * method is called with an 
     * <code>AWTPermission("listenToAllAWTEvents")</code> permission.
     * This may result in a SecurityException. 
     * <p>
     * Note:  event listener use is not recommended for normal
     * application use, but are intended solely to support special
     * purpose facilities including support for accessibility,
     * event record/playback, and diagnostic tracing.
     *
     * If listener is null, no exception is thrown and no action is performed.
     *
     * @param    listener   the event listener.
     * @throws SecurityException
     *        if a security manager exists and its 
     *        <code>checkPermission</code> method doesn't allow the operation.
     * @see      #addAWTEventListener
     * @see      #getAWTEventListeners
     * @see      SecurityManager#checkPermission
     * @see      java.awt.AWTEvent
     * @see      java.awt.AWTPermission
     * @see      java.awt.event.AWTEventListener
     * @see      java.awt.event.AWTEventListenerProxy
     * @since    1.2
     */
    public void removeAWTEventListener(AWTEventListener listener) {
        AWTEventListener localL = deProxyAWTEventListener(listener);

        if (listener == null) {
            return;
        }
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkPermission(SecurityConstants.ALL_AWT_EVENTS_PERMISSION);
        }

        synchronized (this) {
            SelectiveAWTEventListener selectiveListener =
            (SelectiveAWTEventListener)listener2SelectiveListener.get(localL);

            if (selectiveListener != null) {
                listener2SelectiveListener.remove(localL);
                int[] listenerCalls = selectiveListener.getCalls();
                for (int i=0; i<LONG_BITS; i++) {
                    calls[i] -= listenerCalls[i];
                    assert calls[i] >= 0: "Negative Listeners count";
                    
                    if (calls[i] == 0) {
                        enabledOnToolkitMask &= ~(1L<<i);
                    }
                }
            }
            eventListener = ToolkitEventMulticaster.remove(eventListener,
            (selectiveListener == null) ? localL : selectiveListener);
        }
    }

    static boolean enabledOnToolkit(long eventMask) {
        return (enabledOnToolkitMask & eventMask) != 0;
        }

    synchronized int countAWTEventListeners(long eventMask) {
        if (dbg.on) {
            dbg.assertion(eventMask != 0);
        }

        int ci = 0;
        for (; eventMask != 0; eventMask >>>= 1, ci++) {
        }
        ci--;
        return calls[ci];
    }
    /**
     * Returns an array of all the <code>AWTEventListener</code>s 
     * registered on this toolkit.
     * If there is a security manager, its {@code checkPermission} 
     * method is called with an 
     * {@code AWTPermission("listenToAllAWTEvents")} permission.
     * This may result in a SecurityException. 
     * Listeners can be returned 
     * within <code>AWTEventListenerProxy</code> objects, which also contain 
     * the event mask for the given listener.
     * Note that listener objects
     * added multiple times appear only once in the returned array.
     *
     * @return all of the <code>AWTEventListener</code>s or an empty
     *         array if no listeners are currently registered 
     * @throws SecurityException
     *        if a security manager exists and its 
     *        <code>checkPermission</code> method doesn't allow the operation.
     * @see      #addAWTEventListener
     * @see      #removeAWTEventListener
     * @see      SecurityManager#checkPermission
     * @see      java.awt.AWTEvent
     * @see      java.awt.AWTPermission
     * @see      java.awt.event.AWTEventListener
     * @see      java.awt.event.AWTEventListenerProxy
     * @since 1.4
     */
    public AWTEventListener[] getAWTEventListeners() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
	    security.checkPermission(SecurityConstants.ALL_AWT_EVENTS_PERMISSION);
        }
        synchronized (this) {
            EventListener[] la = ToolkitEventMulticaster.getListeners(eventListener,AWTEventListener.class); 

            AWTEventListener[] ret = new AWTEventListener[la.length];
            for (int i = 0; i < la.length; i++) { 
                SelectiveAWTEventListener sael = (SelectiveAWTEventListener)la[i]; 
                AWTEventListener tempL = sael.getListener(); 
                //assert tempL is not an AWTEventListenerProxy - we should 
                // have weeded them all out 
                // don't want to wrap a proxy inside a proxy 
                ret[i] = new AWTEventListenerProxy(sael.getEventMask(), tempL); 
            } 
            return ret; 
        }
    }

    /**
     * Returns an array of all the <code>AWTEventListener</code>s 
     * registered on this toolkit which listen to all of the event
     * types specified in the {@code eventMask} argument.
     * If there is a security manager, its {@code checkPermission} 
     * method is called with an 
     * {@code AWTPermission("listenToAllAWTEvents")} permission.
     * This may result in a SecurityException. 
     * Listeners can be returned
     * within <code>AWTEventListenerProxy</code> objects, which also contain
     * the event mask for the given listener.
     * Note that listener objects
     * added multiple times appear only once in the returned array.
     *
     * @param  eventMask the bitmask of event types to listen for
     * @return all of the <code>AWTEventListener</code>s registered
     *         on this toolkit for the specified
     *         event types, or an empty array if no such listeners
     *         are currently registered
     * @throws SecurityException
     *        if a security manager exists and its 
     *        <code>checkPermission</code> method doesn't allow the operation.
     * @see      #addAWTEventListener
     * @see      #removeAWTEventListener
     * @see      SecurityManager#checkPermission
     * @see      java.awt.AWTEvent
     * @see      java.awt.AWTPermission
     * @see      java.awt.event.AWTEventListener
     * @see      java.awt.event.AWTEventListenerProxy
     * @since 1.4
     */
    public AWTEventListener[] getAWTEventListeners(long eventMask) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
	    security.checkPermission(SecurityConstants.ALL_AWT_EVENTS_PERMISSION);
        }
        synchronized (this) {
            EventListener[] la = ToolkitEventMulticaster.getListeners(eventListener,AWTEventListener.class);

            java.util.List list = new ArrayList(la.length);

            for (int i = 0; i < la.length; i++) {
                SelectiveAWTEventListener sael = (SelectiveAWTEventListener)la[i];
                if ((sael.getEventMask() & eventMask) == eventMask) {
                    //AWTEventListener tempL = sael.getListener();
                    list.add(new AWTEventListenerProxy(sael.getEventMask(),
                                                       sael.getListener()));
                }
            }
            return (AWTEventListener[])list.toArray(new AWTEventListener[0]);
        }
    }

    /*
     * This method notifies any AWTEventListeners that an event
     * is about to be dispatched.
     *
     * @param theEvent the event which will be dispatched.
     */
    void notifyAWTEventListeners(AWTEvent theEvent) {
        // This is a workaround for headless toolkits.  It would be
        // better to override this method but it is declared package private.
        // "this instanceof" syntax defeats polymorphism.
        // --mm, 03/03/00
        if (this instanceof HeadlessToolkit) {
            ((HeadlessToolkit)this).getUnderlyingToolkit()
                .notifyAWTEventListeners(theEvent);
            return;
        }

	AWTEventListener eventListener = this.eventListener;
        if (eventListener != null) {
	    eventListener.eventDispatched(theEvent);
        }
    }

    static private class ToolkitEventMulticaster extends AWTEventMulticaster
        implements AWTEventListener {
        // Implementation cloned from AWTEventMulticaster.

        ToolkitEventMulticaster(AWTEventListener a, AWTEventListener b) {
            super(a, b);
        }

        static AWTEventListener add(AWTEventListener a, 
                                    AWTEventListener b) {
	    if (a == null)  return b;
	    if (b == null)  return a;
	    return new ToolkitEventMulticaster(a, b);
        }

        static AWTEventListener remove(AWTEventListener l, 
                                       AWTEventListener oldl) {
            return (AWTEventListener) removeInternal(l, oldl);
        }

	// #4178589: must overload remove(EventListener) to call our add()
	// instead of the static addInternal() so we allocate a
	// ToolkitEventMulticaster instead of an AWTEventMulticaster.
	// Note: this method is called by AWTEventListener.removeInternal(),
	// so its method signature must match AWTEventListener.remove().
	protected EventListener remove(EventListener oldl) {
	    if (oldl == a)  return b;
	    if (oldl == b)  return a;
	    AWTEventListener a2 = (AWTEventListener)removeInternal(a, oldl);
	    AWTEventListener b2 = (AWTEventListener)removeInternal(b, oldl);
	    if (a2 == a && b2 == b) {
		return this;	// it's not here
	    }
	    return add(a2, b2);
	}

        public void eventDispatched(AWTEvent event) {
            ((AWTEventListener)a).eventDispatched(event);
            ((AWTEventListener)b).eventDispatched(event);
        }
    }

    private class SelectiveAWTEventListener implements AWTEventListener {
	AWTEventListener listener;
	private long eventMask;
        // This array contains the number of times to call the eventlistener
        // for each event type.
        int[] calls = new int[Toolkit.LONG_BITS];

        public AWTEventListener getListener() {return listener;}
        public long getEventMask() {return eventMask;}
        public int[] getCalls() {return calls;}

        public void orEventMasks(long mask) {
            eventMask |= mask;
            // For each event bit set in mask, increment its call count.
            for (int i=0; i<Toolkit.LONG_BITS; i++) {
                // If no bits are set, break out of loop.
                if (mask == 0) {
                    break;
                }
                if ((mask & 1L) != 0) {  // Always test bit 0.
                    calls[i]++;
                }
                mask >>>= 1;  // Right shift, fill with zeros on left.
            }
        }

	SelectiveAWTEventListener(AWTEventListener l, long mask) {
	    listener = l;
	    eventMask = mask;
	}

        public void eventDispatched(AWTEvent event) {
            long eventBit = 0; // Used to save the bit of the event type.
	    if (((eventBit = eventMask & AWTEvent.COMPONENT_EVENT_MASK) != 0 &&
		 event.id >= ComponentEvent.COMPONENT_FIRST &&
		 event.id <= ComponentEvent.COMPONENT_LAST)
	     || ((eventBit = eventMask & AWTEvent.CONTAINER_EVENT_MASK) != 0 &&
		 event.id >= ContainerEvent.CONTAINER_FIRST &&
		 event.id <= ContainerEvent.CONTAINER_LAST)
	     || ((eventBit = eventMask & AWTEvent.FOCUS_EVENT_MASK) != 0 &&
		 event.id >= FocusEvent.FOCUS_FIRST &&
		 event.id <= FocusEvent.FOCUS_LAST)
	     || ((eventBit = eventMask & AWTEvent.KEY_EVENT_MASK) != 0 &&
		 event.id >= KeyEvent.KEY_FIRST &&
		 event.id <= KeyEvent.KEY_LAST)
	     || ((eventBit = eventMask & AWTEvent.MOUSE_WHEEL_EVENT_MASK) != 0 &&
	         event.id == MouseEvent.MOUSE_WHEEL)
             || ((eventBit = eventMask & AWTEvent.MOUSE_MOTION_EVENT_MASK) != 0 &&
	         (event.id == MouseEvent.MOUSE_MOVED ||
	          event.id == MouseEvent.MOUSE_DRAGGED))
	     || ((eventBit = eventMask & AWTEvent.MOUSE_EVENT_MASK) != 0 &&
	         event.id != MouseEvent.MOUSE_MOVED &&
	         event.id != MouseEvent.MOUSE_DRAGGED &&
	         event.id != MouseEvent.MOUSE_WHEEL &&
		 event.id >= MouseEvent.MOUSE_FIRST &&
		 event.id <= MouseEvent.MOUSE_LAST)
	     || ((eventBit = eventMask & AWTEvent.WINDOW_EVENT_MASK) != 0 &&
		 (event.id >= WindowEvent.WINDOW_FIRST &&
		 event.id <= WindowEvent.WINDOW_LAST))
	     || ((eventBit = eventMask & AWTEvent.ACTION_EVENT_MASK) != 0 &&
		 event.id >= ActionEvent.ACTION_FIRST &&
		 event.id <= ActionEvent.ACTION_LAST)
	     || ((eventBit = eventMask & AWTEvent.ADJUSTMENT_EVENT_MASK) != 0 &&
		 event.id >= AdjustmentEvent.ADJUSTMENT_FIRST &&
		 event.id <= AdjustmentEvent.ADJUSTMENT_LAST)
	     || ((eventBit = eventMask & AWTEvent.ITEM_EVENT_MASK) != 0 &&
		 event.id >= ItemEvent.ITEM_FIRST &&
		 event.id <= ItemEvent.ITEM_LAST)
	     || ((eventBit = eventMask & AWTEvent.TEXT_EVENT_MASK) != 0 &&
		 event.id >= TextEvent.TEXT_FIRST &&
		 event.id <= TextEvent.TEXT_LAST)
	     || ((eventBit = eventMask & AWTEvent.INPUT_METHOD_EVENT_MASK) != 0 &&
		 event.id >= InputMethodEvent.INPUT_METHOD_FIRST &&
		 event.id <= InputMethodEvent.INPUT_METHOD_LAST)
	     || ((eventBit = eventMask & AWTEvent.PAINT_EVENT_MASK) != 0 &&
		 event.id >= PaintEvent.PAINT_FIRST &&
		 event.id <= PaintEvent.PAINT_LAST)
	     || ((eventBit = eventMask & AWTEvent.INVOCATION_EVENT_MASK) != 0 &&
		 event.id >= InvocationEvent.INVOCATION_FIRST &&
		 event.id <= InvocationEvent.INVOCATION_LAST)
	     || ((eventBit = eventMask & AWTEvent.HIERARCHY_EVENT_MASK) != 0 &&
		 event.id == HierarchyEvent.HIERARCHY_CHANGED)
             || ((eventBit = eventMask & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) != 0 &&
                 (event.id == HierarchyEvent.ANCESTOR_MOVED ||
                  event.id == HierarchyEvent.ANCESTOR_RESIZED))
             || ((eventBit = eventMask & AWTEvent.WINDOW_STATE_EVENT_MASK) != 0 &&
                 event.id == WindowEvent.WINDOW_STATE_CHANGED)
             || ((eventBit = eventMask & AWTEvent.WINDOW_FOCUS_EVENT_MASK) != 0 &&
                 (event.id == WindowEvent.WINDOW_GAINED_FOCUS ||
                  event.id == WindowEvent.WINDOW_LOST_FOCUS))
                || ((eventBit = eventMask & sun.awt.SunToolkit.GRAB_EVENT_MASK) != 0 &&
                    (event instanceof sun.awt.UngrabEvent))) {  
                // Get the index of the call count for this event type.
                // Instead of using Math.log(...) we will calculate it with
                // bit shifts. That's what previous implementation looked like:
                //
                // int ci = (int) (Math.log(eventBit)/Math.log(2));
                int ci = 0;
                for (long eMask = eventBit; eMask != 0; eMask >>>= 1, ci++) {
                }
                ci--;
                // Call the listener as many times as it was added for this
                // event type.
                for (int i=0; i<calls[ci]; i++) {
		    listener.eventDispatched(event);
                }
	    }
        }
    }
    
    /**
     * Returns a map of visual attributes for the abstract level description
     * of the given input method highlight, or null if no mapping is found.
     * The style field of the input method highlight is ignored. The map
     * returned is unmodifiable.
     * @param highlight input method highlight
     * @return style attribute map, or <code>null</code>
     * @exception HeadlessException if
     *     <code>GraphicsEnvironment.isHeadless</code> returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since 1.3
     */
    public abstract Map<java.awt.font.TextAttribute,?>
	mapInputMethodHighlight(InputMethodHighlight highlight)
	throws HeadlessException;

}

