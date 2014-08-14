/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.util.Collection;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ProcessSymbolTypeImpl#getProcess <em>Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ProcessSymbolTypeImpl#getSubProcesses <em>Sub Processes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ProcessSymbolTypeImpl#getParentProcesses <em>Parent Processes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessSymbolTypeImpl extends IModelElementNodeSymbolImpl implements ProcessSymbolType {
	/**
	 * The cached value of the '{@link #getProcess() <em>Process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcess()
	 * @generated
	 * @ordered
	 */
	protected ProcessDefinitionType process;

	/**
	 * The cached value of the '{@link #getSubProcesses() <em>Sub Processes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubProcesses()
	 * @generated
	 * @ordered
	 */
	protected EList<SubProcessOfConnectionType> subProcesses;

	/**
	 * The cached value of the '{@link #getParentProcesses() <em>Parent Processes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentProcesses()
	 * @generated
	 * @ordered
	 */
	protected EList<SubProcessOfConnectionType> parentProcesses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessSymbolTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getProcessSymbolType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessDefinitionType getProcess() {
		return process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcess(ProcessDefinitionType newProcess, NotificationChain msgs) {
		ProcessDefinitionType oldProcess = process;
		process = newProcess;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotPackage.PROCESS_SYMBOL_TYPE__PROCESS, oldProcess, newProcess);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcess(ProcessDefinitionType newProcess) {
		if (newProcess != process) {
			NotificationChain msgs = null;
			if (process != null)
				msgs = ((InternalEObject)process).eInverseRemove(this, CarnotPackage.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS, ProcessDefinitionType.class, msgs);
			if (newProcess != null)
				msgs = ((InternalEObject)newProcess).eInverseAdd(this, CarnotPackage.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS, ProcessDefinitionType.class, msgs);
			msgs = basicSetProcess(newProcess, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CarnotPackage.PROCESS_SYMBOL_TYPE__PROCESS, newProcess, newProcess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SubProcessOfConnectionType> getSubProcesses() {
		if (subProcesses == null) {
			subProcesses = new EObjectWithInverseEList<SubProcessOfConnectionType>(SubProcessOfConnectionType.class, this, CarnotPackage.PROCESS_SYMBOL_TYPE__SUB_PROCESSES, CarnotPackage.SUB_PROCESS_OF_CONNECTION_TYPE__PROCESS_SYMBOL);
		}
		return subProcesses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SubProcessOfConnectionType> getParentProcesses() {
		if (parentProcesses == null) {
			parentProcesses = new EObjectWithInverseEList<SubProcessOfConnectionType>(SubProcessOfConnectionType.class, this, CarnotPackage.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES, CarnotPackage.SUB_PROCESS_OF_CONNECTION_TYPE__SUBPROCESS_SYMBOL);
		}
		return parentProcesses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PROCESS:
				if (process != null)
					msgs = ((InternalEObject)process).eInverseRemove(this, CarnotPackage.PROCESS_DEFINITION_TYPE__PROCESS_SYMBOLS, ProcessDefinitionType.class, msgs);
				return basicSetProcess((ProcessDefinitionType)otherEnd, msgs);
			case CarnotPackage.PROCESS_SYMBOL_TYPE__SUB_PROCESSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubProcesses()).basicAdd(otherEnd, msgs);
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParentProcesses()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PROCESS:
				return basicSetProcess(null, msgs);
			case CarnotPackage.PROCESS_SYMBOL_TYPE__SUB_PROCESSES:
				return ((InternalEList<?>)getSubProcesses()).basicRemove(otherEnd, msgs);
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES:
				return ((InternalEList<?>)getParentProcesses()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PROCESS:
				return getProcess();
			case CarnotPackage.PROCESS_SYMBOL_TYPE__SUB_PROCESSES:
				return getSubProcesses();
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES:
				return getParentProcesses();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PROCESS:
				setProcess((ProcessDefinitionType)newValue);
				return;
			case CarnotPackage.PROCESS_SYMBOL_TYPE__SUB_PROCESSES:
				getSubProcesses().clear();
				getSubProcesses().addAll((Collection<? extends SubProcessOfConnectionType>)newValue);
				return;
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES:
				getParentProcesses().clear();
				getParentProcesses().addAll((Collection<? extends SubProcessOfConnectionType>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PROCESS:
				setProcess((ProcessDefinitionType)null);
				return;
			case CarnotPackage.PROCESS_SYMBOL_TYPE__SUB_PROCESSES:
				getSubProcesses().clear();
				return;
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES:
				getParentProcesses().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PROCESS:
				return process != null;
			case CarnotPackage.PROCESS_SYMBOL_TYPE__SUB_PROCESSES:
				return subProcesses != null && !subProcesses.isEmpty();
			case CarnotPackage.PROCESS_SYMBOL_TYPE__PARENT_PROCESSES:
				return parentProcesses != null && !parentProcesses.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ProcessSymbolTypeImpl
