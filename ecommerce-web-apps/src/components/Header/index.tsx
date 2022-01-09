import React from 'react';
import { Link } from 'react-router-dom'
import { Navbar, Container, Nav, NavDropdown, Form, FormControl, Button } from 'react-bootstrap';

const Header: React.FC = () => {
    return (
        <Navbar bg="light" expand="lg">
            <Container fluid>
                <Navbar.Brand href="/">Admin dashboard</Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll" />
                <Navbar.Collapse id="navbarScroll">
                    <Nav
                        className="me-auto my-2 my-lg-0"
                        style={{ maxHeight: '100px' }}
                    >
                        <Nav.Link href="/">Home</Nav.Link>
                        <Nav.Link href="#action2">About</Nav.Link>
                        <NavDropdown title="Entities" id="navbarScrollingDropdown">
                            <NavDropdown.Item as={Link} to="/category">Category</NavDropdown.Item>
                            <NavDropdown.Item as={Link} to="/product">Product</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                    <Form className="d-flex">
                        <FormControl
                            type="search"
                            placeholder="Search"
                            className="me-2"
                            aria-label="Search"
                        />
                        <Button variant="outline-success">Search</Button>
                    </Form>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header;
