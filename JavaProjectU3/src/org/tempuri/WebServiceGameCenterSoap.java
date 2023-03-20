/**
 * WebServiceGameCenterSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface WebServiceGameCenterSoap extends java.rmi.Remote {
    public void verifyCredentials(java.lang.String id, java.lang.String loginPassword, javax.xml.rpc.holders.BooleanHolder verifyCredentialsResult, javax.xml.rpc.holders.StringHolder role) throws java.rmi.RemoteException;
    public java.lang.String verifyCredentialsJava(java.lang.String id, java.lang.String loginPassword) throws java.rmi.RemoteException;
    public java.lang.String getSelectedEntityData(java.lang.String selectedEntityCon, java.lang.String role) throws java.rmi.RemoteException;
    public boolean create(org.tempuri.KeyValuePairCustom[] entityData, java.lang.String selectedEntityCon, java.lang.String role) throws java.rmi.RemoteException;
    public boolean delete(java.lang.String idDelete, java.lang.String selectedEntityCon, java.lang.String role) throws java.rmi.RemoteException;
    public java.lang.String getErrorMessage() throws java.rmi.RemoteException;
    public java.lang.String tryErrorHandling() throws java.rmi.RemoteException;
}
