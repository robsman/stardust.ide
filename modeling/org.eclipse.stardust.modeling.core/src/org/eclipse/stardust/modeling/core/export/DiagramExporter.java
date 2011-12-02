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
package org.eclipse.stardust.modeling.core.export;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.ActivitySymbolNodeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.IHighliteableGraphicalObject;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.WorkflowModelDiagramEditPartFactory;
import org.eclipse.stardust.modeling.core.highlighting.ColorFactory;
import org.eclipse.stardust.modeling.core.highlighting.HighlightState;
import org.eclipse.stardust.modeling.core.highlighting.IColorFactory;
import org.eclipse.stardust.modeling.core.utils.ImageFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import ag.carnot.base.log.LogManager;
import ag.carnot.base.log.Logger;
import ag.carnot.error.PublicException;

/**
 * @author sborn
 * @version $Revision$
 */
public class DiagramExporter
{
   private static final Logger trace = LogManager.getLogger(DiagramExporter.class);
   private static final boolean headlessMode;

   static
   {
      // In headless mode the DiagramPlugin instance will not be created automatically.
      // This means that the DiagramPlugin and the SWT Display has to be created manually.
      if (null == DiagramPlugin.getDefault())
      {
         new DiagramPlugin();
         headlessMode = true;
      }
      else
      {
         headlessMode = false;
      }
   }

   private static int jpgInsetLeft = 50;
   private static int jpgInsetRigth = 50;
   private static int jpgInsetTop = 25;
   private static int jpgInsetBottom = 25;

   private DiagramType diagram;
   private Map/*<Long, HighlightState>*/ highligteStates;
   private final IColorFactory colorFactory;

   private Integer changedFontHeight;
   private final IWorkbenchPart editor;

   public DiagramExporter(DiagramType diagram)
   {
      this(diagram, null, null);
   }

   public DiagramExporter(DiagramType diagram, Integer fontHeight)
   {
      this(diagram, fontHeight, null);
   }

   public DiagramExporter(DiagramType diagram, IWorkbenchPart workbenchPart)
   {
      this(diagram, null, workbenchPart);
   }

   protected DiagramExporter(DiagramType diagram, Integer fontHeight, IWorkbenchPart workbenchPart)
   {
      this.diagram = diagram;
      this.changedFontHeight = fontHeight;
      this.editor = workbenchPart;
      setHighligteStates(null);

      if (headlessMode && null == Display.getCurrent())
      {
         new Display();
      }

      colorFactory = new ColorFactory(ColorConstants.black);
   }

   public byte[] dumpDiagramToJPEG()
   {
      BufferedImage image = drawDiagram();

      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      encodeImage(image, outStream, ImageFormat.JPEG);

      return outStream.toByteArray();
   }

   public byte[] dumpDiagramToPNG()
   {
      BufferedImage image = drawDiagram();

      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      encodeImage(image, outStream, ImageFormat.PNG);

      return outStream.toByteArray();
   }

   public byte[] dumpDiagramToGIF()
   {
      BufferedImage image = drawDiagram();

      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      encodeImage(image, outStream, ImageFormat.GIF);

      return outStream.toByteArray();
   }

   public void dumpDiagramToJPEGFile(File file)
   {
      BufferedImage image = drawDiagram();

      try
      {
         encodeImage(image, new FileOutputStream(file), ImageFormat.JPEG);
      }
      catch (FileNotFoundException e)
      {
         throw new PublicException(e);
      }
   }

   public void dumpDiagramToPNGFile(File file)
   {
      BufferedImage image = drawDiagram();

      try
      {
         encodeImage(image, new FileOutputStream(file), ImageFormat.PNG);
      }
      catch (FileNotFoundException e)
      {
         throw new PublicException(e);
      }
   }

   public void dumpDiagramToGIFFile(File file)
   {
      BufferedImage image = drawDiagram();

      try
      {
         encodeImage(image, new FileOutputStream(file), ImageFormat.GIF);
      }
      catch (FileNotFoundException e)
      {
         throw new PublicException(e);
      }
   }

   public void setHighligteStates(Map highligteStates)
   {
      this.highligteStates = null != highligteStates ? highligteStates : new HashMap();
   }

