/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.diagram;

import java.util.Iterator;

import org.eclipse.stardust.engine.core.model.utils.ModelElement;


/**
 *
 */
public interface Diagram extends ModelElement, SymbolOwner
{
   /**
    * Returns true if the symbol is contained in the diagram otherwise
    * false is returned.
    */
   public boolean contains(NodeSymbol symbol);

   /**
    * Returns <code>true</code> if at least one connection exist between
    * the two symbols
    */
   public boolean existConnectionBetween(NodeSymbol symbol1, NodeSymbol symbol2);

   /**
    * Returns <code>true</code> if at least one connection exist between
    * the two symbols. The connection must be a instance of the class <code>connectionType</code>.
    * If the parameter <code>uniDirectional</code> is <code>true</code> only
    * connection from <code>symbol1</code> to <code>symbol2</code> will be found.
    */
   public boolean existConnectionBetween(Symbol symbol1, Symbol symbol2
         , Class connectionType
         , boolean uniDirectional);

   /**
    * Returns an Iterator of connections corresponding to the two symbols.
    * 
    * @see #existConnectionBetween
    */
   public Iterator getExistingConnectionsBetween(Symbol symbol1, Symbol symbol2
         , Class connectionType
         , boolean uniDirectional);

   /**
    * Returns the first symbol that use the <code>searchedObject</code> as its
    * userobject.
    */
   public Symbol findSymbolForUserObject(Object searchedObject);

   public String getName();

   Iterator getAllNodes(Class type);

   Iterator getAllConnections(Class type);

   Iterator getAllNodeSymbols();

   Iterator getAllConnections();

   String getId();

}
