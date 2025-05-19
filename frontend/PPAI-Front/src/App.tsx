
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Menu, PantallaCierreDeOrdenDeInspeccion } from "./components";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Menu/>}></Route>
          <Route path="/cierre-de-inspeccion" element={<PantallaCierreDeOrdenDeInspeccion/>}></Route>
        </Routes>
      </Router>
    </>
  )
}

export default App
