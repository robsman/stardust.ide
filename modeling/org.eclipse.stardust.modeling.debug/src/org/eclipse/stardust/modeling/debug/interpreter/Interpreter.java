/*
 * $Id: Interpreter.java 7281 2005-01-20 19:09:01Z rsauer $
 * (C) 2000 - 2005 CARNOT AG
 */
package org.eclipse.stardust.modeling.debug.interpreter;

import javax.swing.JComponent;

/**
 *
 */
public abstract class Interpreter
{
   /**
    *
    */
   public abstract JComponent createGroupComponent(DataGroup dataGroup);
}
