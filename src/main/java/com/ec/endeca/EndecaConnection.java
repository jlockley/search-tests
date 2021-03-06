/**
 * 
 */
package com.ec.endeca;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.endeca.navigation.HttpENEConnection;

/**
 * <pre>
 * ************************************************************************************************
 * Copyright (c) Electrocomponents Plc.
 *
 * Author  : <<<<user name>>>>
 * Created : 18 Apr 2016 at 16:03:37
 *
 * ************************************************************************************************
 * </pre>
 */
public enum EndecaConnection {
       
    STATIC2UK   ("ecllapjmdxs653.ebs.ecomp.com", "18017"),
    STATIC2ENXASEA   ("ecllapjmdxs655.ebs.ecomp.com", "18020"), //enXAsea
    STATIC2ENXANA  ("ecllapjmdxs657.ebs.ecomp.com", "18021"), // enXAna
    STATIC2ENXAANZ   ("ecllapjmdxs657.ebs.ecomp.com", "18022"), //enXAanz
    STATIC2ENXREURO   ("ecllapjmdxs651.ebs.ecomp.com", "18019"), //enXReuro
    STATIC2FR ("ecllapjmdxs657.ebs.ecomp.com", "18008"),
    STATIC2IT ("ecllapjmdxs655.ebs.ecomp.com", "18011"),
    STATIC2DE ("ecllapjmdxs653.ebs.ecomp.com", "18005"),
    STATIC2ES ("ecllapjmdxs657.ebs.ecomp.com", "18007"),
    STATIC2HU ("ecllapjmdxs653.ebs.ecomp.com", "18009"),
    STATIC2PL ("ecllapjmdxs655.ebs.ecomp.com", "18015"),
    STATIC2CZ ("ecllapjmdxs653.ebs.ecomp.com", "18004"),
    STATIC2DA ("ecllapjmdxs655.ebs.ecomp.com", "18006"),
//    STATIC2JP ("ecllapjmdxs657.ebs.ecomp.com", "18007"),
//    STATIC2CN ("ecllapjmdxs657.ebs.ecomp.com", "18007"),
    
    PREPUK   ("ecllapjmdxs601.ebs.ecomp.com", "18017"),
    PREPENXASEA   ("ecllapjmdxs614.ebs.ecomp.com", "18020"), //enXAsea
    PREPENXANA  ("ecllapjmdxs612.ebs.ecomp.com", "18021"), // enXAna
    PREPENXAANZ   ("ecllapjmdxs602.ebs.ecomp.com", "18022"), //enXAanz
    PREPENXREURO   ("ecllapjmdxs611.ebs.ecomp.com", "18019"), //enXReuro
    PREPFR ("ecllapjmdxs605.ebs.ecomp.com", "18008"),
    PREPIT ("ecllapjmdxs605.ebs.ecomp.com", "18011"),
    PREPDE ("ecllapjmdxs609.ebs.ecomp.com", "18005"),
    PREPES ("ecllapjmdxs615.ebs.ecomp.com", "18007"),
    PREPHU ("ecllapjmdxs611.ebs.ecomp.com", "18009"),
//    PREPPL ("ecllapjmdxs605.ebs.ecomp.com", "18011"),
//    PREPCZ ("ecllapjmdxs609.ebs.ecomp.com", "18005"),
//    PREPJP ("ecllapjmdxs615.ebs.ecomp.com", "18007"),
//    PREPCN ("ecllapjmdxs615.ebs.ecomp.com", "18007"),
//    
    PRODUK ("ecllapjmdxp001.ebs.ecomp.com", "18017"),
    PRODFR ("ecllapjmdxp013.ebs.ecomp.com", "18008"),
    PRODIT ("ecllapjmdxp011.ebs.ecomp.com", "18011"),
    PRODDE ("ecllapjmdxp019.ebs.ecomp.com", "18005"),
    PRODES ("ecllapjmdxp011.ebs.ecomp.com", "18007"),
    PRODHU ("ecllapjmdxp009.ebs.ecomp.com", "18009"),
      
    SSPUK ("ecllapjmdxs903.ebs.ecomp.com", "18017"),
    SSPFR ("ecllapjmdxs901.ebs.ecomp.com", "18008"),
    SSPDE ("ecllapjmdxs901.ebs.ecomp.com", "18005"),
    SSPIT ("ecllapjmdxs901.ebs.ecomp.com", "18011"),
    SSPES ("ecllapjmdxs903.ebs.ecomp.com", "18007"),
    SSPPL ("ecllapjmdxs903.ebs.ecomp.com", "18007"),
    SSPHU ("ecllapjmdxs903.ebs.ecomp.com", "18017");
//    SSPDE ("ecllapjmdxs901.ebs.ecomp.com", "18005"),
//    SSPIT ("ecllapjmdxs901.ebs.ecomp.com", "18011"),
//    SSPES ("ecllapjmdxs903.ebs.ecomp.com", "18007");
//    SSPUK ("ecllapjmdxs903.ebs.ecomp.com", "18017"),
//    SSPDE ("ecllapjmdxs901.ebs.ecomp.com", "18005"),
//    SSPIT ("ecllapjmdxs901.ebs.ecomp.com", "18011"),
//    SSPES ("ecllapjmdxs903.ebs.ecomp.com", "18007");

    private final String endecaHost;
    private final String endecaPort;
    private static final Logger LOGGER = LogManager.getLogger(EndecaConnection.class);
    
    EndecaConnection(String endecaHost, String endecaPort) {
        this.endecaHost = endecaHost;
        this.endecaPort = endecaPort;
    }

    public HttpENEConnection getConnection(){return new HttpENEConnection(endecaHost, endecaPort);};
    
}