   private void applyHighlightStates(EditPart editPart)
   {
      if (null != editPart)
      {
         Object rawModel = editPart.getModel();

         if (rawModel instanceof ActivitySymbolType)
         {
            ActivitySymbolType activitySymbol = (ActivitySymbolType) rawModel;
            Long oid = new Long(activitySymbol.getActivity().getElementOid());
            HighlightState highlightState = (HighlightState)highligteStates.get(oid);
            if (null != highlightState)
            {
               IHighliteableGraphicalObject highlightable = (IHighliteableGraphicalObject) editPart;

               highlightable.setHighliteBorderColor(colorFactory
                     .createColor(highlightState));
            }

            ActivitySymbolNodeEditPart symbolEditPart = (ActivitySymbolNodeEditPart) editPart;
            List connectionEditParts = new ArrayList();
            connectionEditParts.addAll(symbolEditPart.getSourceConnections());
            connectionEditParts.addAll(symbolEditPart.getTargetConnections());

            for (Iterator iter = connectionEditParts.iterator(); iter.hasNext();)
            {
               applyHighlightStates((EditPart) iter.next());
            }
         }
         else if (rawModel instanceof TransitionConnectionType)
         {
            TransitionConnectionType transitionConnection = (TransitionConnectionType) rawModel;
            TransitionType transition = transitionConnection.getTransition();

            if (null != transition)
            {
               Long oid = new Long(transition.getElementOid());
               HighlightState highlightState = (HighlightState)highligteStates.get(oid);
               if (null != highlightState)
               {
                  IHighliteableGraphicalObject highlightable = (IHighliteableGraphicalObject) editPart;

                  highlightable.setHighliteBorderColor(colorFactory
                        .createColor(highlightState));
               }
            }
         }

         for (Iterator iter = editPart.getChildren().iterator(); iter.hasNext();)
         {
            applyHighlightStates((EditPart)iter.next());
         }
      }
   }

   private BufferedImage drawDiagram()
   {
      GraphicalViewer viewer = new ScrollingGraphicalViewer();
      viewer
            .setEditPartFactory(new WorkflowModelDiagramEditPartFactory(
                  (WorkflowModelEditor) (editor instanceof WorkflowModelEditor
                        ? editor
                        : null)));

      // This is done by default on creating ScrollingGraphicalViewer
      //ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
      //viewer.setRootEditPart(rootEditPart);

      EditPart editPart = viewer.getEditPartFactory().createEditPart(null, diagram);
      editPart.setParent(viewer.getRootEditPart());

      if (editPart instanceof GraphicalEditPart)
      {
         Font font = JFaceResources.getDialogFont();
         try
         {
            GraphicalEditPart graphicalEditPart = (GraphicalEditPart) editPart;
            viewer.setContents(graphicalEditPart);

            applyHighlightStates(graphicalEditPart);

            if (null != changedFontHeight)
            {
               FontData fontData[] = font.getFontData();
               for (int i = 0; i < fontData.length; ++i)
               {
                  fontData[i].setHeight(changedFontHeight.intValue());
               }
               font = new Font(Display.getCurrent(), fontData);
            }

            IFigure diagramLayer = graphicalEditPart.getFigure();

            // necessary when rendered with SWTSwing instead of "native" SWT
            if (null == diagramLayer.getBackgroundColor())
            {
               diagramLayer.setBackgroundColor(ColorConstants.white);
            }

            diagramLayer.setFont(font);
            diagramLayer.validate();

            LayerManager manager = (LayerManager) viewer.getEditPartRegistry().get(
                  LayerManager.ID);
            IFigure connectionLayer = manager.getLayer(LayerConstants.CONNECTION_LAYER);
            connectionLayer.setFont(font);
            connectionLayer.validate();

            Rectangle bounds = computeBounds(diagramLayer);
            diagramLayer.setBounds(bounds);

            /**
             * Anything up to the call to convertToAWT() is based on code from
             * {@link org.eclipse.draw2d.BufferedGraphicsSource#getGraphics} and slightly
             * adjusted for portability.
             */
            Image imageBuffer;
            try
            {
               imageBuffer = new Image(null, bounds.width, bounds.height);
            }
            catch (SWTError noMoreHandles)
            {
               imageBuffer = null;
            }
            catch (IllegalArgumentException tooBig)
            {
               imageBuffer = null;
            }

            Graphics graphics;
            GC imageGC;
            if (imageBuffer != null)
            {
               imageGC = new GC(imageBuffer, SWT.RIGHT_TO_LEFT | SWT.LEFT_TO_RIGHT);
               graphics = new SWTGraphics(imageGC);
               graphics.translate(bounds.getLocation().negate());
            }
            else
            {
               throw new RuntimeException(MessageFormat.format(
                     Diagram_Messages.MSG_CannotCreateImageBuffer, new Object[0]));
            }

            graphics.setClip(bounds);

            // necessary when rendered with SWTSwing instead of "native" SWT
            graphics.fillRectangle(bounds.x, bounds.y, bounds.width, bounds.height);

            diagramLayer.setBorder(null);
            diagramLayer.paint(graphics);

            connectionLayer.setBorder(null);
            connectionLayer.paint(graphics);

            return convertToAWT(imageBuffer.getImageData());
         }
         finally
         {
            if (null != changedFontHeight)
            {
               font.dispose();
            }
         }
      }

      throw new RuntimeException(Diagram_Messages.MSG_CannotDrawDiagram);
   }

