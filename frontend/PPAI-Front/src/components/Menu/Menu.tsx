import { Link } from 'react-router-dom';
import "./Menu.css";
export const Menu = () => {
  return (
    <>
      <div className="entrada-container">
        <div className="blur-background" />
        <div className="button-container">
          <Link to="/cierre-de-inspeccion">
            <button className="entrada-button">Cerrar Orden De Inspección</button>
          </Link>
        </div>
      </div>
    </>
  );
};
