/*
 * @(#)Dialog.java	1.127 07/06/19
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package java.awt;

import java.awt.peer.DialogPeer;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import javax.accessibility.*;
import java.util.Vector;
import java.util.Iterator;
import sun.awt.AppContext;
import sun.awt.SunToolkit;
import sun.awt.PeerEvent;
import java.lang.ref.WeakReference;
import sun.security.util.SecurityConstants;
import java.util.concurrent.atomic.AtomicLong;
import java.security.AccessController;
import java.security.PrivilegedAction;

import sun.awt.util.IdentityArrayList;

/**
 * A Dialog is a top-level window with a title and a border
 * that is typically used to take some form of input from the user.
 *
 * The size of the dialog includes any area designated for the
 * border.  The dimensions of the border area can be obtained
 * using the <code>getInsets</code> method, however, since
 * these dimensions are platform-dependent, a valid insets
 * value cannot be obtained until the dialog is made displayable
 * by either calling <code>pack</code> or <code>show</code>.
 * Since the border area is included in the overall size of the
 * dialog, the border effectively obscures a portion of the dialog,
 * constraining the area available for rendering and/or displaying
 * subcomponents to the rectangle which has an upper-left corner
 * location of <code>(insets.left, insets.top)</code>, and has a size of
 * <code>width - (insets.left + insets.right)</code> by
 * <code>height - (insets.top + insets.bottom)</code>.
 * <p>
 * The default layout for a dialog is <code>BorderLayout</code>.
 * <p>
 * A dialog may have its native decorations (i.e. Frame & Titlebar) turned off
 * with <code>setUndecorated</code>.  This can only be done while the dialog
 * is not {@link Component#isDisplayable() displayable}.
 * <p>
 * A dialog may have another window as its owner when it's constructed.  When
 * the owner window of a visible dialog is minimized, the dialog will
 * automatically be hidden from the user. When the owner window is subsequently
 * restored, the dialog is made visible to the user again.
 * <p>
 * In a multi-screen environment, you can create a <code>Dialog</code>
 * on a different screen device than its owner.  See {@link java.awt.Frame} for
 * more information.
 * <p>
 * A dialog can be either modeless (the default) or modal.  A modal
 * dialog is one which blocks input to some other top-level windows
 * in the application, except for any windows created with the dialog
 * as their owner. See <a href="doc-files/Modality.html">AWT Modality</a>
 * specification for details.
 * <p>
 * Dialogs are capable of generating the following
 * <code>WindowEvents</code>:
 * <code>WindowOpened</code>, <code>WindowClosing</code>,
 * <code>WindowClosed</code>, <code>WindowActivated</code>,
 * <code>WindowDeactivated</code>, <code>WindowGainedFocus</code>,
 * <code>WindowLostFocus</code>.
 *
 * @see WindowEvent
 * @see Window#addWindowListener
 *
 * @version	1.127, 06/19/07
 * @author	Sami Shaio
 * @author	Arthur van Hoff
 * @since       JDK1.0
 */
public class Dialog extends Window {

    static {
        /* ensure that the necessary native libraries are loaded */
	Toolkit.loadLibraries();
        if (!GraphicsEnvironment.isHeadless()) {
            initIDs();
        }
    }

    /**
     * A dialog's resizable property. Will be true
     * if the Dialog is to be resizable, otherwise
     * it will be false.
     *
     * @serial
     * @see #setResizable(boolean)
     */
    boolean resizable = true;


    /**
     * This field indicates whether the dialog is undecorated.
     * This property can only be changed while the dialog is not displayable.
     * <code>undecorated</code> will be true if the dialog is
     * undecorated, otherwise it will be false.
     *
     * @serial
     * @see #setUndecorated(boolean)
     * @see #isUndecorated()
     * @see Component#isDisplayable()
     * @since 1.4
     */
    boolean undecorated = false;

    /**
     * Modal dialogs block all input to some top-level windows.
     * Whether a particular window is blocked depends on dialog's type
     * of modality; this is called the "scope of blocking". The
     * <code>ModalityType</code> enum specifies modal types and their
     * associated scopes.
     *
     * @see Dialog#getModalityType
     * @see Dialog#setModalityType
     * @see Toolkit#isModalityTypeSupported
     *
     * @since 1.6
     */
    public static enum ModalityType {
        /**
         * <code>MODELESS</code> dialog doesn't block any top-level windows.
         */
        MODELESS,
        /**
         * A <code>DOCUMENT_MODAL</code> dialog blocks input to all top-level windows
         * from the same document except those from its own child hierarchy.
         * A document is a top-level window without an owner. It may contain child
         * windows that, together with the top-level window are treated as a single
         * solid document. Since every top-level window must belong to some
         * document, its root can be found as the top-nearest window without an owner.
         */
        DOCUMENT_MODAL,
        /**
         * An <code>APPLICATION_MODAL</code> dialog blocks all top-level windows
         * from the same Java application except those from its own child hierarchy.
         * If there are several applets launched in a browser, they can be
         * treated either as separate applications or a single one. This behavior
         * is implementation-dependent.
         */
        APPLICATION_MODAL,
        /**
         * A <code>TOOLKIT_MODAL</code> dialog blocks all top-level windows run
         * from the same toolkit except those from its own child hierarchy. If there
         * are several applets launched in a browser, all of them run with the same
         * toolkit; thus, a toolkit-modal dialog displayed by an applet may affect
         * other applets and all windows of the browser instance which embeds the
         * Java runtime environment for this toolkit.
         * Special <code>AWTPermission</code> "toolkitModality" must be granted to use
         * toolkit-modal dialogs. If a <code>TOOLKIT_MODAL</code> dialog is being created
         * and this permission is not granted, a <code>SecurityException</code> will be
         * thrown, and no dialog will be created. If a modality type is being changed
         * to <code>TOOLKIT_MODAL</code> and this permission is not granted, a
         * <code>SecurityException</code> will be thrown, and the modality type will
         * be left unchanged.
         */
        TOOLKIT_MODAL
    };

    /**
     * Default modality type for modal dialogs. The default modality type is
     * <code>APPLICATION_MODAL</code>. Calling the oldstyle <code>setModal(true)</code>
     * is equal to <code>setModalityType(DEFAULT_MODALITY_TYPE)</code>.
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#setModal
     *
     * @since 1.6
     */
    public final static ModalityType DEFAULT_MODALITY_TYPE = ModalityType.APPLICATION_MODAL;

