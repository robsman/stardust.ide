package org.eclipse.stardust.test.model.transformation.bpmn.playground;

public class JavaApp {

	public JavaApp() {}
	
	public String concatTrippleString(String paramSubject, String paramPredicate, String paramObject) {
		return paramSubject.concat(" - ").concat(paramPredicate).concat(" - ").concat(paramObject);
	}

	public ComplexReturnType splitTrippleString(String trippleString) {
		return new ComplexReturnType(trippleString.split(" - "));
	}


	public static class ComplexReturnType {
		public String subject;
		public String predicate;
		public String object;
		
		public ComplexReturnType(String[] tripple) {
			subject = tripple[0];
			predicate = tripple[1];
			object = tripple[2];
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getPredicate() {
			return predicate;
		}

		public void setPredicate(String predicate) {
			this.predicate = predicate;
		}

		public String getObject() {
			return object;
		}

		public void setObject(String object) {
			this.object = object;
		}
	}
}
