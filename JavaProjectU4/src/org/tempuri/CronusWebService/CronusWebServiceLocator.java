/**
 * CronusWebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri.CronusWebService;

public class CronusWebServiceLocator extends org.apache.axis.client.Service implements org.tempuri.CronusWebService.CronusWebService {

    public CronusWebServiceLocator() {
    }


    public CronusWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CronusWebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CronusWebServiceSoap
    private java.lang.String CronusWebServiceSoap_address = "http://localhost/WebApplicationCronus/CronusWebService.asmx";

    public java.lang.String getCronusWebServiceSoapAddress() {
        return CronusWebServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CronusWebServiceSoapWSDDServiceName = "CronusWebServiceSoap";

    public java.lang.String getCronusWebServiceSoapWSDDServiceName() {
        return CronusWebServiceSoapWSDDServiceName;
    }

    public void setCronusWebServiceSoapWSDDServiceName(java.lang.String name) {
        CronusWebServiceSoapWSDDServiceName = name;
    }

    public org.tempuri.CronusWebService.CronusWebServiceSoap getCronusWebServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CronusWebServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCronusWebServiceSoap(endpoint);
    }

    public org.tempuri.CronusWebService.CronusWebServiceSoap getCronusWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.CronusWebService.CronusWebServiceSoapStub _stub = new org.tempuri.CronusWebService.CronusWebServiceSoapStub(portAddress, this);
            _stub.setPortName(getCronusWebServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCronusWebServiceSoapEndpointAddress(java.lang.String address) {
        CronusWebServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.CronusWebService.CronusWebServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.CronusWebService.CronusWebServiceSoapStub _stub = new org.tempuri.CronusWebService.CronusWebServiceSoapStub(new java.net.URL(CronusWebServiceSoap_address), this);
                _stub.setPortName(getCronusWebServiceSoapWSDDServiceName());
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
        if ("CronusWebServiceSoap".equals(inputPortName)) {
            return getCronusWebServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/CronusWebService", "CronusWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/CronusWebService", "CronusWebServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CronusWebServiceSoap".equals(portName)) {
            setCronusWebServiceSoapEndpointAddress(address);
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
