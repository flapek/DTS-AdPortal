import React, {useState} from "react";
import Card from 'react-bootstrap/Card'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Axios from "axios";
import Form from 'react-bootstrap/Form'
import {Button} from "react-bootstrap";
import {useForm} from "react-hook-form";
import authHeader from '../services/auth-header'
import './Offers.css'

const AddComment = props => {

    const {register, handleSubmit} = useForm();
    const [inputValue, setInputValue] = useState('');

    const onSubmit = data => {

        const comment = {
            content: data.content,
            offerId: props.offer.id,
            username: props.username
        }

        Axios
            .post('http://localhost:8082/offer/addComment', comment, {headers: authHeader()})
            .then((response) => {
                props.updateComments(response.data)
            })
            .catch(error => {
                console.log(error)
            });

        // props.getComments();

        setInputValue('');
    }

    function handleChange(e) {
        setInputValue(e.target.value);
    }

    return (
        <Form onSubmit={handleSubmit(onSubmit)}>
            <Card>
                <Card.Body>
                    <Row>
                        <Col  md="10">
                            <Card.Text>
                                <Form.Control type="text" placeholder="Wpisz komentarz" {...register("content",
                                    {required: true})} value={inputValue} onChange={handleChange}/>
                            </Card.Text>
                        </Col>
                        <Col  md="2">
                            <Button type="submit" className="btn-primary btn">Dodaj komentarz</Button>
                        </Col>
                    </Row>
                </Card.Body>
            </Card>
        </Form>

    )
}


export default AddComment