import React from 'react';
import './App.css';
import { Route,Switch}  from 'react-router-dom';
import {BrowserRouter } from 'react-router-dom';
import LoginScreen from "./LoginScreen/loginScreen";
import AgentScreen from "./MainScreens/agentScreen";
import ManagerScreen from "./MainScreens/managerScreen";
function App() {
    return (
    <BrowserRouter>
      <Switch>
          <Route exact path="/" >
              <LoginScreen/>
          </Route>
          <Route path="/agentDashboard">
              <AgentScreen/>
          </Route>
          <Route path="/managerDashboard">
              <ManagerScreen/>
          </Route>
      </Switch>
    </BrowserRouter>
  );
}

export default App;
