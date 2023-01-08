import React, {useEffect, useState} from "react";
import Axios from "axios";
import Offer from '../components/Offer'
import './Offers.css'
import Filter from "./Filter";
import authHeader from '../services/auth-header'
import AuthService from "../services/auth.service";
import {Pagination} from "react-bootstrap";


const OfferList = props => {
  const [offers, setOffers] = useState([]);
  const [ currentUser, setCurrentUser ] = useState();
  const [pagesCount, setPagesCount] = useState()

  const fetchOffers = async (filterOptions) => {
    const user = AuthService.getCurrentUser()
    if (user)
      setCurrentUser(user.username)
    else
      setCurrentUser('')
    const formData = new FormData();
    formData.append('tags', filterOptions.tags);
    formData.append('sort', filterOptions.sort);
    formData.append('pageNumber', filterOptions.pageNumber);
    const {data} = await Axios.post(
        "http://localhost:8082/filtered_offers", filterOptions, {headers: authHeader()}
    );
    const offers = data.offers;
    setOffers(offers);
    setPagesCount(data.pagesCount)
  };

  const getFilters = filters => {
    let sort = '';
    if (filters.sort === 'Najdroższe') {
      sort = 'SORT_BY_PRICE_DESC';
    } else if (filters.sort === 'Najtańsze') {
      sort = 'SORT_BY_PRICE_ASC';
    } else if (filters.sort === 'Alfabetycznie A-Z') {
      sort = 'SORT_BY_TITLE_ASC';
    } else if (filters.sort === 'Alfabetycznie Z-A') {
      sort = 'SORT_BY_TITLE_DESC';
    } else if (filters.sort === 'Najnowsze') {
      sort = 'SORT_BY_DATE_DESC';
    } else if (filters.sort === 'Najstarsze') {
      sort = 'SORT_BY_DATE_ASC';
    } else {
      sort = 'SORT_BY_DATE_DESC';
    }
    const filterOptions = {
      tags: filters.tags,
      sort: sort,
      pageNumber: 0
    }
    fetchOffers(filterOptions);
  }

  useEffect(() => {
    const filterOptions = {
      tags: [],
      sort: 'SORT_BY_PRICE_DESC',
      pageNumber: 0
    }
    fetchOffers(filterOptions);
  }, []);

  let items = [];

  const clicked = event => {
    console.log(event.target.text);
    fetchOffers();

  }

  for (let number = 1; number <= pagesCount; number++) {
    items.push(
        <Pagination.Item
            key={number}
            onClick={(event) => clicked(event)}>
          {number}
        </Pagination.Item>
    );
  }

  return (
      <div className="container">
        <Filter getFilters={getFilters}/>
        <div>
          <div className="cards">
            {offers.map((offer) => (
                <Offer key={offer.id}
                       offer={offer}
                       showOfferDetails={props.showOfferDetails}
                       currentUser={currentUser}
                />
            ))}
          </div>
          <Pagination style={{justifyContent: "center"}}>
            {items}
          </Pagination>
        </div>
      </div>

  );
}

export default OfferList;
