import React from "react";
import ReactDOM from "react-dom/client";
import "bootstrap/dist/css/bootstrap.css";
import "primereact/resources/themes/lara-light-indigo/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";

import Home from "./components/homeComponent";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<Home/>);