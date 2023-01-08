import React, { Component } from "react";
import UserService from "../services/user.service";
import OfferList from "./OfferList";

export default class MainPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: "",

      offer: {
        id: null, 
        title: '', 
        price: null, 
        details: '',
        photos: ''
      }
    };

    this.showOfferDetails = this.showOfferDetails.bind(this);
  }

  showOfferDetails(data){
    this.setState({offer: data});
    // this.state.offer = data;
  }

  componentDidMount() {
    UserService.getPublicContent().then(
      response => {
        this.setState({
          content: response.data
        });
      },
      error => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });
      }
    );
  }

  render() {
    return (
      <div className="container">
        <header className="jumbotron">
          <h3>{this.state.content}</h3>
        </header>

        <OfferList showOfferDetails={this.showOfferDetails}/>

      </div>
    );
  }
}