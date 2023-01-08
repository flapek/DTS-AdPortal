import React from "react";
import Card from 'react-bootstrap/Card'
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Axios from "axios";
import authHeader from "../services/auth-header";
import MapToView from "./map/MapToView";


const OfferMainDetails = props => {

    const onClick = e => {

        console.log(props)

        Axios
            .post('http://localhost:8082/bought', props.offer, { headers: authHeader() })
            .catch(error => {console.log(error)});
    }

    return(
        <Card>
            <Card.Img className="photo" variant="top" src={`data:image/jpeg;base64,${props.offer.photos}`} height="300px" width="300px" />
            <Card.Body>
                <Row><Col><Card.Title>{props.offer.title}</Card.Title></Col></Row>
                <Row>
                    <Col sm={10}><Card.Subtitle>Cena: {props.offer.price} zł</Card.Subtitle></Col>
                    <Col><Button variant="primary" size="lg" onClick={onClick}>Kup teraz</Button></Col>
                </Row>
                <Row><Col><Card.Subtitle>Tagi:{props.offer.tags.map((tag) => (
                    <span key = {tag.id}> #{tag.name}</span>
                ))}</Card.Subtitle></Col></Row>

                <Row><Col><Card.Subtitle>Szczegóły oferty</Card.Subtitle></Col></Row>
                <Row><Col><Card.Text>{props.offer.details}</Card.Text></Col></Row>
                {props.offer.status===1 ? <Row><Col><Card.Text>Przygotowane do wysyłki!</Card.Text></Col></Row>
                    : <div></div>}
                {props.offer.status===2 ? <Row><Col><Card.Text>Wysłano!</Card.Text></Col></Row>
                    : <div></div>}
                <MapToView offer={props.offer}/>
            </Card.Body>
        </Card>
    )
}

export default OfferMainDetails