    /**
     * True if this dialog is modal, false is the dialog is modeless.
     * A modal dialog blocks user input to some application top-level
     * windows. This field is kept only for backwards compatibility. Use the
     * {@link Dialog.ModalityType ModalityType} enum instead.
     *
     * @serial
     *
     * @see #isModal
     * @see #setModal
     * @see #getModalityType
     * @see #setModalityType
     * @see ModalityType
     * @see ModalityType#MODELESS
     * @see #DEFAULT_MODALITY_TYPE
     */
    boolean modal;

    /**
     * Modality type of this dialog. If the dialog's modality type is not
     * {@link Dialog.ModalityType#MODELESS ModalityType.MODELESS}, it blocks all
     * user input to some application top-level windows.
     *
     * @serial
     *
     * @see ModalityType
     * @see #getModalityType
     * @see #setModalityType
     *
     * @since 1.6
     */
    ModalityType modalityType;

    /**
     * Any top-level window can be marked not to be blocked by modal
     * dialogs. This is called "modal exclusion". This enum specifies
     * the possible modal exclusion types.
     *
     * @see Window#getModalExclusionType
     * @see Window#setModalExclusionType
     * @see Toolkit#isModalExclusionTypeSupported
     *
     * @since 1.6
     */
    public static enum ModalExclusionType {
        /**
         * No modal exclusion.
         */
        NO_EXCLUDE,
        /**
         * <code>APPLICATION_EXCLUDE</code> indicates that a top-level window
         * won't be blocked by any application-modal dialogs. Also, it isn't
         * blocked by document-modal dialogs from outside of its child hierarchy.
         */
        APPLICATION_EXCLUDE,
        /**
         * <code>TOOLKIT_EXCLUDE</code> indicates that a top-level window
         * won't be blocked by  application-modal or toolkit-modal dialogs. Also,
         * it isn't blocked by document-modal dialogs from outside of its
         * child hierarchy.
         * The "toolkitModality" <code>AWTPermission</code> must be granted
         * for this exclusion. If an exclusion property is being changed to
         * <code>TOOLKIT_EXCLUDE</code> and this permission is not granted, a
         * <code>SecurityEcxeption</code> will be thrown, and the exclusion
         * property will be left unchanged.
         */
        TOOLKIT_EXCLUDE
    };

    /**
     * @since 1.6
     */
    private final static ModalExclusionType DEFAULT_MODAL_EXCLUSION_TYPE =
        ModalExclusionType.APPLICATION_EXCLUDE;

    /* operations with this list should be synchronized on tree lock*/
    transient static IdentityArrayList<Dialog> modalDialogs = new IdentityArrayList<Dialog>();

    transient IdentityArrayList<Window> blockedWindows = new IdentityArrayList<Window>();

    /**
     * Specifies the title of the Dialog.
     * This field can be null.
     *
     * @serial
     * @see #getTitle()
     * @see #setTitle(String)
     */
    String title;

    private transient boolean keepBlocking = false;
    private transient ModalEventFilter modalFilter;

    /*
     * Indicates that this dialog is being hidden. This flag is set to true at
     * the beginning of hide() and to false at the end of hide().
     *
     * @see #hide()
     * @see #hideAndDisposePreHandler()
     * @see #hideAndDisposeHandler()
     * @see #shouldBlock()
     */
    transient volatile boolean isInHide = false;

    /*
     * Indicates that this dialog is being disposed. This flag is set to true at
     * the beginning of doDispose() and to false at the end of doDispose().
     *
     * @see #hide()
     * @see #hideAndDisposePreHandler()
     * @see #hideAndDisposeHandler()
     * @see #doDispose()
     */
    transient volatile boolean isInDispose = false;

    private static final String base = "dialog";
    private static int nameCounter = 0;

    /*
     * JDK 1.1 serialVersionUID
     */
    private static final long serialVersionUID = 5920926903803293709L;

    /**
     * Constructs an initially invisible, modeless <code>Dialog</code> with
     * the specified owner <code>Frame</code> and an empty title.
     *
     * @param owner the owner of the dialog or <code>null</code> if
     *     this dialog has no owner
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>'s
     *    <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *    <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     */
     public Dialog(Frame owner) {
         this(owner, "", false);
     }

    /**
     * Constructs an initially invisible <code>Dialog</code> with the specified
     * owner <code>Frame</code> and modality and an empty title.
     *
     * @param owner the owner of the dialog or <code>null</code> if
     *     this dialog has no owner
     * @param modal specifes whether dialog blocks user input to other top-level
     *     windows when shown. If <code>false</code>, the dialog is <code>MODELESS</code>;
     *     if <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>'s
     *    <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *     <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     */
     public Dialog(Frame owner, boolean modal) {
         this(owner, "", modal);
     }

    /**
     * Constructs an initially invisible, modeless <code>Dialog</code> with
     * the specified owner <code>Frame</code> and title.
     *
     * @param owner the owner of the dialog or <code>null</code> if
     *     this dialog has no owner
     * @param title the title of the dialog or <code>null</code> if this dialog
     *     has no title
     * @exception IllegalArgumentException if the <code>owner</code>'s
     *     <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *     <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     */
     public Dialog(Frame owner, String title) {
         this(owner, title, false);
     }

    /**
     * Constructs an initially invisible <code>Dialog</code> with the
     * specified owner <code>Frame</code>, title and modality.
     *
     * @param owner the owner of the dialog or <code>null</code> if
     *     this dialog has no owner
     * @param title the title of the dialog or <code>null</code> if this dialog
     *     has no title
     * @param modal specifes whether dialog blocks user input to other top-level
     *     windows when shown. If <code>false</code>, the dialog is <code>MODELESS</code>;
     *     if <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>'s
     *    <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *    <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     */
     public Dialog(Frame owner, String title, boolean modal) {
         this(owner, title, modal ? DEFAULT_MODALITY_TYPE : ModalityType.MODELESS);
     }