   private Rectangle computeBounds(IFigure figure)
   {
      int left = Integer.MAX_VALUE;
      int right = Integer.MIN_VALUE;
      int top = Integer.MAX_VALUE;
      int bottom = Integer.MIN_VALUE;

      List children = figure.getChildren();
      for (Iterator iter = children.iterator(); iter.hasNext();)
      {
         IFigure child = (IFigure) iter.next();
         Rectangle childBounds = child.getBounds();

         left = Math.min(left, childBounds.x);
         right = Math.max(right, (childBounds.x + childBounds.width));
         top = Math.min(top, childBounds.y);
         bottom = Math.max(bottom, (childBounds.y + childBounds.height));
      }

      Rectangle rect = new Rectangle(
            left - jpgInsetLeft,
            top - jpgInsetTop,
            right - left + jpgInsetLeft + jpgInsetRigth,
            bottom - top + jpgInsetTop + jpgInsetBottom);
      return rect;
   }

   private void encodeImage(BufferedImage image, OutputStream outStream,
         String imgFormat)
   {
      // Encode the image
      try
      {
         ImageIO.write(image, imgFormat, outStream);
      }
      catch (Exception x)
      {
         throw new PublicException(x);
      }
      finally
      {
         if (outStream != null)
         {
            try
            {
               outStream.close();
            }
            catch (IOException ex)
            {
               throw new PublicException(ex);
            }
         }
      }
   }

   /**
    * Converts SWT image data to AWT buffered image.
    * Copied from http://www.eclipse.org/swt/snippets/ (class Snippet156)
    *
    * @param data
    * @return
    */
   private static BufferedImage convertToAWT(ImageData data)
   {
      ColorModel colorModel = null;
      PaletteData palette = data.palette;
      if (palette.isDirect)
      {
         colorModel = new DirectColorModel(data.depth, palette.redMask,
               palette.greenMask, palette.blueMask);
         BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel
               .createCompatibleWritableRaster(data.width, data.height), false, null);
         WritableRaster raster = bufferedImage.getRaster();
         int[] pixelArray = new int[3];
         for (int y = 0; y < data.height; y++)
         {
            for (int x = 0; x < data.width; x++)
            {
               int pixel = data.getPixel(x, y);
               RGB rgb = palette.getRGB(pixel);
               pixelArray[0] = rgb.red;
               pixelArray[1] = rgb.green;
               pixelArray[2] = rgb.blue;
               raster.setPixels(x, y, 1, 1, pixelArray);
            }
         }
         return bufferedImage;
      }
      else
      {
         RGB[] rgbs = palette.getRGBs();
         byte[] red = new byte[rgbs.length];
         byte[] green = new byte[rgbs.length];
         byte[] blue = new byte[rgbs.length];
         for (int i = 0; i < rgbs.length; i++)
         {
            RGB rgb = rgbs[i];
            red[i] = (byte) rgb.red;
            green[i] = (byte) rgb.green;
            blue[i] = (byte) rgb.blue;
         }
         if (data.transparentPixel != -1)
         {
            colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue,
                  data.transparentPixel);
         }
         else
         {
            colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
         }
         BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel
               .createCompatibleWritableRaster(data.width, data.height), false, null);
         WritableRaster raster = bufferedImage.getRaster();
         int[] pixelArray = new int[1];
         for (int y = 0; y < data.height; y++)
         {
            for (int x = 0; x < data.width; x++)
            {
               int pixel = data.getPixel(x, y);
               pixelArray[0] = pixel;
               raster.setPixel(x, y, pixelArray);
            }
         }
         return bufferedImage;
      }
   }}
