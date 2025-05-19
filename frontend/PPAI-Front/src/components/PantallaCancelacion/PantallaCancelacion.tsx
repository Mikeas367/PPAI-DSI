import { useEffect, useState } from "react";
import type { OrdenDeInspeccion } from "../../models";
import { obtenerOrdenes } from "../../services";
export const PantallaCierreDeOrdenDeInspeccion = () => {
    const [ordenes, setOrdenes] = useState<OrdenDeInspeccion[]>()
    useEffect(()=>{
        const opcionCerrarOrdenInspeccion = async () => {
            try {
                const response = await obtenerOrdenes();
                setOrdenes(response.data)
            } catch (error) {
                window.alert(error)
            }
        }
        opcionCerrarOrdenInspeccion()
    },[])
    return(
        <div>
          <h2 className="text-center">Lista de Ordenes</h2>
          <table className="table table-hover table-bordered">
            <thead>
              <tr>
                <th>Numero De Orden</th>
                <th>fechaHoraCierre</th>
                <th>Estacion Sismologica</th>
                <th>Identificador de Sismografo</th>
              </tr>
            </thead>
            <tbody>
              {ordenes?.map((o) => {
                return (
                  <tr key={o.numeroOrden}>
                    <td>{o.numeroOrden}</td>
                    <td>{o.fechaHoraFinalizacion}</td>
                    <td>{o.nombreEstacionSismologica}</td>
                    <td>{o.identificadorSismografo}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
    )
}