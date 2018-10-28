/**
 * CongestionService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.nateng.riits.isp.vds;

public interface CongestionService extends java.rmi.Remote {
    public void initialize(java.lang.String agencyName, java.lang.String serviceName, java.lang.String configFile) throws java.rmi.RemoteException;
    public java.lang.String getTrafficInfo(java.lang.String userName, java.lang.String userPass, java.lang.String xmlMessageRequest) throws java.rmi.RemoteException;
}
