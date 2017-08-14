/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warsoft.servlet;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author ander
 */
public class Conexion implements Job {

    private final String URLFIREBASE = 
            "https://us-central1-nucleourbano-5b3c9.cloudfunctions.net/deleteVotos?key=500813cbfc090ffe396c58d3a2fddd0a8c81c42d";

    public Conexion() {

    }

    public void mostrar() {
        try {
            HttpClient httpclient = HttpClients.createDefault();
            URIBuilder builder = new URIBuilder(URLFIREBASE);

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            // Request body
           // StringEntity reqEntity = new StringEntity("{body}");
            //request.set

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            String estado = "";
            if (entity != null) 
            {
              estado = EntityUtils.toString(entity);
            }
            System.out.println("Se ha Ejecutado: "+estado);
        } catch (IOException | URISyntaxException | ParseException e) {
            System.out.println("Ocurrio un error: "+e.toString());
        }
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        mostrar();
    }

}
