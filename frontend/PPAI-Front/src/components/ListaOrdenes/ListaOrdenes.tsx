import { useEffect, useState } from "react";
import type { OrdenDeInspeccion } from "../../models";
import { obtenerOrdenes } from "../../services";
import { useNavigate } from "react-router-dom";

// este seria el "MostrarOrdenes" -> si le ponemos ese nombre no le hace justicia a lo que es el componente una lista basicamente
export const PantallaCierreDeOrdenDeInspeccion = () => {
  const [ordenes, setOrdenes] = useState<OrdenDeInspeccion[] | null>(null);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const cargarOrdenes = async () => {
      try {
        const response = await obtenerOrdenes();
        setOrdenes(response.data);
      } catch (error) {
        console.error(error);
        setError("Ocurrió un error al cargar las órdenes.");
      } finally {
        setCargando(false);
      }
    };

    cargarOrdenes();
  }, []);

  const cerrar = (numeroOrden: number) => {
    navigate(`/cancelar/${numeroOrden}`);
  };

  if (cargando) {
    return (
      <div className="text-center mt-5">
        <div className="spinner-border text-primary" role="status"></div>
        <p className="mt-3">Cargando datos...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="alert alert-danger text-center mt-4" role="alert">
        {error}
      </div>
    );
  }

  if (ordenes && ordenes.length === 0) {
    return (
      <div className="alert alert-info text-center mt-4">
        No hay órdenes disponibles para mostrar.
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">Lista de Órdenes</h2>
      <table className="table table-hover table-bordered">
        <thead className="table-light">
          <tr>
            <th>Número de Orden</th>
            <th>Fecha y Hora de Cierre</th>
            <th>Estación Sismológica</th>
            <th>Identificador de Sismógrafo</th>
            <th>Opciones</th>
          </tr>
        </thead>
        <tbody>
          {ordenes?.map((o) => (
            <tr key={o.numeroOrden}>
              <td>{o.numeroOrden}</td>
              <td>{o.fechaHoraFinalizacion ?? "-"}</td>
              <td>{o.nombreEstacionSismologica}</td>
              <td>{o.identificadorSismografo}</td>
              <td>
                <button
                  onClick={() => cerrar(o.numeroOrden)}
                  className="btn btn-outline-danger btn-sm"
                >
                  Cerrar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};