import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Container, Button, ButtonToolbar, ButtonGroup, Table } from 'react-bootstrap'
import productApi from '../../../services/product.api';
import { IProduct } from '../../../shared/model/product/product.model';

export interface IProductSearchResult {
    id: string;
    score: number;
    content: IProduct
}

const Product = (props: RouteComponentProps<{ url: string }>) => {

    const [products, setProducts] = useState<IProductSearchResult[]>([]);

    useEffect(() => {
        load();
    }, []);

    const load = async function () {
        const response = await productApi.get("/products");
        console.log(response.data);
        setProducts(response.data.searchHits);
    }

    const { match } = props;

    return (
        <Container>
            <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center p-2 mb-3 border-bottom">
                <h1 className="h2">Products</h1>
                <ButtonToolbar className="mb-2 md-md-0">
                    <ButtonGroup className="me-2" aria-label="First group">
                        <Link to={`${match.url}/new`} className='btn btn-primary'>New</Link>
                    </ButtonGroup>
                </ButtonToolbar>
            </div>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Code</th>
                        <th>Price</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        products.map(product => (
                            <tr key={product.id}>
                                <td>
                                    <Link to={`${match.url}/${product.content.id}`}>
                                        {product.content.name}
                                    </Link>
                                </td>
                                <td>{product.content.code}</td>
                                <td>{product.content.price}</td>
                                <td>{product.content.category.name}</td>
                            </tr>
                        ))
                    }
                </tbody>
            </Table>
        </Container>
    );
}

export default Product;
