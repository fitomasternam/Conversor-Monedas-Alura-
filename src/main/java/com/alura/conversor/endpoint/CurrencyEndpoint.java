/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.alura.conversor.endpoint;

/**
 *
 * @author fito
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alura.conversor.model.CurrencyResponseDTO;
import com.alura.conversor.service.ServiceCurrency;
import com.alura.conversor.util.ExchangeApi;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrencyEndpoint
{
    private static CurrencyEndpoint instance;
    private final Scanner scanner;
    private final ServiceCurrency serviceCurrency;

    private CurrencyEndpoint()
    {
        this.scanner = new Scanner(System.in);
        this.serviceCurrency = new ServiceCurrency(new ExchangeApi(), new ObjectMapper());
    }

    public static CurrencyEndpoint getInstance()
    {
        if (instance == null) instance = new CurrencyEndpoint();
        return instance;
        
    }

    public void showMenu()
    {
        int option;
        while (true)
        {
            try
            {
                
                System.out.println("**********************************************");
                System.out.println("Eligi una opcion:");
                System.out.println("1. Dólar            => Peso Argentino");
                System.out.println("2. Peso Argentino   => Dólar");
                System.out.println("3. Dólar            => Real Brasileño");
                System.out.println("4. Real Brasileño   => Dólar");
                System.out.println("5. Dólar            => Peso Colombiano");
                System.out.println("6. Peso Colombiano  => Dólar");
                System.out.println("7. Salir");
                System.out.println("**********************************************");
                System.out.print("Opción: ");

                option = Integer.parseInt(scanner.nextLine());
                if (option >= 1 && option <= 6) processConversion(option);
                else if (option == 7) break;
                else System.out.println("eleji una opción del 1 al 7.");

                System.out.println();
                System.out.println("Mandale Enter para cargar el menú...");
                scanner.nextLine();
            }
            catch (Exception e)
            {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
                
            }
        }
        
        scanner.close();
        
        
    }

    private void processConversion(int option)
    {
        try
        {
            System.out.print("Ingrese el valor que desea convertir: ");
            double amount = Double.parseDouble(scanner.nextLine());
            String fromCurrency = "";
            String toCurrency = switch (option)
            {
                case 1 ->
                {
                    fromCurrency = "USD";
                    yield "ARS";
                }
                case 2 ->
                {
                    fromCurrency = "ARS";
                    yield "USD";
                }
                case 3 ->
                {
                    fromCurrency = "USD";
                    yield "BRL";
                }
                case 4 ->
                {
                    fromCurrency = "BRL";
                    yield "USD";
                }
                case 5 ->
                {
                    fromCurrency = "USD";
                    yield "COP";
                }
                case 6 ->
                {
                    fromCurrency = "COP";
                    yield "USD";
                }
                default -> "";
            };

            CurrencyResponseDTO responseDTO = serviceCurrency.getExchangeRate(fromCurrency, toCurrency, amount);
            if (responseDTO != null)
            {
                Double convertedAmount = responseDTO.getConversion_result();
                DecimalFormat df = new DecimalFormat("#,##0.00");
                String formattedAmount = df.format(amount);
                String formattedConvertedAmount = df.format(convertedAmount);

                System.out.printf("El valor de %s %s corresponde al valor final de => %s %s%n", formattedAmount, fromCurrency, formattedConvertedAmount, toCurrency);
            }
            else System.out.println("No se pudó realizar la conversión.");
        }
        catch (Exception e)
        {
            System.out.println("Error durante la conversión: " + e.getMessage());
        }
    }
}