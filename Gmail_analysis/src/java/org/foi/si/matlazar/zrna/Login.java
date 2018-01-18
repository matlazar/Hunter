/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.si.matlazar.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import org.foi.si.matlazar.podaci.Izbornik;
import org.foi.si.matlazar.rest.klijenti.EmailJson;
import org.foi.si.matlazar.podaci.PodaciEmail;
import org.foi.si.matlazar.podaci.Verifikacija;

/**
 *
 * @author HP
 */
@ManagedBean
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    private String korIme = "";
    private String lozinka = "";
    Store store;
    private List<PodaciEmail> inboxList = new ArrayList<>();
    static final ArrayList<Izbornik> izbornikJezika = new ArrayList<>();
    private boolean prikaz = false;
    private String odabraniJezik;

    static {
        izbornikJezika.add(new Izbornik("hrvatski", "hr"));
        izbornikJezika.add(new Izbornik("engleski", "en"));
    }

    /**
     * Creates a new instance of login
     */
    public Login() {
    }

    public String autentikacija() throws MessagingException {
        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            Session session = Session.getDefaultInstance(props);
            store = session.getStore("imaps");
            store.connect("imap.gmail.com", korIme, lozinka);
            System.out.println(store);
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);
            Message messages[] = inbox.getMessages();
            EmailJson ej = new EmailJson();
            for (Message message : messages) {
                Address[] froms = message.getFrom();
                String email = ((InternetAddress) froms[0]).getAddress();
                System.out.println(email);
                System.out.println(message.getSubject());
                System.out.println("----------------------");
                Verifikacija ver = ej.rezultat(email);
                String subjekt = message.getSubject();
                inboxList.add(new PodaciEmail(new Verifikacija(ver.getRezultat(), ver.getEmail(), ver.getGeneriraniMail(), ver.getJednokratnaUporaba(), ver.getWebMail(), ver.getMxRecords(), ver.getSmtpServer(), ver.getSmtpCheck(), ver.getAcceptAll()), subjekt));
            }
            setPrikaz(true);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void zatvori() {
        inboxList.clear();
        setPrikaz(false);
    }

    public Object odaberiJezik() {
        setOdabraniJezik(odabraniJezik);
        return "PromjenaJezika";
    }

    public String getKorIme() {
        return korIme;
    }

    public void setKorIme(String korIme) {
        this.korIme = korIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public List<PodaciEmail> getInboxList() {
        return inboxList;
    }

    public void setInboxList(List<PodaciEmail> inboxList) {
        this.inboxList = inboxList;
    }

    public boolean isPrikaz() {
        return prikaz;
    }

    public void setPrikaz(boolean prikaz) {
        this.prikaz = prikaz;
    }

    public ArrayList<Izbornik> getIzbornikJezika() {
        return izbornikJezika;
    }

    public String getOdabraniJezik() {
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        if (view == null) {
            odabraniJezik = "hr";
        } else {
            Locale lokalniJezik = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            odabraniJezik = lokalniJezik.getLanguage();
        }

        return odabraniJezik;
    }

    public void setOdabraniJezik(String odabraniJezik) {
        this.odabraniJezik = odabraniJezik;
        Locale lokalniJezik = new Locale(odabraniJezik);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(lokalniJezik);
    }

    @PostConstruct
    public void reset() {
        if (store != null) {
            try {
                store.close();
            } catch (MessagingException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
