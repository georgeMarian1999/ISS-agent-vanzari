import React from 'react';
import './App.css';
import { Route,Switch}  from 'react-router-dom';
import {BrowserRouter } from 'react-router-dom';
import LoginScreen from "./LoginScreen/loginScreen";
import AgentDashboard from "./Screens/agentDashboard/agentDashboard";
import ManagerDashboard from "./Screens/managerDashboard/managerDashboard";
import ProductsScreen from "./Screens/agentDashboard/productsScreen/productsScreen";
import IstoricComenzi from "./Screens/agentDashboard/IstoricsComenziScreen/IstoricComenzi";
import ComenziPrimite from "./Screens/managerDashboard/ComenziPrimiteScreen/comenziPrimite";
function App() {
  return (
      <BrowserRouter>
        <Switch>
          <Route exact path="/" >
            <LoginScreen/>
          </Route>
          <Route path="/products">
            <ProductsScreen/>
          </Route>
          <Route path="/istoricComenzi">
            <IstoricComenzi/>
          </Route>
          <Route path="/agentDashboard">
            <AgentDashboard/>
          </Route>
          <Route path="/managerDashboard">
            <ManagerDashboard/>
          </Route>
          <Route path="/comenziPrimite">
            <ComenziPrimite/>
          </Route>
        </Switch>
      </BrowserRouter>
  );
}

export default App;
