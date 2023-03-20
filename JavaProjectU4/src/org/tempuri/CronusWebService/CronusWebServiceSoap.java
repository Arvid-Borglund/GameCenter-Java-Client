/**
 * CronusWebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri.CronusWebService;

public interface CronusWebServiceSoap extends java.rmi.Remote {
    public java.lang.String getServerError() throws java.rmi.RemoteException;
    public java.lang.String getEmployeeById(java.lang.String empId) throws java.rmi.RemoteException;
    public java.lang.String createEmployee(org.tempuri.CronusWebService.KeyValuePairCronus[] employeeData) throws java.rmi.RemoteException;
    public java.lang.String updateEmployee(org.tempuri.CronusWebService.KeyValuePairCronus[] employeeData) throws java.rmi.RemoteException;
    public java.lang.String deleteEmployee(java.lang.String empId) throws java.rmi.RemoteException;
    public java.lang.String[] getPrimaryKeyConstraintNames() throws java.rmi.RemoteException;
    public java.lang.String[] getAllColumnNames() throws java.rmi.RemoteException;
    public java.lang.String getTotalTables() throws java.rmi.RemoteException;
    public java.lang.String getTotalColumns() throws java.rmi.RemoteException;
}
