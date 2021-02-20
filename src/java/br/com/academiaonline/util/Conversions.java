/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Diego
 */
public class Conversions {

    public static Date convertDate(String date) {
        try {
            if (date != null || !date.trim().equals("")) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
                return fmt.parse(date);
                
            } else {
                System.out.println("Problemas ao converter data!");
                return null;
            }
        } catch (ParseException ex) {
            System.out.println("Problemas ao converter data! Erro: " + ex.getMessage());
            return null;
        }
    }
}