    /**
     * Constructs an initially invisible <code>Dialog</code> with the specified owner
     * <code>Frame</code>, title, modality, and <code>GraphicsConfiguration</code>.
     * @param owner the owner of the dialog or <code>null</code> if this dialog
     *     has no owner
     * @param title the title of the dialog or <code>null</code> if this dialog
     *     has no title
     * @param modal specifes whether dialog blocks user input to other top-level
     *     windows when shown. If <code>false</code>, the dialog is <code>MODELESS</code>;
     *     if <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>
     * @param gc the <code>GraphicsConfiguration</code> of the target screen device;
     *     if <code>null</code>, the default system <code>GraphicsConfiguration</code>
     *     is assumed
     * @exception java.lang.IllegalArgumentException if <code>gc</code>
     *     is not from a screen device
     * @exception HeadlessException when
     *     <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @since 1.4
     */
     public Dialog(Frame owner, String title, boolean modal,
                   GraphicsConfiguration gc) {
         this(owner, title, modal ? DEFAULT_MODALITY_TYPE : ModalityType.MODELESS, gc);
     }

    /**
     * Constructs an initially invisible, modeless <code>Dialog</code> with
     * the specified owner <code>Dialog</code> and an empty title.
     *
     * @param owner the owner of the dialog or <code>null</code> if this
     *     dialog has no owner
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>'s
     *     <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *     <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @since 1.2
     */
     public Dialog(Dialog owner) {
         this(owner, "", false);
     }

    /**
     * Constructs an initially invisible, modeless <code>Dialog</code>
     * with the specified owner <code>Dialog</code> and title.
     *
     * @param owner the owner of the dialog or <code>null</code> if this
     *     has no owner
     * @param title the title of the dialog or <code>null</code> if this dialog
     *     has no title
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>'s
     *     <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *     <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @since 1.2
     */
     public Dialog(Dialog owner, String title) {
         this(owner, title, false);
     }

    /**
     * Constructs an initially invisible <code>Dialog</code> with the
     * specified owner <code>Dialog</code>, title, and modality.
     *
     * @param owner the owner of the dialog or <code>null</code> if this
     *     dialog has no owner
     * @param title the title of the dialog or <code>null</code> if this
     *     dialog has no title
     * @param modal specifes whether dialog blocks user input to other top-level
     *     windows when shown. If <code>false</code>, the dialog is <code>MODELESS</code>;
     *     if <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>
     * @exception IllegalArgumentException if the <code>owner</code>'s
     *    <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *    <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     *
     * @since 1.2
     */
     public Dialog(Dialog owner, String title, boolean modal) {
         this(owner, title, modal ? DEFAULT_MODALITY_TYPE : ModalityType.MODELESS);
     }

    /**
     * Constructs an initially invisible <code>Dialog</code> with the
     * specified owner <code>Dialog</code>, title, modality and
     * <code>GraphicsConfiguration</code>.
     *
     * @param owner the owner of the dialog or <code>null</code> if this
     *     dialog has no owner
     * @param title the title of the dialog or <code>null</code> if this
     *     dialog has no title
     * @param modal specifes whether dialog blocks user input to other top-level
     *     windows when shown. If <code>false</code>, the dialog is <code>MODELESS</code>;
     *     if <code>true</code>, the modality type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>
     * @param gc the <code>GraphicsConfiguration</code> of the target screen device;
     *     if <code>null</code>, the default system <code>GraphicsConfiguration</code>
     *     is assumed
     * @exception java.lang.IllegalArgumentException if <code>gc</code>
     *    is not from a screen device
     * @exception HeadlessException when
     *    <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog.ModalityType#MODELESS
     * @see java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     *
     * @since 1.4
     */
     public Dialog(Dialog owner, String title, boolean modal,
                   GraphicsConfiguration gc) {
         this(owner, title, modal ? DEFAULT_MODALITY_TYPE : ModalityType.MODELESS, gc);
     }

    /**
     * Constructs an initially invisible, modeless <code>Dialog</code> with the
     * specified owner <code>Window</code> and an empty title.
     *
     * @param owner the owner of the dialog. The owner must be an instance of
     *     {@link java.awt.Dialog Dialog}, {@link java.awt.Frame Frame}, any
     *     of their descendents or <code>null</code>
     *
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>
     *     is not an instance of {@link java.awt.Dialog Dialog} or {@link
     *     java.awt.Frame Frame}
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>'s
     *     <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *     <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.GraphicsEnvironment#isHeadless
     *
     * @since 1.6
     */
    public Dialog(Window owner) {
        this(owner, null, ModalityType.MODELESS);
    }

    /**
     * Constructs an initially invisible, modeless <code>Dialog</code> with
     * the specified owner <code>Window</code> and title.
     *
     * @param owner the owner of the dialog. The owner must be an instance of
     *    {@link java.awt.Dialog Dialog}, {@link java.awt.Frame Frame}, any
     *    of their descendents or <code>null</code>
     * @param title the title of the dialog or <code>null</code> if this dialog
     *    has no title
     *
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>
     *    is not an instance of {@link java.awt.Dialog Dialog} or {@link
     *    java.awt.Frame Frame}
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>'s
     *    <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *    <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     *
     * @see java.awt.GraphicsEnvironment#isHeadless
     *
     * @since 1.6
     */
    public Dialog(Window owner, String title) {
        this(owner, title, ModalityType.MODELESS);
    }

    /**
     * Constructs an initially invisible <code>Dialog</code> with the
     * specified owner <code>Window</code> and modality and an empty title.
     *
     * @param owner the owner of the dialog. The owner must be an instance of
     *    {@link java.awt.Dialog Dialog}, {@link java.awt.Frame Frame}, any
     *    of their descendents or <code>null</code>
     * @param modalityType specifies whether dialog blocks input to other
     *    windows when shown. <code>null</code> value and unsupported modality
     *    types are equivalent to <code>MODELESS</code>
     *
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>
     *    is not an instance of {@link java.awt.Dialog Dialog} or {@link
     *    java.awt.Frame Frame}
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>'s
     *    <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *    <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     * @exception SecurityException if the calling thread does not have permission
     *    to create modal dialogs with the given <code>modalityType</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see java.awt.Toolkit#isModalityTypeSupported
     *
     * @since 1.6
     */
    public Dialog(Window owner, ModalityType modalityType) {
        this(owner, null, modalityType);
    }

