import React, { useState, useEffect } from 'react';
import { Container, Button, ButtonToolbar, ButtonGroup, Accordion, Card, Stack } from 'react-bootstrap'
import CustomToggle from '../../../components/Common/CustomToggle';
import productApi from '../../../services/product.api';
import { ICategory } from '../../../shared/model/product/category.model'

export interface ICategoryTree extends ICategory  {
    subCategories: ICategoryTree[];
}

const Category: React.FC = () => {

    const [categories, setCategories] = useState<ICategoryTree[]>([]);

    useEffect(() => {
        load();
    }, []);

    const newCategory = async function() { 
        
    }

    const load = async function() {
        const response = await productApi.get("/categories")
        console.log(response.data);
        setCategories(response.data);
    }

    return (
        <Container>
            <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center p-2 mb-3 border-bottom">
                <h1 className="h2">Category</h1>
                <ButtonToolbar className="mb-2 md-md-0">
                    <ButtonGroup className="me-2" aria-label="First group">
                        <Button className="" onClick={newCategory}>New</Button>
                    </ButtonGroup>
                </ButtonToolbar>
            </div>
            {
                categories.map(category => (
                    <Accordion key={category.id} flush>
                        <Card>
                            <Card.Header>
                                <CustomToggle eventKey={category.id.toString()} name={category.name} >
                                    <>
                                        <Button variant="warning">Edit</Button>{' '}
                                        <Button variant="danger">Delete</Button>{' '}
                                    </>
                                </CustomToggle>
                            </Card.Header>
                            <Accordion.Collapse eventKey={category.id.toString()}>
                                <Card.Body>
                                    {
                                        category.subCategories.map(sub => (
                                            <Stack direction="horizontal" className='p-3 border' gap={3} key={sub.id}>
                                                <div className="me-auto">{sub.name}</div>
                                                <Button variant="warning">Edit</Button>{' '}
                                                <Button variant="danger">Delete</Button>{' '}
                                            </Stack>
                                        ))
                                    }
                                </Card.Body>
                            </Accordion.Collapse>
                        </Card>
                    </Accordion>
                ))
            }
        </Container>
    );
}

export default Category;
