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
public class PodaciEmail {
    
    private Verifikacija verifikacija;
    private String subject;

    
    public PodaciEmail(Verifikacija verifikacija, String subject) {
        this.verifikacija = verifikacija;
        this.subject = subject;
    }

    public Verifikacija getVerifikacija() {
        return verifikacija;
    }

    public void setVerifikacija(Verifikacija verifikacija) {
        this.verifikacija = verifikacija;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    

}
