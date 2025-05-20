
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Menu, PantallaCierreDeOrdenDeInspeccion, FormCancelacion } from "./components";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Menu/>}></Route>
          <Route path="/cierre-de-inspeccion" element={<PantallaCierreDeOrdenDeInspeccion/>}></Route>
          <Route path="cancelar/:numeroOrden" element={<FormCancelacion/>}></Route>
        </Routes>
      </Router>
    </>
  )
}

export default App
