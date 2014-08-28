package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.QName;

import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.core.pojo.utils.JavaApplicationTypeHelper;
import org.eclipse.stardust.engine.core.spi.extensions.model.AccessPoint;
import org.eclipse.stardust.model.bpmn2.extension.AccessPointSchemaWrapper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.XSDType2Stardust;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;

/**
 * @author Simon Nikles
 *
 */
public class IntrinsicJavaAccesspointInfo {

	public static String encodeMethod(IMethod method) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException
	{
		return Reflect.encodeMethod(getMethod(method)).toString();
	}

	public static Method getMethod(IMethod method) throws ClassNotFoundException, MalformedURLException, CoreException, NoSuchMethodException, SecurityException {
		if (null == method) return null;
		IType type = method.getDeclaringType();
		IJavaProject javaProject = method.getJavaProject();
		List<String> javaTypes = new ArrayList<String>();
		String[] parameterTypes = method.getParameterTypes();
		for (String paramType : parameterTypes) {
	        String typeString = Signature.toString(paramType);
	        String[][] resolvedType = type.resolveType(typeString);
	        IType parameterType = null == resolvedType || resolvedType.length == 0 
	        					? null 
	        					: type.getJavaProject().findType(resolvedType[0][0], resolvedType[0][1]);
	        if (null != parameterType) javaTypes.add(parameterType.getFullyQualifiedName());
		}
		Class<?> cls = loadClass(javaProject, type.getFullyQualifiedName()); 
		
		Method mth = cls.getMethod(method.getElementName(), getParameterTypes(javaProject, cls, javaTypes.toArray(new String[]{})).toArray(new Class<?>[]{})); // method.getParameterTypes()).toArray(new Class<?>[]{}));
		return mth;
	}
	
	public static String encodeConstructor(IMethod constructorMethod) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException
	{
		if (null == constructorMethod) return "";
		IJavaProject javaProject = constructorMethod.getJavaProject();
		IType declaringType = constructorMethod.getDeclaringType();
		Class<?> cls = loadClass(javaProject, declaringType.getTypeQualifiedName());
		Constructor<?> constructor = cls.getConstructor(getParameterTypes(javaProject, cls, constructorMethod.getParameterTypes()).toArray(new Class<?>[]{}));
		return Reflect.encodeConstructor(constructor).toString();
	}

	private static List<Class<?>> getParameterTypes(IJavaProject project, Class<?> cls, String[] parameterTypes) throws ClassNotFoundException, MalformedURLException, CoreException {
		List<Class<?>> types = new ArrayList<Class<?>>();
		for (String param : parameterTypes) {
			types.add(loadClass(project, param));
		}
		return types;
	}

	public static String encodeMethod(Method mth)
	{
		return Reflect.encodeMethod(mth).toString();
	}


	public static Method decodeMethod(Class<?> cls, String encodedMethod)
	{
		return Reflect.decodeMethod(cls, encodedMethod);
	}

	public static Constructor<?> decodeConstructor(Class<?> cls, String encodedConstructor)
	{
		return Reflect.decodeConstructor(cls, encodedConstructor);
	}

	public static ItemDefinition addAccessPointItemDefinitionSchema(IMethod method, ItemDefinition itemDef) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException {
		Method mth = getMethod(method);
		Class<?> cls = mth.getDeclaringClass();
		@SuppressWarnings("rawtypes")
		Map calculateAccessPoints = JavaApplicationTypeHelper.calculateAccessPoints(cls, mth, false, false);
		AccessPointSchemaWrapper wrapper = new AccessPointSchemaWrapper();
		for (Object v : calculateAccessPoints.values()) {
			AccessPoint ap = (AccessPoint) v;
			String id = ap.getId();
			String displayName = ap.getName();
			String flavor = ap.getAttribute("carnot:engine:flavor").toString();
			String typeClass = ap.getAttribute("carnot:engine:className").toString();
			if ("RETURN_VALUE".equals(flavor) 
					||  "PARAMETER".equals(flavor)) {
				wrapper.addElement(displayName, id, getDataType(typeClass), id, typeClass);
			}
		}
		return ExtensionHelper2.INSTANCE.createAccessPointItemDefinition(wrapper, itemDef);
	}

