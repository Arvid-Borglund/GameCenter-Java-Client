package org.tempuri;

public class WebServiceGameCenterSoapProxy implements org.tempuri.WebServiceGameCenterSoap {
  private String _endpoint = null;
  private org.tempuri.WebServiceGameCenterSoap webServiceGameCenterSoap = null;
  
  public WebServiceGameCenterSoapProxy() {
    _initWebServiceGameCenterSoapProxy();
  }
  
  public WebServiceGameCenterSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServiceGameCenterSoapProxy();
  }
  
  private void _initWebServiceGameCenterSoapProxy() {
    try {
      webServiceGameCenterSoap = (new org.tempuri.WebServiceGameCenterLocator()).getWebServiceGameCenterSoap();
      if (webServiceGameCenterSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServiceGameCenterSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServiceGameCenterSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServiceGameCenterSoap != null)
      ((javax.xml.rpc.Stub)webServiceGameCenterSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.WebServiceGameCenterSoap getWebServiceGameCenterSoap() {
    if (webServiceGameCenterSoap == null)
      _initWebServiceGameCenterSoapProxy();
    return webServiceGameCenterSoap;
  }
  
  public void verifyCredentials(java.lang.String id, java.lang.String loginPassword, javax.xml.rpc.holders.BooleanHolder verifyCredentialsResult, javax.xml.rpc.holders.StringHolder role) throws java.rmi.RemoteException{
    if (webServiceGameCenterSoap == null)
      _initWebServiceGameCenterSoapProxy();
    webServiceGameCenterSoap.verifyCredentials(id, loginPassword, verifyCredentialsResult, role);
  }
  
  public java.lang.String verifyCredentialsJava(java.lang.String id, java.lang.String loginPassword) throws java.rmi.RemoteException{
    if (webServiceGameCenterSoap == null)
      _initWebServiceGameCenterSoapProxy();
    return webServiceGameCenterSoap.verifyCredentialsJava(id, loginPassword);
  }
  
  public java.lang.String getSelectedEntityData(java.lang.String selectedEntityCon, java.lang.String role) throws java.rmi.RemoteException{
    if (webServiceGameCenterSoap == null)
      _initWebServiceGameCenterSoapProxy();
    return webServiceGameCenterSoap.getSelectedEntityData(selectedEntityCon, role);
  }
  
  public boolean create(org.tempuri.KeyValuePairCustom[] entityData, java.lang.String selectedEntityCon, java.lang.String role) throws java.rmi.RemoteException{
    if (webServiceGameCenterSoap == null)
      _initWebServiceGameCenterSoapProxy();
    return webServiceGameCenterSoap.create(entityData, selectedEntityCon, role);
  }
  
  public boolean delete(java.lang.String idDelete, java.lang.String selectedEntityCon, java.lang.String role) throws java.rmi.RemoteException{
    if (webServiceGameCenterSoap == null)
      _initWebServiceGameCenterSoapProxy();
    return webServiceGameCenterSoap.delete(idDelete, selectedEntityCon, role);
  }
  
  public java.lang.String getErrorMessage() throws java.rmi.RemoteException{
    if (webServiceGameCenterSoap == null)
      _initWebServiceGameCenterSoapProxy();
    return webServiceGameCenterSoap.getErrorMessage();
  }
  
  public java.lang.String tryErrorHandling() throws java.rmi.RemoteException{
    if (webServiceGameCenterSoap == null)
      _initWebServiceGameCenterSoapProxy();
    return webServiceGameCenterSoap.tryErrorHandling();
  }
  
  
}