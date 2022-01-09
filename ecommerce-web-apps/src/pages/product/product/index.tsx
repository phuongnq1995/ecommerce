import React from 'react';
import { Switch, Route, RouteComponentProps } from 'react-router-dom'
import Product from './product';
import ProductUpdate from './product-update';
import ProductDetails from './product-details';

const Routes = (props: RouteComponentProps<{ url: string }>) => {

    return (
        <Switch>
            <Route exact path={`${props.match.path}/new`} component={ProductUpdate} />
            <Route exact path={`${props.match.path}/:id`} component={ProductDetails} />
            <Route path={`${props.match.path}`} component={Product} />
        </Switch>
    );
}

export default Routes;
