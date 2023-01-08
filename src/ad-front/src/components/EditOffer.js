import React, {useState} from "react";
import Axios from "axios";
import Container from 'react-bootstrap/Container'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Tags from '../components/Tags'
import authHeader from '../services/auth-header'
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";


const EditOffer = props => {
    const [selectedFiles, setSelectedFiles] = useState(undefined);
    const [tags, setTags] = useState([])
    const [offer, setOffer] = useState(props.location.state.offer)
    const [disabled, setDisabled] = useState(true);


    const handleInputChange = event => {
        const {name, value} = event.target
        setOffer({...offer, [name]: value})
    }

    const handleCheckboxChange = event => {
        setDisabled(!event.target.checked)
        const {name, value} = event.target
        setOffer({...offer, [name]: value})
    }

    const handleSubmit = data => {
        data.preventDefault()
        const formData = new FormData()
        if (!disabled) {
            formData.append('file', selectedFiles[0])
        }
        console.log(offer)
        formData.append('title', offer.title)
        formData.append('price', offer.price)
        formData.append('details', offer.details)
        formData.append('tags', tags)
        formData.append('id', props.location.state.offer.id)
        formData.append('newPhoto', !disabled)
        sendOffer(formData)

    }

    const sendOffer = offer => {
        if (offer.get('newPhoto') && !disabled) {
            Axios
                .put('http://localhost:8082/updateOfferWithPhoto', offer, {headers: authHeader()})
        } else {
            Axios
                .put('http://localhost:8082/updateOffer', offer, {headers: authHeader()})
        }
    }


    const onDelete = () => {
        Axios
            .delete('http://localhost:8082/delete-offer/' + props.location.state.offer.id, {headers: authHeader()})
            .then(response => {
                console.log(response)
                window.location.replace("http://localhost:3000/myAds")
            });
    }


    const checkFileSize = (event) => {
        let files = event.target.files
        let size = 1048576
        let err = "";
        for (var x = 0; x < files.length; x++) {
            if (files[x].size > size) {
                err += 'Plik' + files[x].type + ' jest za duży. Maksymalny rozmiar pliku to 1048576 bajtów\n';
            }
        }
        ;
        if (err !== '') {
            event.target.value = null
            alert(err)
            return false
        }

        return true;
    }

    const checkMimeType = (event) => {
        let files = event.target.files
        let err = ''
        const types = ['image/png', 'image/jpeg', 'image/gif']
        for (let x = 0; x < files.length; x++) {
            if (types.every(type => files[x].type !== type)) {
                err += files[x].type + ' ma niedopuszczalny format. Akceptowalne tylko "png", "jpeg", "gif"\n';
                alert(err)
            }
        }
        ;

        if (err !== '') {
            event.target.value = null
            return false;
        }
        return true;

    }

    const onChangeHandler = e => {
        if (checkFileSize(e) && checkMimeType(e))
            setSelectedFiles(e.target.files);
    }

    const addTag = data => {
        setTags(data)
    }

    return (
        <Container>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="formFile" className="mb-3">
                    <Form.Label>Add photo</Form.Label>
                    <Form.Control type="file" name="photos" accept="image/png, image/gif, image/jpeg"
                                  onChange={onChangeHandler} disabled={disabled} />
                </Form.Group>
                <Form.Check controlId="newPhoto">
                    <Form.Check.Input type="checkbox" name='newPhoto' onChange={handleCheckboxChange}
                                      value={offer.newPhoto}/>
                    <Form.Check.Label>Zmień zdjęcie</Form.Check.Label>
                </Form.Check>
                <Form.Group controlId="offerTitle">
                    <Form.Label>Tytuł</Form.Label>
                    <Form.Control type="text" placeholder="Podaj tytuł" name='title' onChange={handleInputChange}
                                  value={offer.title}/>
                </Form.Group>
                <Form.Group controlId="offerPrice">
                    <Form.Label>Cena w zł</Form.Label>
                    <Form.Control type="number" placeholder="Podaj cenę" name="price" onChange={handleInputChange}
                                  value={offer.price}/>
                </Form.Group>
                <Form.Group controlId="offerDetails">
                    <Form.Label>Opis</Form.Label>
                    <Form.Control type="text" placeholder="Podaj opis" name="details" onChange={handleInputChange}
                                  value={offer.details}/>
                </Form.Group>
                <Form.Group>
                    <Tags
                        addTag={addTag}
                        value={props.location.state.offer.tags}
                    />
                </Form.Group>
                <Form.Group>
                    <Row>
                        <Col xs={2}><Button variant="primary" type="submit">Zaipisz zmiany</Button></Col>
                        <Col><Button className="btn-danger" onClick={onDelete}>Usuń ofertę</Button></Col>
                    </Row>
                </Form.Group>
            </Form>
        </Container>
    )
};

export default EditOffer