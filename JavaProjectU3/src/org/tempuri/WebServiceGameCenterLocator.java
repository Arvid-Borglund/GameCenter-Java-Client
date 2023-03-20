/**
 * WebServiceGameCenterLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class WebServiceGameCenterLocator extends org.apache.axis.client.Service implements org.tempuri.WebServiceGameCenter {

    public WebServiceGameCenterLocator() {
    }


    public WebServiceGameCenterLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WebServiceGameCenterLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WebServiceGameCenterSoap
    private java.lang.String WebServiceGameCenterSoap_address = "http://localhost/WebApplicationGameCenter/WebServiceGameCenter.asmx";

    public java.lang.String getWebServiceGameCenterSoapAddress() {
        return WebServiceGameCenterSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WebServiceGameCenterSoapWSDDServiceName = "WebServiceGameCenterSoap";

    public java.lang.String getWebServiceGameCenterSoapWSDDServiceName() {
        return WebServiceGameCenterSoapWSDDServiceName;
    }

    public void setWebServiceGameCenterSoapWSDDServiceName(java.lang.String name) {
        WebServiceGameCenterSoapWSDDServiceName = name;
    }

    public org.tempuri.WebServiceGameCenterSoap getWebServiceGameCenterSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WebServiceGameCenterSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWebServiceGameCenterSoap(endpoint);
    }

    public org.tempuri.WebServiceGameCenterSoap getWebServiceGameCenterSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.WebServiceGameCenterSoapStub _stub = new org.tempuri.WebServiceGameCenterSoapStub(portAddress, this);
            _stub.setPortName(getWebServiceGameCenterSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWebServiceGameCenterSoapEndpointAddress(java.lang.String address) {
        WebServiceGameCenterSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.WebServiceGameCenterSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.WebServiceGameCenterSoapStub _stub = new org.tempuri.WebServiceGameCenterSoapStub(new java.net.URL(WebServiceGameCenterSoap_address), this);
                _stub.setPortName(getWebServiceGameCenterSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WebServiceGameCenterSoap".equals(inputPortName)) {
            return getWebServiceGameCenterSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "WebServiceGameCenter");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "WebServiceGameCenterSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WebServiceGameCenterSoap".equals(portName)) {
            setWebServiceGameCenterSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
