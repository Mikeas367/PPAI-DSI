import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getMotivosTipo } from "../../services/motivosTipo.service";
import type { MotivoTipo } from "../../models/MotivoTipo";
import { actualizarEstadoOrden } from "../../services";
// Asumiendo que tiene { id: number, nombre: string }

export const FormCancelacion = () => {
  const { numeroOrden } = useParams();
  const navigate = useNavigate();

  const [motivos, setMotivos] = useState<MotivoTipo[]>([]);
  const [seleccionados, setSeleccionados] = useState<number[]>([]);
  const [comentarios, setComentarios] = useState<{ [id: number]: string }>({});

  useEffect(() => {
    const obtenerMotivos = async () => {
      const response = await getMotivosTipo();
      console.log(response.data)
      setMotivos(response.data);
    };
    obtenerMotivos();
  }, []);

  const toggleSeleccion = (id: number) => {
    if (seleccionados.includes(id)) {
      setSeleccionados(seleccionados.filter(m => m !== id));
      const nuevosComentarios = { ...comentarios };
      delete nuevosComentarios[id];
      setComentarios(nuevosComentarios);
    } else {
      setSeleccionados([...seleccionados, id]);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const payload = seleccionados.map(id => ({
    idMotivoTipo: id,
    comentario: comentarios[id] || ""
  }));


    console.log("Datos a enviar:", payload);

    // Acá iría tu POST al backend, por ejemplo:
    await actualizarEstadoOrden(Number(numeroOrden), payload)
    // await enviarCancelacionOrden(payload);

    alert("Cancelación enviada");
    navigate("/");
  };

  return (
    <div>
      <h2>Cancelar Orden #{numeroOrden}</h2>
      <form onSubmit={handleSubmit}>
        {motivos.map((m) => (
          <div key={m.id}>
            <label>
              <input
                type="checkbox"
                checked={seleccionados.includes(m.id)}
                onChange={() => toggleSeleccion(m.id)}
              />
              {m.descripcion}
            </label>
            {seleccionados.includes(m.id) && (
              <div>
                <input
                  type="text"
                  placeholder={`Comentario para ${m.descripcion}`}
                  value={comentarios[m.id] || ""}
                  onChange={(e) =>
                    setComentarios({ ...comentarios, [m.id]: e.target.value })
                  }
                />
              </div>
            )}
          </div>
        ))}
        <button type="submit">Enviar</button>
      </form>
    </div>
  );
};
