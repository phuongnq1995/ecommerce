import React from 'react';
import { useAccordionButton, Button, Stack, } from 'react-bootstrap'

interface ICustomToggleProps {
    name: string;
    children: React.ReactElement;
    eventKey: string;
}

const CustomToggle: React.FC<ICustomToggleProps> = ({ name, children, eventKey }) => {
    const decoratedOnClick = useAccordionButton(eventKey, () =>
        console.log('totally custom!'),
    );

    return (
        <Stack direction="horizontal" gap={3}>
            <Button variant="light" className="acordion-button-left" onClick={decoratedOnClick} >
                {name}
            </Button>
            {children}
        </Stack>
    );
}

export default CustomToggle;