import axios from "axios"

import { apiRoutes } from "../config";
import type { OrdenDeInspeccion } from "../models";
import type { MotivoTipo } from "../models/MotivoTipo";

const ordenesURL = apiRoutes.OrdenesCompletamenteFinalizadas;

export const obtenerOrdenes = async () => {
    const response = await axios.get<OrdenDeInspeccion[]>(ordenesURL)
    return response
}

export const actualizarEstadoOrden = async (numeroOrden: number, motivos: { idMotivoTipo: number; comentario: string }[]) => {
    const response = await axios.put(ordenesURL+`/cambiar-estado-orden/${numeroOrden}`, motivos)
    return response
}