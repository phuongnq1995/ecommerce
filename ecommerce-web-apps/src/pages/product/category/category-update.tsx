import React, { useState, useEffect } from 'react';
import { Container, Button, ButtonToolbar, ButtonGroup, Accordion, Card, Stack } from 'react-bootstrap'
import productApi from '../../../services/product.api';

interface ICategory {
    id: number;
    name: string;
    code: string;
    description: string;
    parentId: number;
}

const Category: React.FC = () => {

    return (
        <Container>

        </Container>

    );
}