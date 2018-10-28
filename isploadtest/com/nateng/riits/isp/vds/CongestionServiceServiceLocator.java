/**
 * CongestionServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.nateng.riits.isp.vds;

public class CongestionServiceServiceLocator extends org.apache.axis.client.Service implements com.nateng.riits.isp.vds.CongestionServiceService {

    // Use to get a proxy class for ISP_CongestionService
    private final java.lang.String ISP_CongestionService_address = "http://rtsisp01.riits.net/ISP/services/ISP_CongestionService";

    public java.lang.String getISP_CongestionServiceAddress() {
        return ISP_CongestionService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ISP_CongestionServiceWSDDServiceName = "ISP_CongestionService";

    public java.lang.String getISP_CongestionServiceWSDDServiceName() {
        return ISP_CongestionServiceWSDDServiceName;
    }

    public void setISP_CongestionServiceWSDDServiceName(java.lang.String name) {
        ISP_CongestionServiceWSDDServiceName = name;
    }

    public com.nateng.riits.isp.vds.CongestionService getISP_CongestionService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ISP_CongestionService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getISP_CongestionService(endpoint);
    }

    public com.nateng.riits.isp.vds.CongestionService getISP_CongestionService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.nateng.riits.isp.vds.ISP_CongestionServiceSoapBindingStub _stub = new com.nateng.riits.isp.vds.ISP_CongestionServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getISP_CongestionServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.nateng.riits.isp.vds.CongestionService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.nateng.riits.isp.vds.ISP_CongestionServiceSoapBindingStub _stub = new com.nateng.riits.isp.vds.ISP_CongestionServiceSoapBindingStub(new java.net.URL(ISP_CongestionService_address), this);
                _stub.setPortName(getISP_CongestionServiceWSDDServiceName());
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
        String inputPortName = portName.getLocalPart();
        if ("ISP_CongestionService".equals(inputPortName)) {
            return getISP_CongestionService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://rtsisp01.riits.net/ISP/services/ISP_CongestionService", "CongestionServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("ISP_CongestionService"));
        }
        return ports.iterator();
    }

}
