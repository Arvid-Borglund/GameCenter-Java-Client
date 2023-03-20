package org.tempuri.CronusWebService;

public class CronusWebServiceSoapProxy implements org.tempuri.CronusWebService.CronusWebServiceSoap {
  private String _endpoint = null;
  private org.tempuri.CronusWebService.CronusWebServiceSoap cronusWebServiceSoap = null;
  
  public CronusWebServiceSoapProxy() {
    _initCronusWebServiceSoapProxy();
  }
  
  public CronusWebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initCronusWebServiceSoapProxy();
  }
  
  private void _initCronusWebServiceSoapProxy() {
    try {
      cronusWebServiceSoap = (new org.tempuri.CronusWebService.CronusWebServiceLocator()).getCronusWebServiceSoap();
      if (cronusWebServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cronusWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cronusWebServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cronusWebServiceSoap != null)
      ((javax.xml.rpc.Stub)cronusWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.CronusWebService.CronusWebServiceSoap getCronusWebServiceSoap() {
    if (cronusWebServiceSoap == null)
      _initCronusWebServiceSoapProxy();
    return cronusWebServiceSoap;
  }
  
  public java.lang.String getServerError() throws java.rmi.RemoteException{
    if (cronusWebServiceSoap == null)
      _initCronusWebServiceSoapProxy();
    return cronusWebServiceSoap.getServerError();
  }
  
  public java.lang.String getEmployeeById(java.lang.String empId) throws java.rmi.RemoteException{
    if (cronusWebServiceSoap == null)
      _initCronusWebServiceSoapProxy();
    return cronusWebServiceSoap.getEmployeeById(empId);
  }
  
  public java.lang.String createEmployee(org.tempuri.CronusWebService.KeyValuePairCronus[] employeeData) throws java.rmi.RemoteException{
    if (cronusWebServiceSoap == null)
      _initCronusWebServiceSoapProxy();
    return cronusWebServiceSoap.createEmployee(employeeData);
  }
  
  public java.lang.String updateEmployee(org.tempuri.CronusWebService.KeyValuePairCronus[] employeeData) throws java.rmi.RemoteException{
    if (cronusWebServiceSoap == null)
      _initCronusWebServiceSoapProxy();
    return cronusWebServiceSoap.updateEmployee(employeeData);
  }
  
  public java.lang.String deleteEmployee(java.lang.String empId) throws java.rmi.RemoteException{
    if (cronusWebServiceSoap == null)
      _initCronusWebServiceSoapProxy();
    return cronusWebServiceSoap.deleteEmployee(empId);
  }
  
  public java.lang.String[] getPrimaryKeyConstraintNames() throws java.rmi.RemoteException{
    if (cronusWebServiceSoap == null)
      _initCronusWebServiceSoapProxy();
    return cronusWebServiceSoap.getPrimaryKeyConstraintNames();
  }
  
  public java.lang.String[] getAllColumnNames() throws java.rmi.RemoteException{
    if (cronusWebServiceSoap == null)
      _initCronusWebServiceSoapProxy();
    return cronusWebServiceSoap.getAllColumnNames();
  }
  
  public java.lang.String getTotalTables() throws java.rmi.RemoteException{
    if (cronusWebServiceSoap == null)
      _initCronusWebServiceSoapProxy();
    return cronusWebServiceSoap.getTotalTables();
  }
  
  public java.lang.String getTotalColumns() throws java.rmi.RemoteException{
    if (cronusWebServiceSoap == null)
      _initCronusWebServiceSoapProxy();
    return cronusWebServiceSoap.getTotalColumns();
  }
  
  
}