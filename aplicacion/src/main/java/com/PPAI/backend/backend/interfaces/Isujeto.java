package com.PPAI.backend.backend.interfaces;

import java.util.List;

public interface Isujeto {
    void registrarObservador(IObservador observador);
    void eliminarObservador(IObservador observador);
    void notificar();
}
