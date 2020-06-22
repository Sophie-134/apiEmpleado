package ar.com.ada.api.empleado.models.responses;

import java.math.BigDecimal;

public class GenericResponse {
    public boolean isOk = false;
    public int id;
    public String message = "";
    public BigDecimal sueldo;
}