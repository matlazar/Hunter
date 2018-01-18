/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.si.matlazar.rest.klijenti;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.foi.si.matlazar.podaci.Verifikacija;

/**
 *
 * @author HP
 */
public class EmailJson { 
    //private String apiKey = "19f101e3dc579513560972d13a23017df84d1a50";
    EmailJsonHelper helper;
    Client client;

    public EmailJson() {
        client = ClientBuilder.newClient();
    }
    
    public Verifikacija rezultat(String email){
        try {
            WebTarget webResource = client.target(EmailJsonHelper.getHUNTER_BASE_URI()).path("v2/email-verifier");
            webResource = webResource.queryParam("email", URLEncoder.encode(email, "UTF-8"));
            webResource = webResource.queryParam("api_key", "19f101e3dc579513560972d13a23017df84d1a50");
            
            String odgovor = webResource.request(MediaType.APPLICATION_JSON).get(String.class);
            
            JsonReader reader = Json.createReader(new StringReader(odgovor));
            
            JsonObject jo = reader.readObject();
            
            JsonObject obj = jo.getJsonObject("data");
            
            Verifikacija ver = new Verifikacija(obj.getString("result"), obj.getString("email"), Boolean.toString(obj.getBoolean("gibberish")), Boolean.toString(obj.getBoolean("disposable")), Boolean.toString(obj.getBoolean("webmail")), Boolean.toString(obj.getBoolean("mx_records")), Boolean.toString(obj.getBoolean("smtp_server")), Boolean.toString(obj.getBoolean("smtp_check")), Boolean.toString(obj.getBoolean("accept_all")));
            
            return ver;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EmailJson.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
           
}
