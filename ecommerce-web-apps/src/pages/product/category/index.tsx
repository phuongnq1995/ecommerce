import React from 'react';
import { Switch, Route, RouteComponentProps } from 'react-router-dom'
import Category from './category';

const Routes = (props: RouteComponentProps<{ url: string }>) => {

    return (
        <Switch>
            <Route path={`${props.match.url}`} component={Category} />
        </Switch>
    );
}

export default Routes;
