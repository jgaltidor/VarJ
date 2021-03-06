/*
 * @(#)WindowPeer.java	1.24 06/05/03
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.awt.peer;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * The peer interfaces are intended only for use in porting
 * the AWT. They are not intended for use by application
 * developers, and developers should not implement peers
 * nor invoke any of the peer methods directly on the peer
 * instances.
 */
public interface WindowPeer extends ContainerPeer {
    void toFront();
    void toBack();
    void setAlwaysOnTop(boolean alwaysOnTop);
    void updateFocusableWindowState();
    boolean requestWindowFocus();
    void setModalBlocked(Dialog blocker, boolean blocked);
    void updateMinimumSize();
    void updateIconImages();
}

