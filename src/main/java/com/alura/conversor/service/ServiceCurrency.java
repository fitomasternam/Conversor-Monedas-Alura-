/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.alura.conversor.service;

/**
 *
 * @author fito
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alura.conversor.model.CurrencyResponseDTO;
import com.alura.conversor.util.ExchangeApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServiceCurrency
{
    private final ExchangeApi apiCliente;
    private final ObjectMapper objetoMapper;
    private final String apiKeys;

    public ServiceCurrency(ExchangeApi apiClient, ObjectMapper objectMapper)
    {
        this.apiCliente = apiClient;
        this.objetoMapper = objectMapper;
        this.apiKeys = loadApiKey();
    }

    public CurrencyResponseDTO getExchangeRate(String baseCode, String targetCode, Double amount)
    {
        String formattedAmount = String.format("%.0f", amount);
        String apiUrl = "https://v6.exchangerate-api.com/v6/" + apiKeys + "/pair/" + baseCode + "/" + targetCode + "/" + formattedAmount;

        try
        {
            String jsonResponse = apiCliente.get(apiUrl).orElseThrow(() -> new RuntimeException("Error al obtener la respuesta de la API"));
            return objetoMapper.readValue(jsonResponse, CurrencyResponseDTO.class);
        }
        catch (Exception e)
        {
            System.err.println("Excepción al obtener la tasa de cambio: " + e.getMessage());
            return null;
        }
    }

    private String loadApiKey()
    {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties"))
        {
            if (input == null)
            {
                throw new RuntimeException("No se puede encontrar el archivo de configuración: config.properties");
            }

            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("api.key");
        }
        catch (IOException ex)
        {
            throw new RuntimeException("Error al cargar la clave API desde config.properties", ex);
        }
    }
}