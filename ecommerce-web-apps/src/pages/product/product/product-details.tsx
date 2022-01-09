import React, { useState, useEffect } from 'react';
import { useForm, Controller } from "react-hook-form";
import { Link, RouteComponentProps } from 'react-router-dom';
import { Container, Button, Image, ButtonGroup, Form, Row, Col } from 'react-bootstrap';
import Select, { OnChangeValue } from 'react-select'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import productApi from '../../../services/product.api';
import { ICategoryTree } from '../category/category';
import { IProduct } from '../../../shared/model/product/product.model';
import { IPrice } from '../../../shared/model/product/price.model';
import { IImage } from '../../../shared/model/product/image.model'
import { IOption } from '../../../shared/model/common/option.model';
import UploadFile from '../../../components/Common/UploadFile';

interface IProductDetails extends IProduct {
    historyPrices?: IPrice[] | null;
}

const ProductDetails = (props: RouteComponentProps<{ id: string }>) => {

    const [product, setProduct] = useState<IProductDetails>();

    useEffect(() => {
        loadProduct(props.match.params.id);
    }, []);

    const loadProduct = async function (id: string) {
        const response = await productApi.get("/products/" + id)
        setProduct(response.data);
    }

    return (
        <Container>
            <Row className="justify-content-center mt-3">
                <Col md="8">
                    <h2 id="gatewayApp.blogBlog.home.createOrEditLabel" data-cy="BlogCreateUpdateHeading">
                        Product details
                    </h2>
                </Col>
            </Row>
            {
                product ? (
                    <Row className="justify-content-center mt-3">
                        <Col md="4">
                            <Image
                                rounded={true}
                                fluid={true}
                                height={300}
                                width={300}
                                src={product.images || product.images.length > 0 ? product.images[0].fileDownloadUri : ""} />
                        </Col>
                        <Col md="6">
                            <Row>
                                <div>{product.name}</div>
                                <p></p>
                                <div>${product.price}</div>
                                <div>
                                    <b>About this item</b>
                                    <p>{product.description}</p>
                                </div>
                            </Row>
                        </Col>
                    </Row>
                ) : <></>
            }
        </Container>
    );
}

export default ProductDetails;