    /**
     * Constructs an initially invisible <code>Dialog</code> with the
     * specified owner <code>Window</code>, title and modality.
     *
     * @param owner the owner of the dialog. The owner must be an instance of
     *     {@link java.awt.Dialog Dialog}, {@link java.awt.Frame Frame}, any
     *     of their descendents or <code>null</code>
     * @param title the title of the dialog or <code>null</code> if this dialog
     *     has no title
     * @param modalityType specifies whether dialog blocks input to other
     *    windows when shown. <code>null</code> value and unsupported modality
     *    types are equivalent to <code>MODELESS</code>
     *
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>
     *     is not an instance of {@link java.awt.Dialog Dialog} or {@link
     *     java.awt.Frame Frame}
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>'s
     *     <code>GraphicsConfiguration</code> is not from a screen device
     * @exception HeadlessException when
     *     <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     * @exception SecurityException if the calling thread does not have permission
     *     to create modal dialogs with the given <code>modalityType</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see java.awt.Toolkit#isModalityTypeSupported
     *
     * @since 1.6
     */
    public Dialog(Window owner, String title, ModalityType modalityType) {
        super(owner);

        if ((owner != null) &&
            !(owner instanceof Frame) &&
            !(owner instanceof Dialog))
        {
            throw new IllegalArgumentException("Wrong parent window");
        }

        this.title = title;
        setModalityType(modalityType);
        SunToolkit.checkAndSetPolicy(this, false);
    }

    /**
     * Constructs an initially invisible <code>Dialog</code> with the
     * specified owner <code>Window</code>, title, modality and
     * <code>GraphicsConfiguration</code>.
     *
     * @param owner the owner of the dialog. The owner must be an instance of
     *     {@link java.awt.Dialog Dialog}, {@link java.awt.Frame Frame}, any
     *     of their descendents or <code>null</code>
     * @param title the title of the dialog or <code>null</code> if this dialog
     *     has no title
     * @param modalityType specifies whether dialog blocks input to other
     *    windows when shown. <code>null</code> value and unsupported modality
     *    types are equivalent to <code>MODELESS</code>
     * @param gc the <code>GraphicsConfiguration</code> of the target screen device;
     *     if <code>null</code>, the default system <code>GraphicsConfiguration</code>
     *     is assumed
     *
     * @exception java.lang.IllegalArgumentException if the <code>owner</code>
     *     is not an instance of {@link java.awt.Dialog Dialog} or {@link
     *     java.awt.Frame Frame}
     * @exception java.lang.IllegalArgumentException if <code>gc</code>
     *     is not from a screen device
     * @exception HeadlessException when
     *     <code>GraphicsEnvironment.isHeadless()</code> returns <code>true</code>
     * @exception SecurityException if the calling thread does not have permission
     *     to create modal dialogs with the given <code>modalityType</code>
     *
     * @see java.awt.Dialog.ModalityType
     * @see java.awt.Dialog#setModal
     * @see java.awt.Dialog#setModalityType
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see java.awt.Toolkit#isModalityTypeSupported
     *
     * @since 1.6
     */
    public Dialog(Window owner, String title, ModalityType modalityType,
                  GraphicsConfiguration gc) {
        super(owner, gc);

        if ((owner != null) &&
            !(owner instanceof Frame) &&
            !(owner instanceof Dialog))
        {
            throw new IllegalArgumentException("wrong owner window");
        }

        this.title = title;
        setModalityType(modalityType);
        SunToolkit.checkAndSetPolicy(this, false);
    }

    /**
     * Construct a name for this component.  Called by getName() when the
     * name is null.
     */
    String constructComponentName() {
        synchronized (getClass()) {
	    return base + nameCounter++;
	}
    }

    /**
     * Makes this Dialog displayable by connecting it to
     * a native screen resource.  Making a dialog displayable will
     * cause any of its children to be made displayable.
     * This method is called internally by the toolkit and should
     * not be called directly by programs.
     * @see Component#isDisplayable
     * @see #removeNotify
     */
    public void addNotify() {
	synchronized (getTreeLock()) {
	    if (parent != null && parent.getPeer() == null) {
                parent.addNotify();
	    }

	    if (peer == null) {
	        peer = getToolkit().createDialog(this);
	    }
	    super.addNotify();
	}
    }

    /**
     * Indicates whether the dialog is modal.
     * <p>
     * This method is obsolete and is kept for backwards compatiblity only.
     * Use {@link #getModalityType getModalityType()} instead.
     *
     * @return    <code>true</code> if this dialog window is modal;
     *            <code>false</code> otherwise
     *
     * @see       java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see       java.awt.Dialog.ModalityType#MODELESS
     * @see       java.awt.Dialog#setModal
     * @see       java.awt.Dialog#getModalityType
     * @see       java.awt.Dialog#setModalityType
     */
    public boolean isModal() {
        return isModal_NoClientCode();
    }
    final boolean isModal_NoClientCode() {
        return modalityType != ModalityType.MODELESS;
    }

    /**
     * Specifies whether this dialog should be modal.
     * <p>
     * This method is obsolete and is kept for backwards compatibility only.
     * Use {@link #setModalityType setModalityType()} instead.
     * <p>
     * Note: changing modality of the visible dialog may have no effect
     * until it is hidden and then shown again.
     *
     * @param modal specifies whether dialog blocks input to other windows
     *     when shown; calling to <code>setModal(true)</code> is equivalent to
     *     <code>setModalityType(Dialog.DEFAULT_MODALITY_TYPE)</code>, and
     *     calling to <code>setModal(false)</code> is equvivalent to
     *     <code>setModalityType(Dialog.ModalityType.MODELESS)</code>
     *
     * @see       java.awt.Dialog#DEFAULT_MODALITY_TYPE
     * @see       java.awt.Dialog.ModalityType#MODELESS
     * @see       java.awt.Dialog#isModal
     * @see       java.awt.Dialog#getModalityType
     * @see       java.awt.Dialog#setModalityType
     *
     * @since     1.1
     */
    public void setModal(boolean modal) {
        this.modal = modal;
        setModalityType(modal ? DEFAULT_MODALITY_TYPE : ModalityType.MODELESS);
    }

    /**
     * Returns the modality type of this dialog.
     *
     * @return modality type of this dialog
     *
     * @see java.awt.Dialog#setModalityType
     *
     * @since 1.6
     */
    public ModalityType getModalityType() {
        return modalityType;
    }

