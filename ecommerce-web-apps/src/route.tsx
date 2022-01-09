import React from 'react';
import { Switch, Route } from 'react-router-dom'
import Home from './pages/Home';
import Entities from './pages/';

const Routes: React.FC = () => {
    return (
        <Switch>
            <Route path="/home" exact component={Home} />
            <Route path="/" component={Entities} />
        </Switch>
    );
}

export default Routes;
