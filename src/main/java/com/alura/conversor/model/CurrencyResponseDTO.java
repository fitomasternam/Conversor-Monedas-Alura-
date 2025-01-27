/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.alura.conversor.model;

/**
 *
 * @author fito
 */
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponseDTO
{
    private String base_code;
    private String target_code;
    private Double conversion_rate;
    private Double conversion_result;
    private String result;

    @JsonAlias("e1rror-type")
    private String error_type;
}
