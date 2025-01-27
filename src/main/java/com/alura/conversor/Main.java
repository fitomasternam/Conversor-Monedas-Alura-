/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.alura.conversor;

/**
 *
 * @author fito
 */

import com.alura.conversor.endpoint.CurrencyEndpoint;
import org.springframework.context.annotation.Configuration;
@Configuration
public class Main
{
    public static void main(String[] args)
    {
        
        System.out.println("Hola amigo, bienvenido al conversor de monedas ");
                
        CurrencyEndpoint.getInstance().showMenu();
        
         
    }
}