    /**
     * Sets the modality type for this dialog. See {@link
     * java.awt.Dialog.ModalityType ModalityType} for possible modality types.
     * <p>
     * If the given modality type is not supported, <code>MODELESS</code>
     * is used. You may want to call <code>getModalityType()</code> after calling
     * this method to ensure that the modality type has been set.
     * <p>
     * Note: changing modality of the visible dialog may have no effect
     * until it is hidden and then shown again.
     *
     * @param type specifies whether dialog blocks input to other
     *     windows when shown. <code>null</code> value and unsupported modality
     *     types are equivalent to <code>MODELESS</code>
     * @exception SecurityException if the calling thread does not have permission
     *     to create modal dialogs with the given <code>modalityType</code>
     *
     * @see       java.awt.Dialog#getModalityType
     * @see       java.awt.Toolkit#isModalityTypeSupported
     *
     * @since     1.6
     */
    public void setModalityType(ModalityType type) {
        if (type == null) {
            type = Dialog.ModalityType.MODELESS;
        }
        if (modalityType == type) {
            return;
        }
        if (type == ModalityType.TOOLKIT_MODAL) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkPermission(SecurityConstants.TOOLKIT_MODALITY_PERMISSION);
            }
        }
        modalityType = type;
        modal = (modalityType != ModalityType.MODELESS);
    }

    /**
     * Gets the title of the dialog. The title is displayed in the
     * dialog's border.
     * @return    the title of this dialog window. The title may be
     *            <code>null</code>.
     * @see       java.awt.Dialog#setTitle
     */
    public String getTitle() {
	return title;
    }

    /**
     * Sets the title of the Dialog.
     * @param title the title displayed in the dialog's border;
	 * a null value results in an empty title
     * @see #getTitle
     */
    public void setTitle(String title) {
        String oldTitle = this.title;

        synchronized(this) {
            this.title = title;
            DialogPeer peer = (DialogPeer)this.peer;
            if (peer != null) {
                peer.setTitle(title);
            }
        }
        firePropertyChange("title", oldTitle, title);
    }

    /**
     * @return true if we actually showed, false if we just called toFront()
     */
    private boolean conditionalShow(Component toFocus, AtomicLong time) {
        boolean retval;

        synchronized (getTreeLock()) {
            if (peer == null) {
                addNotify();
            }
            validate();
            if (visible) {
                toFront();
                retval = false;
            } else {
                visible = retval = true;

                // check if this dialog should be modal blocked BEFORE calling peer.show(),
                // otherwise, a pair of FOCUS_GAINED and FOCUS_LOST may be mistakenly
                // generated for the dialog
                if (!isModal()) {
                    checkShouldBeBlocked(this);
                } else {
                    modalShow();
                }

                if (toFocus != null && time != null && isFocusable() && 
                    isEnabled() && !isModalBlocked()) {
                    // keep the KeyEvents from being dispatched
                    // until the focus has been transfered
                    time.set(Toolkit.getEventQueue().getMostRecentEventTimeEx());
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().
                        enqueueKeyEvents(time.get(), toFocus);
                }

                peer.show(); // now guaranteed never to block
                if (isModalBlocked()) {
                    modalBlocker.toFront();
                }

                setLocationByPlatform(false);
                for (int i = 0; i < ownedWindowList.size(); i++) {
                    Window child = ownedWindowList.elementAt(i).get();
                    if ((child != null) && child.showWithParent) {
                        child.show();
                        child.showWithParent = false;
                    }       // endif
                }   // endfor
                Window.updateChildFocusableWindowState(this);

                createHierarchyEvents(HierarchyEvent.HIERARCHY_CHANGED,
                                      this, parent,
                                      HierarchyEvent.SHOWING_CHANGED,
                                      Toolkit.enabledOnToolkit(AWTEvent.HIERARCHY_EVENT_MASK));
                if (componentListener != null ||
                        (eventMask & AWTEvent.COMPONENT_EVENT_MASK) != 0 ||
                        Toolkit.enabledOnToolkit(AWTEvent.COMPONENT_EVENT_MASK)) {
                    ComponentEvent e =
                        new ComponentEvent(this, ComponentEvent.COMPONENT_SHOWN);
                    Toolkit.getEventQueue().postEvent(e);
                }
            }
        }

        if (retval && (state & OPENED) == 0) {
            postWindowEvent(WindowEvent.WINDOW_OPENED);
            state |= OPENED;
        }

        return retval;
    }

    /**
     * Shows or hides this {@code Dialog} depending on the value of parameter
     * {@code b}.
     * @param b if {@code true}, makes the {@code Dialog} visible, 
     * otherwise hides the {@code Dialog}.
     * If the dialog and/or its owner
     * are not yet displayable, both are made displayable.  The 
     * dialog will be validated prior to being made visible.  
     * If {@code false}, hides the {@code Dialog} and then causes {@code setVisible(true)}
     * to return if it is currently blocked.
     * <p>
     * <b>Notes for modal dialogs</b>.
     * <ul>
     * <li>{@code setVisible(true)}:  If the dialog is not already
     * visible, this call will not return until the dialog is
     * hidden by calling {@code setVisible(false)} or
     * {@code dispose}.
     * <li>{@code setVisible(false)}:  Hides the dialog and then
     * returns on {@code setVisible(true)} if it is currently blocked.
     * <li>It is OK to call this method from the event dispatching
     * thread because the toolkit ensures that other events are
     * not blocked while this method is blocked.
     * </ul>
     * @see java.awt.Window#setVisible
     * @see java.awt.Window#dispose
     * @see java.awt.Component#isDisplayable
     * @see java.awt.Component#validate
     * @see java.awt.Dialog#isModal
     */
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    /**
    * Stores the app context on which event dispatch thread the dialog
    * is being shown. Initialized in show(), used in hideAndDisposeHandler()
    */
    transient private AppContext showAppContext;

   /**
     * Makes the {@code Dialog} visible. If the dialog and/or its owner
     * are not yet displayable, both are made displayable.  The 
     * dialog will be validated prior to being made visible.
     * If the dialog is already visible, this will bring the dialog 
     * to the front.
     * <p>
     * If the dialog is modal and is not already visible, this call
     * will not return until the dialog is hidden by calling hide or
     * dispose. It is permissible to show modal dialogs from the event
     * dispatching thread because the toolkit will ensure that another 
     * event pump runs while the one which invoked this method is blocked.
     * @see Component#hide
     * @see Component#isDisplayable
     * @see Component#validate
     * @see #isModal
     * @see Window#setVisible(boolean)
     * @deprecated As of JDK version 1.5, replaced by
     * {@link #setVisible(boolean) setVisible(boolean)}.
     */
    @Deprecated
    public void show() {
        beforeFirstShow = false;
        if (!isModal()) {
            conditionalShow(null, null);
        } else {
            // Set this variable before calling conditionalShow(). That
            // way, if the Dialog is hidden right after being shown, we
            // won't mistakenly block this thread.
            keepBlocking = true;

            // Store the app context on which this dialog is being shown.
            // Event dispatch thread of this app context will be sleeping until
            // we wake it by any event from hideAndDisposeHandler().
            showAppContext = AppContext.getAppContext();

            AtomicLong time = new AtomicLong();
            Component predictedFocusOwner = null;
            try {
                predictedFocusOwner = getMostRecentFocusOwner();
                if (conditionalShow(predictedFocusOwner, time)) {
                    // We have two mechanisms for blocking: 1. If we're on the
                    // EventDispatchThread, start a new event pump. 2. If we're
                    // on any other thread, call wait() on the treelock.

                    modalFilter = ModalEventFilter.createFilterForDialog(this);

                    final Runnable pumpEventsForFilter = new Runnable() {
                        public void run() {
                            EventDispatchThread dispatchThread =
                                (EventDispatchThread)Thread.currentThread();
                            dispatchThread.pumpEventsForFilter(new Conditional() {
                                public boolean evaluate() {
                                    return keepBlocking && windowClosingException == null;
                                }
                            }, modalFilter);
                        }
                    };

                    // if this dialog is toolkit-modal, the filter should be added
                    // to all EDTs (for all AppContexts)
                    if (modalityType == ModalityType.TOOLKIT_MODAL) {
                        Iterator it = AppContext.getAppContexts().iterator();
                        while (it.hasNext()) {
                            AppContext appContext = (AppContext)it.next();
                            if (appContext == showAppContext) {
                                continue;
                            }
                            EventQueue eventQueue = (EventQueue)appContext.get(AppContext.EVENT_QUEUE_KEY);
                            // it may occur that EDT for appContext hasn't been started yet, so
                            // we post an empty invocation event to trigger EDT initialization
                            Runnable createEDT = new Runnable() {
                                public void run() {};
                            };
                            eventQueue.postEvent(new InvocationEvent(this, createEDT));
                            EventDispatchThread edt = eventQueue.getDispatchThread();
                            edt.addEventFilter(modalFilter);
                        }
                    }

                    modalityPushed();
                    try {
                        if (EventQueue.isDispatchThread()) {
                            /*
                             * dispose SequencedEvent we are dispatching on current
                             * AppContext, to prevent us from hang.
                             *
                             */
                            // BugId 4531693 (son@sparc.spb.su)
                            SequencedEvent currentSequencedEvent = KeyboardFocusManager.
                                getCurrentKeyboardFocusManager().getCurrentSequencedEvent();
                            if (currentSequencedEvent != null) {
                                currentSequencedEvent.dispose();
                            }

                            /*
                             * Event processing is done inside doPrivileged block so that
                             * it wouldn't matter even if user code is on the stack 
                             * Fix for BugId 6300270
                             */

                             AccessController.doPrivileged(new PrivilegedAction() {
                                     public Object run() {
                                        pumpEventsForFilter.run();
                                        return null;
                                     }
                             });
                        } else {
                            synchronized (getTreeLock()) {
                                Toolkit.getEventQueue().postEvent(new PeerEvent(this,
                                                                                pumpEventsForFilter,
                                                                                PeerEvent.PRIORITY_EVENT));
                                while (keepBlocking && windowClosingException == null) {
                                    try {
                                        getTreeLock().wait();
                                    } catch (InterruptedException e) {
                                        break;
                                    }
                                }
                            }
                        }
                    } finally {
                        modalityPopped();
                    }

                    // if this dialog is toolkit-modal, its filter must be removed
                    // from all EDTs (for all AppContexts)
                    if (modalityType == ModalityType.TOOLKIT_MODAL) {
                        Iterator it = AppContext.getAppContexts().iterator();
                        while (it.hasNext()) {
                            AppContext appContext = (AppContext)it.next();
                            if (appContext == showAppContext) {
                                continue;
                            }
                            EventQueue eventQueue = (EventQueue)appContext.get(AppContext.EVENT_QUEUE_KEY);
                            EventDispatchThread edt = eventQueue.getDispatchThread();
                            edt.removeEventFilter(modalFilter);
                        }
                    }

                    if (windowClosingException != null) {
                        windowClosingException.fillInStackTrace();
                        throw windowClosingException;
                    }
                }
            } finally {
                if (predictedFocusOwner != null) {
                    // Restore normal key event dispatching
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().
                        dequeueKeyEvents(time.get(), predictedFocusOwner);
                }
            }
        }
    }

    final void modalityPushed() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        if (tk instanceof SunToolkit) {
            SunToolkit stk = (SunToolkit)tk;
            stk.notifyModalityPushed(this);
        }
    }

    final void modalityPopped() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        if (tk instanceof SunToolkit) {
            SunToolkit stk = (SunToolkit)tk;
            stk.notifyModalityPopped(this);
        }
    }

    void interruptBlocking() {
        if (isModal()) {
            disposeImpl();
        } else if (windowClosingException != null) {
            windowClosingException.fillInStackTrace();
            windowClosingException.printStackTrace();
            windowClosingException = null;
        }
    }
    final static class WakingRunnable implements Runnable {
        public void run() {
        }
    }
    private void hideAndDisposePreHandler() {
        isInHide = true;
        if (keepBlocking) {
            synchronized (getTreeLock()) {
                if (modalDialogs.contains(this)) {
                    modalHide();
                    // dialog can be shown and the disposed before its
                    // modal filter is created
                    if (modalFilter != null) {
                        modalFilter.disable();
                    }
                }
            }
        }
    }
    private void hideAndDisposeHandler() {
        if (keepBlocking) {
            synchronized (getTreeLock()) {
                keepBlocking = false;

                if (showAppContext != null) {
                    // Wake up event dispatch thread on which the dialog was
                    // initially shown
                    SunToolkit.postEvent(showAppContext,
                                         new PeerEvent(this,
                                                       new WakingRunnable(),
                                                       PeerEvent.PRIORITY_EVENT));
                    showAppContext = null;
                }
                EventQueue.invokeLater(new WakingRunnable());
                getTreeLock().notifyAll();
            }
        }
        isInHide = false;
    }

    /**
     * Hides the Dialog and then causes {@code show} to return if it is currently
     * blocked.
     * @see Window#show
     * @see Window#dispose
     * @see Window#setVisible(boolean)
     * @deprecated As of JDK version 1.5, replaced by
     * {@link #setVisible(boolean) setVisible(boolean)}.
     */
    @Deprecated
    public void hide() {
        hideAndDisposePreHandler();
        super.hide();
        // fix for 5048370: if hide() is called from super.doDispose(), then
        // hideAndDisposeHandler() should not be called here as it will be called
        // at the end of doDispose()
        if (!isInDispose) {
            hideAndDisposeHandler();
        }
    }

    /**
     * Disposes the Dialog and then causes show() to return if it is currently
     * blocked.
     */
    void doDispose() {
        // fix for 5048370: set isInDispose flag to true to prevent calling
        // to hideAndDisposeHandler() from hide()
        isInDispose = true;
        super.doDispose();
        hideAndDisposeHandler();
        isInDispose = false;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If this dialog is modal and blocks some windows, then all of them are
     * also sent to the back to keep them below the blocking dialog.
     *
     * @see java.awt.Window#toBack
     */
    public void toBack() {
        super.toBack();
        if (visible) {
            synchronized (getTreeLock()) {
                for (Window w : blockedWindows) {
                    w.toBack_NoClientCode();
                }
            }
        }
    }

    /**
     * Indicates whether this dialog is resizable by the user.
     * By default, all dialogs are initially resizable. 
     * @return    <code>true</code> if the user can resize the dialog;
     *            <code>false</code> otherwise.
     * @see       java.awt.Dialog#setResizable
     */
    public boolean isResizable() {
	return resizable;
    }

    /**
     * Sets whether this dialog is resizable by the user.
     * @param     resizable <code>true</code> if the user can
     *                 resize this dialog; <code>false</code> otherwise.
     * @see       java.awt.Dialog#isResizable
     */
    public void setResizable(boolean resizable) {
        boolean testvalid = false;

        synchronized (this) {
            this.resizable = resizable;
            DialogPeer peer = (DialogPeer)this.peer;
            if (peer != null) {
                peer.setResizable(resizable);
                testvalid = true;
            }
        }

        // On some platforms, changing the resizable state affects
        // the insets of the Dialog. If we could, we'd call invalidate()
        // from the peer, but we need to guarantee that we're not holding
        // the Dialog lock when we call invalidate().
        if (testvalid && valid) {
            invalidate();
        }
    }


    /**
     * Disables or enables decorations for this dialog.
     * This method can only be called while the dialog is not displayable.
     * @param  undecorated <code>true</code> if no dialog decorations are
     *         to be enabled;
     *         <code>false</code> if dialog decorations are to be enabled.
     * @throws <code>IllegalComponentStateException</code> if the dialog
     *         is displayable.
     * @see    #isUndecorated
     * @see    Component#isDisplayable
     * @since 1.4
     */
    public void setUndecorated(boolean undecorated) {
        /* Make sure we don't run in the middle of peer creation.*/
        synchronized (getTreeLock()) {
            if (isDisplayable()) {
                throw new IllegalComponentStateException("The dialog is displayable.");
            }
            this.undecorated = undecorated;
        }
    }

    /**
     * Indicates whether this dialog is undecorated.
     * By default, all dialogs are initially decorated.
     * @return    <code>true</code> if dialog is undecorated;
     *                        <code>false</code> otherwise.
     * @see       java.awt.Dialog#setUndecorated
     * @since 1.4
     */
    public boolean isUndecorated() {
	return undecorated;
    }

    /**
     * Returns a string representing the state of this dialog. This
     * method is intended to be used only for debugging purposes, and the
     * content and format of the returned string may vary between
     * implementations. The returned string may be empty but may not be
     * <code>null</code>.
     *
     * @return    the parameter string of this dialog window.
     */
    protected String paramString() {
        String str = super.paramString() + "," + modalityType;
        if (title != null) {
            str += ",title=" + title;
        }
        return str;
    }

    /**
     * Initialize JNI field and method IDs
     */
    private static native void initIDs();

    /*
     * --- Modality support ---
     *
     */

    /*
     * This method is called only for modal dialogs.
     *
     * Goes through the list of all visible top-level windows and
     * divide them into three distinct groups: blockers of this dialog,
     * blocked by this dialog and all others. Then blocks this dialog
     * by first met dialog from the first group (if any) and blocks all
     * the windows from the second group.
     */
    void modalShow() {
        modalDialogs.add(this);

        // find all the dialogs that block this one
        IdentityArrayList<Dialog> blockers = new IdentityArrayList<Dialog>();
        for (Dialog d : modalDialogs) {
            if (d.shouldBlock(this)) {
                Window w = d;
                while ((w != null) && (w != this)) {
                    w = (Window)(w.getOwner_NoClientCode());
                }
                if ((w == this) || !shouldBlock(d) || (modalityType.compareTo(d.getModalityType()) < 0)) {
                    blockers.add(d);
                }
            }
        }

        // add all blockers' blockers to blockers :)
        for (int i = 0; i < blockers.size(); i++) {
            Dialog blocker = blockers.get(i);
            if (blocker.isModalBlocked()) {
                Dialog blockerBlocker = blocker.getModalBlocker();
                if (!blockers.contains(blockerBlocker)) {
                    blockers.add(i + 1, blockerBlocker);
                }
            }
        }

        if (blockers.size() > 0) {
            blockers.get(0).blockWindow(this);
        }

        // find all windows from blockers' hierarchies
        IdentityArrayList<Window> blockersHierarchies = new IdentityArrayList<Window>(blockers);
        int k = 0;
        while (k < blockersHierarchies.size()) {
            Window w = blockersHierarchies.get(k);
            Window[] ownedWindows = w.getOwnedWindows_NoClientCode();
            for (Window win : ownedWindows) {
                blockersHierarchies.add(win);
            }
            k++;
        }

        // block all windows from scope of blocking except from blockers' hierarchies
        IdentityArrayList<Window> unblockedWindows = Window.getAllUnblockedWindows();
        for (Window w : unblockedWindows) {
            if (shouldBlock(w) && !blockersHierarchies.contains(w)) {
                if ((w instanceof Dialog) && ((Dialog)w).isModal_NoClientCode()) {
                    Dialog wd = (Dialog)w;
                    if (wd.shouldBlock(this) && (modalDialogs.indexOf(wd) > modalDialogs.indexOf(this))) {
                        continue;
                    }
                }
                blockWindow(w);
            }
        }

        if (!isModalBlocked()) {
            updateChildrenBlocking();
        }
    }

    /*
     * This method is called only for modal dialogs.
     *
     * Unblocks all the windows blocked by this modal dialog. After
     * each of them has been unblocked, it is checked to be blocked by
     * any other modal dialogs.
     */
    void modalHide() {
        modalDialogs.remove(this);
        // we should unblock all the windows first...
        IdentityArrayList<Window> save = new IdentityArrayList<Window>();
        int blockedWindowsCount = blockedWindows.size();
        for (int i = 0; i < blockedWindowsCount; i++) {
            Window w = blockedWindows.get(0);
            save.add(w);
            unblockWindow(w); // also removes w from blockedWindows
        }
        // ... and only after that check if they should be blocked
        // by another dialogs
        for (int i = 0; i < blockedWindowsCount; i++) {
            Window w = save.get(i);
            if ((w instanceof Dialog) && ((Dialog)w).isModal_NoClientCode()) {
                Dialog d = (Dialog)w;
                d.modalShow();
            } else {
                checkShouldBeBlocked(w);
            }
        }
    }

    /*
     * Returns whether the given top-level window should be blocked by
     * this dialog. Note, that the given window can be also a modal dialog
     * and it should block this dialog, but this method do not take such
     * situations into consideration (such checks are performed in the
     * modalShow() and modalHide() methods).
     *
     * This method should be called on the getTreeLock() lock.
     */
    boolean shouldBlock(Window w) {
        if (!isVisible_NoClientCode() ||
            (!w.isVisible_NoClientCode() && !w.isInShow) ||
            isInHide ||
            (w == this) ||
            !isModal_NoClientCode())
        {
            return false;
        }
        if ((w instanceof Dialog) && ((Dialog)w).isInHide) {
            return false;
        }
        // check if w is from children hierarchy
        // fix for 6271546: we should also take into consideration child hierarchies
        // of this dialog's blockers
        Window blockerToCheck = this;
        while (blockerToCheck != null) {
            Component c = w;
            while ((c != null) && (c != blockerToCheck)) {
                c = c.getParent_NoClientCode();
            }
            if (c == blockerToCheck) {
                return false;
            }
            blockerToCheck = blockerToCheck.getModalBlocker();
        }
        switch (modalityType) {
            case MODELESS:
                return false;
            case DOCUMENT_MODAL:
                if (w.isModalExcluded(ModalExclusionType.APPLICATION_EXCLUDE)) {
                    // application- and toolkit-excluded windows are not blocked by
                    // document-modal dialogs from outside their children hierarchy
                    Component c = this;
                    while ((c != null) && (c != w)) {
                        c = c.getParent_NoClientCode();
                    }
                    return c == w;
                } else {
                    return getDocumentRoot() == w.getDocumentRoot();
                }
            case APPLICATION_MODAL:
                return !w.isModalExcluded(ModalExclusionType.APPLICATION_EXCLUDE) &&
                    (appContext == w.appContext);
            case TOOLKIT_MODAL:
                return !w.isModalExcluded(ModalExclusionType.TOOLKIT_EXCLUDE);
        }

        return false;
    }

    /*
     * Adds the given top-level window to the list of blocked
     * windows for this dialog and marks it as modal blocked.
     * If the window is already blocked by some modal dialog,
     * does nothing.
     */
    void blockWindow(Window w) {
        if (!w.isModalBlocked()) {
            w.setModalBlocked(this, true);
            blockedWindows.add(w);
        }
    }

    /*
     * Removes the given top-level window from the list of blocked
     * windows for this dialog and marks it as unblocked. If the
     * window is not modal blocked, does nothing.
     */
    void unblockWindow(Window w) {
        if (w.isModalBlocked() && blockedWindows.contains(w)) {
            blockedWindows.remove(w);
            w.setModalBlocked(this, false);
        }
    }

    /*
     * Checks if any other modal dialog D blocks the given window.
     * If such D exists, mark the window as blocked by D.
     */
    static void checkShouldBeBlocked(Window w) {
        synchronized (w.getTreeLock()) {
            for (int i = 0; i < modalDialogs.size(); i++) {
                Dialog modalDialog = modalDialogs.get(i);
                if (modalDialog.shouldBlock(w)) {
                    modalDialog.blockWindow(w);
                    break;
                }
            }
        }
    }

    private void readObject(ObjectInputStream s)
        throws ClassNotFoundException, IOException, HeadlessException
    {
        GraphicsEnvironment.checkHeadless();
        s.defaultReadObject();

        // in 1.5 or earlier modalityType was absent, so use "modal" instead
        if (modalityType == null) {
            setModal(modal);
        }

        blockedWindows = new IdentityArrayList();
    }

    /*
     * --- Accessibility Support ---
     *
     */

    /**
     * Gets the AccessibleContext associated with this Dialog.
     * For dialogs, the AccessibleContext takes the form of an
     * AccessibleAWTDialog.
     * A new AccessibleAWTDialog instance is created if necessary.
     *
     * @return an AccessibleAWTDialog that serves as the
     *         AccessibleContext of this Dialog
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleAWTDialog();
        }
        return accessibleContext;
    }

    /**
     * This class implements accessibility support for the
     * <code>Dialog</code> class.  It provides an implementation of the
     * Java Accessibility API appropriate to dialog user-interface elements.
     * @since 1.3
     */
    protected class AccessibleAWTDialog extends AccessibleAWTWindow
    {
        /*
         * JDK 1.3 serialVersionUID
         */
        private static final long serialVersionUID = 4837230331833941201L;

        /**
         * Get the role of this object.
         *
         * @return an instance of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.DIALOG;
        }

        /**
         * Get the state of this object.
         *
         * @return an instance of AccessibleStateSet containing the current
         * state set of the object
         * @see AccessibleState
         */
        public AccessibleStateSet getAccessibleStateSet() {
            AccessibleStateSet states = super.getAccessibleStateSet();
            if (getFocusOwner() != null) {
                states.add(AccessibleState.ACTIVE);
            }
            if (isModal()) {
                states.add(AccessibleState.MODAL);
            }
            if (isResizable()) {
                states.add(AccessibleState.RESIZABLE);
            }
            return states;
        }

    } // inner class AccessibleAWTDialog
}
