import React from 'react';
import { Switch, Route, RouteComponentProps } from 'react-router-dom'
import Category from './product/category';
import Product from './product/product';

const Routes = (props: RouteComponentProps<{ url: string }>) => {

    return (
        <Switch>
            <Route path={`${props.match.url}category`} component={Category} />
            <Route path={`${props.match.url}product`} component={Product} />
        </Switch>
    );
}

export default Routes;
