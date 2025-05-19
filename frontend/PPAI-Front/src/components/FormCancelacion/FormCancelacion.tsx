import { useEffect, useState } from "react"
import type { MotivoTipo } from "../../models/MotivoTipo"
import { getMotivosTipo } from "../../services/motivosTipo.service"


export const FormCancelacion = () => {
    const [motivos, setMotivos] = useState<MotivoTipo[]>()

    useEffect(()=>{
        const obtenenerMotivos = async() => {
            const response = await getMotivosTipo()
            setMotivos(response.data)
        }
        obtenenerMotivos()
    })



    return(
        <>
        <label>Seleccione El motivo Tipo: </label>
        <select>
            {
                motivos?.map((m, index) => (
                    <option key={index} value={m.descripcion}>
                        {m.descripcion}
                    </option>
                ))
            }
        </select>
        </>
    )    
}