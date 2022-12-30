package com.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MailRequest {
    @ApiModelProperty(value = "El nombre del usuario", required = true)
    private String nombre;
    @ApiModelProperty(value = "El apellido del usuario", required = true)
    private String apellido;

}
