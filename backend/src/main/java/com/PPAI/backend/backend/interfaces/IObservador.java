package com.PPAI.backend.backend.interfaces;

import com.PPAI.backend.backend.models.MotivoFueraServicio;
import com.PPAI.backend.backend.models.MotivoTipo;

import java.time.LocalDate;
import java.util.List;

//  La notificación debe incluir la identificación del sismógrafo, el nombre del estado Fuera de Servicio, la fecha y
//hora de registro del nuevo estado, y los motivos y comentarios asociados al cambio.


public interface IObservador {
    void actualizar(int identificadorSismografo,
                    String estado,
                    LocalDate fecha,
                    String mensaje);
}
