import React, { useState } from "react";
import Axios from "axios";
import Container from 'react-bootstrap/Container'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import { useForm } from "react-hook-form";
import Tags from '../components/Tags'
import authHeader from '../services/auth-header'
import MapToEdit from "./map/Map";

const AddOffer = () => {
    const { register, handleSubmit } = useForm();
    const [selectedFiles, setSelectedFiles] = useState([]);
    const [tags, setTags] = useState([]);
    const [position, setPosition] = useState({
        lat: 52.227716,
        lng: 21.002394
    });

    const onSubmit = data => {
        const formData = new FormData()
        formData.append('file', selectedFiles[0])
        formData.append('title', data.title)
        formData.append('price', data.price)
        formData.append('details', data.details)
        setTags(Array.from(new Set(tags)));
        formData.append('tags', Array.from(new Set(tags)));
        formData.append('lat', position.lat);
        formData.append('lon', position.lon);
        Axios.post('http://localhost:8082/addOffer', formData, { headers: authHeader() }).catch((error)=>{
            console.log(error);
        });

        window.location.replace("http://localhost:3000/myAds");
    }

    const checkFileSize=(event)=>{
        let files = event.target.files
        let size = 1048576 
        let err = ""; 
        for(var x = 0; x<files.length; x++) {
        if (files[x].size > size) {
        err += 'Plik'+files[x].type+' jest za duży. Maksymalny rozmiar pliku to 1048576 bajtów\n';
        }
        };
        if (err !== '') {
            event.target.value = null
            alert(err)
            return false
        }

        return true;
    }

    const checkMimeType=(event)=>{
        let files = event.target.files 
        let err = ''
    const types = ['image/png', 'image/jpeg', 'image/gif']
        for(let x = 0; x<files.length; x++) {
            if (types.every(type => files[x].type !== type)) {
            err += files[x].type+' ma niedopuszczalny format. Akceptowalne tylko "png", "jpeg", "gif"\n';
            alert(err)
            }
        }

    if (err !== '') {
            event.target.value = null 
            return false; 
        }
    return true;
    }

    const onChangeHandler = e => {
    if(checkFileSize(e)&&checkMimeType(e))
        setSelectedFiles(e.target.files);
    }

    const addTag = data => {
        setTags(data);
    }

    const addPosition = data => {
        setPosition(data);
    }

    return (
        <Container>
            <Form onSubmit={handleSubmit(onSubmit)}>
                <Form.Group>
                    <Form.File type="file" name="photos" accept="image/png, image/gif, image/jpeg" onChange={onChangeHandler} />
                </Form.Group>
                <Form.Group controlId="offerTitle">
                    <Form.Label>Tytuł</Form.Label>
                    <Form.Control type="text" placeholder="Podaj tytuł" {...register("title", { required: true })}/>
                </Form.Group>
                <Form.Group controlId="offerPrice">
                    <Form.Label>Cena w zł</Form.Label>
                    <Form.Control type="number" placeholder="Podaj cenę" name="price"  {...register("price", { required: true })}/>
                </Form.Group>
                <Form.Group controlId="offerDetails">
                    <Form.Label>Opis</Form.Label>
                    <Form.Control type="text" placeholder="Podaj opis" name="details"  {...register("details", { required: true })}/>
                </Form.Group>
                <Form.Group>
                <Form.Label>Tagi</Form.Label>
                    <Tags
                        addTag={addTag}
                    />
                </Form.Group>
                <Form.Group>
                    <MapToEdit
                        addPosition={addPosition}
                    />
                </Form.Group>
                <Button variant="primary" type="submit" style={{marginBottom: 10}} size="lg">
                    Dodaj ofertę
                </Button>
            </Form>     
            </Container>
    )
};

export default AddOffer;