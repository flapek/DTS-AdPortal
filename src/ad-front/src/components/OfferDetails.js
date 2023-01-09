import React, {useEffect, useState} from "react";
import Container from 'react-bootstrap/Container'
import Axios from "axios";
import CommentList from '../components/CommentList'
import OfferMainDetails from "./OfferMainDetails";

const OfferDetails = props => {
    const offerData = {
        id: null,
        title: '',
        price: null,
        details: '',
        photos: '',
        tags: [],
        date: '',
        lat: null,
        lon: null,
        newPhoto: false
    }

    const [offer, setOffer] = useState(offerData);
    
    useEffect(() => {
        const fetchOffer = async () => {

            const result = await Axios.get("http://localhost:8082/offer/" + props.location.state.offer.id);
            setOffer(result.data);
        };

        fetchOffer();
        },[]);

    return (
      <Container>
        <OfferMainDetails offer={offer}/>
          {offer.id!=null ? <CommentList offer={offer}/> : <p>Loading comments...</p>}
      </Container>
    )
}

export default OfferDetails
