import React from "react";
import Card from 'react-bootstrap/Card'
import { Container } from "react-bootstrap";
import './Offers.css'

const OfferDesctiption = props => {

    return(
        <Container className="cards">
            <Card style={{ width: '36rem'}}> 
            <Card.Body>
            <Card.Text >
                {props.description}
            </Card.Text>
            </Card.Body>
            </Card>
        </Container>
    )
}

export default OfferDesctiption