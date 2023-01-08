import React from "react";
import { Card } from "react-bootstrap";
import { Link } from 'react-router-dom';
import Col from 'react-bootstrap/Col';
import "../offerStyle.css";

function Offer (props) {
    return (
      <Card className="offer" style={{ width: '18rem'}}>
        <Card.Img id="img" variant="top" src={`data:image/jpeg;base64,${props.offer.photos}`} />
        <Card.Body>
            <Card.Title>
              <Col>
               {props.offer.title}
              </Col>
              <Col>
                  <strong>{props.offer.price}</strong>
                  <small>  zł</small>
              </Col>
            </Card.Title>
            <Card.Text>
              {props.offer.tags.map((tag, index) => (
                <label key = {tag.id} style={{marginRight: 5}}>#{tag.name}</label>
              ))}
            </Card.Text>

            <Link className="btn btn-primary" to={{
              pathname: "/offerDetails",
              state: {
                offer: props.offer,
              },
            }}>
              Zobacz szczegóły
            </Link>
            <p></p>
            <Link className="btn btn-secondary" hidden={props.offer.userLogin!==props.currentUser} to={{
              pathname: "/editOffer",
              state: {
                offer: props.offer,
              },
            }}>
              Edytuj
                </Link>
        </Card.Body>
      </Card>
    )
}

export default Offer;