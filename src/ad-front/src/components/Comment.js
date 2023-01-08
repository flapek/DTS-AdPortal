import React from "react";
import {Button, Card} from "react-bootstrap";
import Row from "react-bootstrap/Row";

const Comment = props => {
    function handleClick() {
        props.deleteComment(props.id);
    }

    return (
        <Card>
            <Card.Header>{props.user}</Card.Header>
            <Row style={{margin: 10}}>
                <Card.Body>
                    <Card.Text>
                        {props.content}
                    </Card.Text>
                </Card.Body>
                <Button variant="outline-danger" size={"sm"} hidden={props.user!==props.currentUser}
                        onClick={handleClick} style={{maxHeight: 40}}>
                    Usuń komentarz
                </Button>
            </Row>
        </Card>
    )
}

export default Comment;