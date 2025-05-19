import axios from "axios";
import { apiRoutes } from "../config";
import type { MotivoTipo } from "../models/MotivoTipo";

const motivosTipoURL = apiRoutes.MotivosTipo

export const getMotivosTipo = async () => {
    const response = await axios.get<MotivoTipo[]>(motivosTipoURL)
    return response;
}