	// TODO MAP TYPES
	private static XSDTypeDefinition getDataType(String typeClass) {
		XSDSimpleTypeDefinition simpleType = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
		simpleType.setTargetNamespace(XSDPackage.eNS_URI);
		if (java.lang.String.class.toString().equals(typeClass)) {
			simpleType.setName("string");
		} else if (Integer.TYPE.toString().equals(typeClass)
		 || Integer.class.toString().equals(typeClass)) {
			simpleType.setName("integer");
		} else {
			simpleType.setName("anyType");
		}		
		return simpleType;
	}

	public static Map<String, String> getMethodParams(IMethod method) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException {
		Method mth = getMethod(method);
		Class<?> cls = mth.getDeclaringClass();
		return getParams(cls, mth);
	}
	
	public static Map<String, String> getParams(String className, String encodedMethod) {
		Class<?> cls = null;
		Method mth = null;
		try {
			cls = findClassInWorkspace(className); 
			mth = Reflect.decodeMethod(cls, encodedMethod);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (null == cls || null == mth) return null;

		return getParams(cls, mth);
	}
	
	private static Map<String, String> getParams(Class<?> cls,	Method mth) {
		Map<String, String> paramList = new HashMap<String, String>();
		
		@SuppressWarnings("rawtypes")
		Map calculateAccessPoints = JavaApplicationTypeHelper.calculateAccessPoints(cls, mth, false, false);

		for (Object v : calculateAccessPoints.values()) {
			AccessPoint ap = (AccessPoint) v;
			String id = ap.getId();
			String displayName = ap.getName();
			String flavor = ap.getAttribute("carnot:engine:flavor").toString();
			
			if ("RETURN_VALUE".equals(flavor) 
				||  "PARAMETER".equals(flavor)) {
				paramList.put(displayName, id); // inverse key value pair
			}
		}

		return paramList;
	}

	private static java.util.List<IJavaProject> getJavaProjects() {
		List<IJavaProject> javaProjects = new ArrayList<IJavaProject>();
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for(IProject project: projects){
			try {
				if (project.isOpen() && project.hasNature(JavaCore.NATURE_ID)) {
					IJavaProject javaProject = JavaCore.create(project);
					javaProjects.add(javaProject);				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return javaProjects;
	}

	public static Class<?> findClassInWorkspace(String clsName) {
		List<IJavaProject> projects = getJavaProjects();
		for (IJavaProject project : projects ) {
			try {
				IType findClass = project.findType(clsName);
				if (null != findClass) {
					return loadClass(project, clsName);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static IType findTypeInWorkspace(String clsName) {
		List<IJavaProject> projects = getJavaProjects();
		for (IJavaProject project : projects ) {
			try {
				IType findClass = project.findType(clsName);
				if (null != findClass) return findClass;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Class<?> loadClass(IJavaProject project, String clsName) throws ClassNotFoundException, MalformedURLException, CoreException {
		ClassLoader loader = getClassLoader(project);
		return loader.loadClass(clsName);
	}

	private static ClassLoader getClassLoader(IJavaProject project) throws CoreException, MalformedURLException {
		String[] classPathEntries = JavaRuntime.computeDefaultRuntimeClassPath(project);
		List<URL> urlList = new ArrayList<URL>();
		for (int i = 0; i < classPathEntries.length; i++) {
			String entry = classPathEntries[i];
			IPath path = new Path(entry);
			URL url = path.toFile().toURI().toURL();
			urlList.add(url);
		}		
		ClassLoader parentClassLoader = project.getClass().getClassLoader();
		URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
		URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);
		return classLoader;
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException {
		Class<?> cls = findClassInWorkspace("org.eclipse.bpmn2.modeler.runtime.stardust.JavaApp");
		Method[] mths = cls.getDeclaredMethods();
		for (Method mth: mths) {
			System.out.println(mth);
			System.out.println("encode from java method: " + encodeMethod(mth));
			System.out.println("----------------------------------------------------");
		}
		System.out.println("#######################################################");
		IType type = findTypeInWorkspace("org.eclipse.bpmn2.modeler.runtime.stardust.JavaApp");
		IMethod[] imths = type.getMethods();
		for (IMethod mth: imths) {
			try {
				System.out.println(mth);
				System.out.println("encode from IMethod: " + encodeMethod(mth));
				System.out.println("----------------------------------------------------");
				Map<String, String> params = getParams("org.eclipse.bpmn2.modeler.runtime.stardust.JavaApp", encodeMethod(mth));
				for (Entry<String,String> entry : params.entrySet()) {
					System.out.println("PARAM: " + entry.getKey() + " = " + entry.getValue());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
