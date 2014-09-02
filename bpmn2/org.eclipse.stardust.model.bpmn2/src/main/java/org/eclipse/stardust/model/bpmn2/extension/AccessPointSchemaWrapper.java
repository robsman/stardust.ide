package org.eclipse.stardust.model.bpmn2.extension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xsd.XSDTypeDefinition;

/**
 * @author Simon Nikles
 *
 */
public class AccessPointSchemaWrapper {

	public enum Direction {
		IN,
		OUT,
		BOTH
	}
	
	private List<AccessPointSchemaElement> elements = new ArrayList<AccessPointSchemaWrapper.AccessPointSchemaElement>();
	
	public List<AccessPointSchemaElement> getElements() {
		return elements;
	}

	public void setElements(List<AccessPointSchemaElement> elements) {
		this.elements = elements;
	}

	public void addElement(AccessPointSchemaElement element) {
		elements.add(element);
	}
	
	public void addElement(String displayName, String accessPointId, XSDTypeDefinition dataType, String elementName, String typeClassName, Direction direction) {
		elements.add(new AccessPointSchemaElement(displayName, accessPointId, dataType, elementName, typeClassName, direction));
	}

	public class AccessPointSchemaElement {
		
		private String displayName;
		private String accessPointId;
		private String elementName;
		private String typeClassName;
		private XSDTypeDefinition dataType;
		private Direction direction;
		
		public AccessPointSchemaElement(String displayName, String accessPointId, XSDTypeDefinition dataType, String elementName, String typeClassName, Direction direction) {
			this.displayName = displayName;
			this.accessPointId = accessPointId;
			this.dataType = dataType;
			this.elementName = elementName;
			this.typeClassName = typeClassName;
			this.direction = direction;
		}
		
		public String getDisplayName() {
			return displayName;
		}
		
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		
		public String getAccessPointId() {
			return accessPointId;
		}
		
		public void setAccessPointId(String accessPointId) {
			this.accessPointId = accessPointId;
		}
		
		public XSDTypeDefinition getDataType() {
			return dataType;
		}
		
		public void setDataType(XSDTypeDefinition dataType) {
			this.dataType = dataType;
		}

		public String getElementName() {
			return elementName;
		}

		public String getTypeClassName() {
			return typeClassName;
		}

		public Direction getDirection() {
			return direction;
		}
		
		public void setDirection(Direction direction) {
			this.direction = direction;
		}		
	}
}
