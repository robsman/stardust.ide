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
package org.eclipse.stardust.modeling.integration.webservices;

import java.util.*;

import javax.wsdl.BindingOperation;
import javax.wsdl.Fault;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.namespace.QName;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotBooleanEditor;
import org.eclipse.stardust.modeling.core.editors.ui.DefaultTableLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;

import com.sun.tools.ws.WsImport;


/**
 * @author fherinean
 * @version $Revision$
 */
public class JaxWSPartsPropertyPage extends
   AbstractModelElementPropertyPage
{
   private static final String EMPTY_STRING = ""; //$NON-NLS-1$
   private static final String[][] LABELS = {
      {new String("_input_"), Diagram_Messages.ELEMENT_Input}, //$NON-NLS-1$
      {new String("_output_"), Diagram_Messages.ELEMENT_Output}, //$NON-NLS-1$ 
      {new String("_faults_"), Diagram_Messages.ELEMENT_Faults}}; //$NON-NLS-1$
   private static final int MAPPED_COLUMN = 2;

   private TreeViewer viewer;
   private CarnotBooleanEditor cellEditor;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      cellEditor.dispose();
      viewer.setInput(getSynchronizer().getOperation());
      viewer.expandAll();
      cellEditor.setTree(viewer.getTree());
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }
   
   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
      Tree tree = new Tree(composite, SWT.BORDER | SWT.FULL_SELECTION);
      tree.setHeaderVisible(true);
      tree.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(tree);

      String[] columnProperties = new String[] {
            Diagram_Messages.COL_NAME_Parts,
            Diagram_Messages.COL_NAME_XMLType,
            Diagram_Messages.COL_NAME_Mapped};
      
      viewer = new TreeViewer(tree);
      TableUtil.createColumns(tree, columnProperties);
      TableUtil.setInitialColumnSizes(tree, new int[] {30, 58, 12});

      TableLabelProvider labelProvider = new DefaultTableLabelProvider(null)
      {
         public String getText(int index, Object element)
         {
            if (element instanceof PartWrapper)
            {
               Part part = ((PartWrapper) element).part;
               switch (index)
               {
                  case 0: return part.getName();
                  case 1: return JaxWSResource.getType(part).toString();
               }
            }
            else if (element instanceof Fault)
            {
               Fault fault = (Fault) element;
               switch (index)
               {
                  case 0: return fault.getName();
               }
            }
            else for (int i = 0; i < LABELS.length; i++)
            {
               if (LABELS[i][0] == element)
               {
                  switch (index)
                  {
                     case 0: return LABELS[i][1];
                     default: return EMPTY_STRING;
                  }
               }
            }
            return EMPTY_STRING;
         }
      };
      TableUtil.setLabelProvider(viewer, labelProvider, columnProperties);
      viewer.setContentProvider(new OperationParameterTreeContentProvider());
      cellEditor = new CarnotBooleanEditor(MAPPED_COLUMN)
      {
         public boolean canEdit(Object element)
         {
            return element instanceof PartWrapper/*
               && !getSynchronizer().isDefault(((PartWrapper)element).part)*/;
         }

         public Object getValue(Object element)
         {
            return getSynchronizer().isMapped(((PartWrapper) element).part)
               ? Boolean.TRUE : Boolean.FALSE;
         }

         public void setValue(Object element, Object value)
         {
            PartWrapper wrapper = (PartWrapper) element;
            Part part = wrapper.part;
            boolean mapped = ((Boolean) value).booleanValue();
            getSynchronizer().setMapped(part, mapped, wrapper.direction);
            if (mapped
                  && getSynchronizer().isDefault(part)
                  && getSynchronizer().getMapping(part).length() == 0)
            {
               QName qName = JaxWSResource.getType(part);
               String mapping = JaxWSResource.getDefaultMappedClass(qName);
               getSynchronizer().setMapping(part, mapping);
            }
         }
      };

      return composite;
   }
   
   private JaxWSOutlineSynchronizer getSynchronizer()
   {
      return (JaxWSOutlineSynchronizer)
         getElement().getAdapter(JaxWSOutlineSynchronizer.class);
   }

   @Override
   public void contributeButtons(Composite parent)
   {
      ((GridLayout) parent.getLayout()).numColumns++;
      Button button = new Button(parent, SWT.PUSH);
      button.setText("Generate classes...");
      setButtonLayoutData(button);
      button.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
         }

         public void widgetSelected(SelectionEvent e)
         {
            try
            {
               IJavaProject project = JavaCore.create(getCurrentProject());
               IPackageFragmentRoot root = generateClasses(project);
               performMappings(root);
               cellEditor.refresh();
            }
            catch (Throwable e1)
            {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }

         private IProject getCurrentProject()
         {
            IModelElement modelElement = (IModelElement) getModelElement();
            Resource eResource = modelElement.eResource();
            if (eResource != null)
            {
               URI eUri = eResource.getURI();
               IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(
                     eUri.segment(1));
               if (resource instanceof IProject)
               {
                  return (IProject) resource;
               }
               else if (resource != null)
               {
                  return resource.getProject();
               }
            }
            return null;
         }
      });
   }

   public static class PartWrapper
   {
      private Part part;
      private DirectionType direction;
      private Object parent;
      
      public PartWrapper(Part part, DirectionType direction, Object parent)
      {
         this.part = part;
         this.direction = direction;
         this.parent = parent;
      }
   }
   
   @SuppressWarnings("unchecked") //$NON-NLS-1$
   private Object[] wrap(Message message, DirectionType direction, Object parent)
   {
      Collection<Part> parts = message.getParts().values();
      Object[] result = new Object[parts.size()];
      int j = 0;
      for (Iterator<Part> i = parts.iterator(); i.hasNext(); j++)
      {
         Part part = i.next();
         result[j] = new PartWrapper(part, direction, parent);
      }
      return result;
   }

   private void performMappings(IPackageFragmentRoot root) throws JavaModelException
   {
      Map<String, IPackageFragment> ns2pkgs = collectPackages(root);
      Operation operation = getSynchronizer().getOperation().getOperation();
      for (Object part : operation.getInput().getMessage().getParts().values())
      {
         performMapping(ns2pkgs, (Part) part, DirectionType.IN_LITERAL);
      }
      for (Object part : operation.getOutput().getMessage().getParts().values())
      {
         performMapping(ns2pkgs, (Part) part, DirectionType.OUT_LITERAL);
      }
      for (Object fault : operation.getFaults().values())
      {
         for (Object part : ((Fault) fault).getMessage().getParts().values())
         {
            performMapping(ns2pkgs, (Part) part, DirectionType.OUT_LITERAL);
         }
      }
   }

   private void performMapping(Map<String, IPackageFragment> ns2pkgs, Part part,
         DirectionType direction)
   {
      QName qName = JaxWSResource.getType(part);
      String mapping = JaxWSResource.getDefaultMappedClass(qName);
      if (mapping == null)
      {
         IPackageFragment fragment = ns2pkgs.get(qName.getNamespaceURI());
         if (fragment != null)
         {
            String localPart = qName.getLocalPart().trim();
            ICompilationUnit unit = fragment.getCompilationUnit(toJavaClassName(localPart));
            if (unit.exists())
            {
               mapping = fragment.getElementName() + '.' + unit.getElementName();
               // strip .java file extension
               mapping = mapping.substring(0, mapping.length() - 5);
            }
         }
      }
      if (mapping != null)
      {
         getSynchronizer().setMapped(part, true, direction);
         getSynchronizer().setMapping(part, mapping);
      }
   }

   private String toJavaClassName(String localPart)
   {
      return localPart.substring(0, 1).toUpperCase() + localPart.substring(1) + ".java";
   }

   @SuppressWarnings("unchecked")
   private Map<String, IPackageFragment> collectPackages(IPackageFragmentRoot root)
         throws JavaModelException
   {
      Map<String, IPackageFragment> ns2pkgs = new HashMap<String, IPackageFragment>();
      IJavaElement[] fragments = root.getChildren();
      for (int i = 0; i < fragments.length; i++)
      {
         ICompilationUnit unit = ((IPackageFragment) fragments[i])
               .getCompilationUnit("package-info.java");
         if (unit.exists())
         {
            NormalAnnotation annotation = (NormalAnnotation) JaxWSASTVisitor.getAnnotation(unit, XmlSchema.class);
            if (annotation != null)
            {
               List<MemberValuePair> values = annotation.values();
               for (MemberValuePair valuePair : values)
               {
                  if ("namespace".equals(valuePair.getName().toString()))
                  {
                     ns2pkgs.put(unquote(valuePair.getValue().toString()), (IPackageFragment) fragments[i]);
                     break;
                  }
               }
            }
         }
      }
      return ns2pkgs;
   }

   private String unquote(String namespace)
   {
      return namespace.substring(1, namespace.length() - 1);
   }

   private IPackageFragmentRoot getSourceLocation(IJavaProject project) throws Throwable
   {
      IPackageFragmentRoot[] roots = project.getPackageFragmentRoots();
      for (int i = 0; i < roots.length; i++)
      {
         if (roots[i].getKind() == IPackageFragmentRoot.K_SOURCE)
         {
            return roots[i];
         }
      }

      return null;
   }

   private IPackageFragmentRoot generateClasses(IJavaProject project) throws Throwable
   {
      IPackageFragmentRoot root = getSourceLocation(project);
      String location = root.getCorrespondingResource().getLocation().toString();
      String wsdlLocation = getSynchronizer().getWsdlLocation();

      String[] args = new String[] {
         "-d", location,
         "-keep",
         "-Xnocompile",
         "-extension",
         wsdlLocation
      };

      WsImport.doMain(args);
      project.getResource().refreshLocal(IResource.DEPTH_INFINITE, null);
      
      return root;
   }

   private class OperationParameterTreeContentProvider implements ITreeContentProvider
   {
      private BindingOperation operation;

      public Object[] getChildren(Object parentElement)
      {
         if (LABELS[0][0] == parentElement)
         {
            Message message = operation.getOperation().getInput().getMessage();
            return wrap(message, DirectionType.IN_LITERAL, parentElement);
         }
         if (LABELS[1][0] == parentElement)
         {
            Message message = operation.getOperation().getOutput().getMessage();
            return wrap(message, DirectionType.OUT_LITERAL, parentElement);
         }
         if (LABELS[2][0] == parentElement)
         {
            return operation.getOperation().getFaults().values().toArray();
         }
         if (parentElement instanceof Fault)
         {
            Message message = ((Fault) parentElement).getMessage();
            return wrap(message, DirectionType.OUT_LITERAL, parentElement);
         }
         return null;
      }

      public Object getParent(Object element)
      {
         if (element instanceof Fault)
         {
            return LABELS[2][0];
         }
         if (element instanceof PartWrapper)
         {
            return ((PartWrapper) element).parent;
         }
         return null;
      }

      public boolean hasChildren(Object element)
      {
         if (LABELS[0][0] == element)
         {
            return hasInputParts();
         }
         if (LABELS[1][0] == element)
         {
            return hasOutputParts();
         }
         if (LABELS[2][0] == element)
         {
            return hasFaults();
         }
         if (element instanceof Fault)
         {
            return hasFaultParts((Fault) element);
         }
         return false;
      }

      private boolean hasFaults()
      {
         return !operation.getOperation().getFaults().isEmpty();
      }

      private boolean hasFaultParts(Fault fault)
      {
         return fault.getMessage() != null
            && !fault.getMessage().getParts().isEmpty();
      }

      private boolean hasOutputParts()
      {
         return operation.getOperation().getOutput() != null
            && operation.getOperation().getOutput().getMessage() != null
            && !operation.getOperation().getOutput().getMessage().getParts().isEmpty();
      }

      private boolean hasInputParts()
      {
         return operation.getOperation().getInput() != null
            && operation.getOperation().getInput().getMessage() != null
            && !operation.getOperation().getInput().getMessage().getParts().isEmpty();
      }

      public Object[] getElements(Object inputElement)
      {
         ArrayList<Object> result = new ArrayList<Object>(3);
         if (hasInputParts())
         {
            result.add(LABELS[0][0]); 
         }

         if (hasOutputParts())
         {
            result.add(LABELS[1][0]); 
         }

         if (hasFaults())
         {
            result.add(LABELS[2][0]);
         }
         return result.toArray();
      }

      public void dispose()
      {
         operation = null;
      }

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
      {
         operation = (BindingOperation) newInput;
      }
   }
}
