import React from "react";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { CategoryList } from "./features/categories/CategoryList";

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/">
          <CategoryList />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
