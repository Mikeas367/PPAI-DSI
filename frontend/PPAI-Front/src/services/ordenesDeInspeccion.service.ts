import axios from "axios"

import { apiRoutes } from "../config";
import type { OrdenDeInspeccion } from "../models";
import type { Estado } from "../models/Estado";

const ordenesURL = apiRoutes.OrdenesCompletamenteFinalizadas;

export const obtenerOrdenes = async () => {
    const response = await axios.get<OrdenDeInspeccion[]>(ordenesURL)
    return response
}

export const actualizarEstadoOrden = async (numeroOrden: number, estado: Estado) => {
    const url = `http://localhost:8080/api/ordenes/${numeroOrden}/estado`
    const response = await axios.put(url, estado)
    return response
}