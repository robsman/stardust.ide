package org.eclipse.bpmn2.modeler.runtime.stardust.composites.data.type;

import java.io.StringWriter;
import java.util.Collections;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.diagrams.ItemDefinitionDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDParser;
import org.w3c.dom.Document;

/**
 * @author Simon Nikles
 *
 */
public class StardustItemDefinitionDetailComposite extends ItemDefinitionDetailComposite {

	private Boolean hasEmbedded = false;
	private Button button;
	private XSDSchema schema;
	private Text schemaEditor;

	public StardustItemDefinitionDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustItemDefinitionDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(final EObject be) {
		super.createBindings(be);

		ItemDefinition itemDef = (ItemDefinition)be;
		schema = ExtensionHelper2.INSTANCE.getEmbeddedSchemaExtension(itemDef);
		if (null != schema) {
			hasEmbedded = true;
			updateEmbeddedSchemaEditor();
		}

		addCheckbox();

	}

	private void addCheckbox() {
		createLabel(this, "Embedded Schema");
		button = getToolkit().createButton(this, "", SWT.CHECK); //$NON-NLS-1$
		button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		button.setSelection(hasEmbedded);
		button.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hasEmbedded = new Boolean(button.getSelection());
				updateEmbeddedSchemaEditor();
				getPropertySection().getSectionRoot().redrawPage();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

	}

	private void updateEmbeddedSchemaEditor() {

		if (hasEmbedded) {
			style |= SWT.MULTI | SWT.V_SCROLL;
			if (null == schemaEditor) {
				schemaEditor = getToolkit().createText(this, "", style | SWT.BORDER); //$NON-NLS-1$
				GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
				data.heightHint = 100;
				schemaEditor.setLayoutData(data);
				schemaEditor.setEditable(true);
			}
			if (null == schema) {
				schema = XSDFactory.eINSTANCE.createXSDSchema();
			}
			try {
				schema.update();
				schema.updateDocument();
				Document document = schema.getDocument();
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "false");
				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(document), new StreamResult(writer));
				schemaEditor.setText(writer.getBuffer().toString());
			} catch (TransformerException e1) {
				e1.printStackTrace();
			}

			schemaEditor.addModifyListener( new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					RecordingCommand command = new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							XSDParser p = new XSDParser(Collections.EMPTY_MAP);
							p.parseString(schemaEditor.getText());
							schema = p.getSchema();
							ExtensionHelper.getInstance().setExtension((ItemDefinition)businessObject, schema);
						}
					};
					editingDomain.getCommandStack().execute(command);
				}
			});
		} else if (null != schemaEditor) {
			schemaEditor.dispose();
			schemaEditor = null;
		}
	}


}
