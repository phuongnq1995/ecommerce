import React, { useState, useEffect } from 'react';
import { useForm, Controller  } from "react-hook-form";
import { useHistory } from "react-router-dom";
import { Container, Button, ButtonToolbar, ButtonGroup, Form, Row, Col } from 'react-bootstrap';
import Select, { OnChangeValue } from 'react-select'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import productApi from '../../../services/product.api';
import { ICategoryTree } from '../category/category';
import { IProduct } from '../../../shared/model/product/product.model';
import { IImage } from '../../../shared/model/product/image.model'
import { IOption } from '../../../shared/model/common/option.model';
import UploadFile from '../../../components/Common/UploadFile';

interface FormData {
    name: string;
    code: string;
    description: string;
    categoryId: string;
    price: number;
    imageIds: string[];
};

const ProductUpdate: React.FC = () => {

    const history = useHistory();

    const [product, setProduct] = useState<IProduct>();
    const [categories, setCategories] = useState<ICategoryTree[]>([]);

    const { control, register, setValue, handleSubmit, formState: { errors } } = useForm<FormData>();
    const onSubmit = handleSubmit(async data => {
        console.log(data);

        const response = await newProduct(data);

        const responseData = response.data;

        history.push("/product/" + responseData.id);
        // Redirect here
    });

    useEffect(() => {
        loadCategory();
    }, []);

    const loadCategory = async function () {
        const response = await productApi.get("/categories")
        setCategories(response.data);
    }

    const newProduct = async function (product: IProduct) {
        return await productApi.post("/products", product);;
    }

    const categoryOptions = categories.map(category => ({
        label: category.name,
        options: category.subCategories ? category.subCategories.map(sub => ({label: sub.name, value: sub.id})) : []
    }));

    return (
        <Container>
            <Row className="justify-content-center">
                <Col md="8">
                    <h2 id="gatewayApp.blogBlog.home.createOrEditLabel" data-cy="BlogCreateUpdateHeading">
                        Create or edit a Product
                    </h2>
                </Col>
            </Row>
            <Row className="justify-content-center mt-3">
                <Col md="8">
                    <Form onSubmit={onSubmit}>
                        <Form.Group className="mb-3" >
                            <Form.Label>Name</Form.Label>
                            <Form.Control {...register("name")} type="text" placeholder="Enter name" />
                        </Form.Group>
                        <Form.Group className="mb-3" >
                            <Form.Label>Code</Form.Label>
                            <Form.Control {...register("code")} type="text" placeholder="Enter code" />
                        </Form.Group>
                        <Form.Group className="mb-3" >
                            <Form.Label>Description</Form.Label>
                            <Form.Control {...register("description")} as="textarea" placeholder="Description" rows={3} />
                        </Form.Group>
                        <Form.Group className="mb-3" >
                            <Form.Label>Price</Form.Label>
                            <Form.Control {...register("price")} type="number" min={0} step={0.02} />
                        </Form.Group>
                        <Form.Group className="mb-3" >
                            <Form.Label>Category</Form.Label>
                            <Controller
                                control={control}
                                defaultValue={""}
                                name="categoryId"
                                render={({ field: {onChange}}) => (
                                    <Select
                                        className="mb-3"
                                        options={categoryOptions}
                                        name="categoryId"
                                        isMulti={false}
                                        onChange={(val: OnChangeValue<IOption, false>) => onChange(val.value)} />
                                )}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" >
                        <Form.Label>Images</Form.Label>
                        <Controller
                                control={control}
                                defaultValue={[]}
                                name="imageIds"
                                render={({ field: {onChange}}) => (
                                    <UploadFile
                                        name="imageIds"
                                        onChange={(data: IImage[]) => onChange(data.map(image => image.id))} />
                                )}
                            />
                        
                        </Form.Group>
                        <Button variant="primary" type="submit">
                            Submit
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default ProductUpdate;
