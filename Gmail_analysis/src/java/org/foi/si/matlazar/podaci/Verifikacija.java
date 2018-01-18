/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.si.matlazar.podaci;

/**
 *
 * @author HP
 */
public class Verifikacija {

    private String rezultat;
    private String email;
    private String generiraniMail;
    private String jednokratnaUporaba;
    private String webMail;
    private String mxRecords;
    private String smtpServer;
    private String smtpCheck;
    private String acceptAll;

    public Verifikacija(String rezultat, String email, String generiraniMail, String jednokratnaUporaba, String webMail, String mxRecords, String smtpServer, String smtpCheck, String acceptAll) {
        this.rezultat = rezultat;
        this.email = email;
        this.generiraniMail = generiraniMail;
        this.jednokratnaUporaba = jednokratnaUporaba;
        this.webMail = webMail;
        this.mxRecords = mxRecords;
        this.smtpServer = smtpServer;
        this.smtpCheck = smtpCheck;
        this.acceptAll = acceptAll;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeneriraniMail() {
        return generiraniMail;
    }

    public void setGeneriraniMail(String generiraniMail) {
        this.generiraniMail = generiraniMail;
    }

    public String getJednokratnaUporaba() {
        return jednokratnaUporaba;
    }

    public void setJednokratnaUporaba(String jednokratnaUporaba) {
        this.jednokratnaUporaba = jednokratnaUporaba;
    }

    public String getWebMail() {
        return webMail;
    }

    public void setWebMail(String webMail) {
        this.webMail = webMail;
    }

    public String getMxRecords() {
        return mxRecords;
    }

    public void setMxRecords(String mxRecords) {
        this.mxRecords = mxRecords;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getSmtpCheck() {
        return smtpCheck;
    }

    public void setSmtpCheck(String smtpCheck) {
        this.smtpCheck = smtpCheck;
    }

    public String getAcceptAll() {
        return acceptAll;
    }

    public void setAcceptAll(String acceptAll) {
        this.acceptAll = acceptAll;
    }
    
}
