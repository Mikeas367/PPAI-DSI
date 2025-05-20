import { useEffect, useState } from "react";
import type { OrdenDeInspeccion } from "../../models";
import { obtenerOrdenes } from "../../services";
import { actualizarEstadoOrden } from "../../services"
import { useNavigate } from "react-router-dom";

// este seria el "MostrarOrdenes" -> si le ponemos ese nombre no le hace justicia a lo que es el componente una lista basicamente
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

 
    //const cerrarOrden = async(numeroOrden: number) => {
    //    await actualizarEstadoOrden(numeroOrden)
    //    const response = await obtenerOrdenes();
    //    console.log("ordenes despues de actulizar: ", ordenes)
    //    setOrdenes(response.data);
    //    alert("EstadoACTUALIZADO")
    //}

    const navigate = useNavigate()
    const cerrar = (numeroOrden: number) => {
      navigate(`/cancelar/${numeroOrden}`)
    }

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
                <th>Opciones</th>
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
                    <td>{
                        <button onClick={() => cerrar(o.numeroOrden)}>Cerrar</button>
                      }
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
    )
}