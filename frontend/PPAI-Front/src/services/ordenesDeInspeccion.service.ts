import axios from "axios"

import { apiRoutes } from "../config";
import type { OrdenDeInspeccion } from "../models";

const ordenesURL = apiRoutes.OrdenesCompletamenteFinalizadas;

export const obtenerOrdenes = async () => {
    const response = await axios.get<OrdenDeInspeccion[]>(ordenesURL)
    return response
}