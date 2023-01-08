import React, { Component } from "react";
import { Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import AuthService from "./services/auth.service";
import Login from "./components/login.component";
import Register from "./components/register.component";
import MainPage from "./components/MainPage.component";
import Profile from "./components/profile.component";
import BoardUser from "./components/board-user.component";
import BoardModerator from "./components/board-moderator.component";
import BoardAdmin from "./components/board-admin.component";
import OfferDetails from "./components/OfferDetails";
import MyAds from "./components/MyOffers";
import EditOffer from "./components/EditOffer";
import {Nav, Navbar} from "react-bootstrap";
import Container from "react-bootstrap/Container";

class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      showModeratorBoard: false,
      showAdminBoard: false,
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
        showAdminBoard: user.roles.includes("ROLE_ADMIN"),
      });
    }
  }

  logOut() {
    AuthService.logout();
  }

  render() {
    const { currentUser, showModeratorBoard, showAdminBoard } = this.state;

    return (
      <div>
        <Navbar bg="dark" variant="dark" expand="lg" sticky="top">
          <Container>
            <Navbar.Brand href="/">Portal ogłoszeniowy</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="mr-auto">
                <Nav.Link href="/home">Ogłoszenia</Nav.Link>
                {showModeratorBoard && (
                    <Nav.Link href="/mod">Moderator Board</Nav.Link>
                    )}
                {showAdminBoard && (
                    <Nav.Link href="/admin">Admin Board</Nav.Link>
                )}
                {currentUser && (
                    <div>
                      <Nav.Link href="/addAdv">Dodaj ogłoszenie</Nav.Link>
                    </div>
                )}
                {currentUser && (
                    <Nav.Link href="/myAds">Moje ogłoszenia</Nav.Link>
                )}
              </Nav>
              <Nav className="ml-auto">
                {currentUser ? (<div></div>) :(<Nav.Link href="/register">Rejestracja</Nav.Link>)}
                {currentUser && (
                    <Nav.Link href="/profile">{currentUser.username}</Nav.Link>
                )}
                {currentUser ? (
                    <Nav.Link href="/login" onClick={this.logOut} >Wyloguj się</Nav.Link>
                ) : (<Nav.Link href="/login">Logowanie</Nav.Link>)}
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>

        <div className="container mt-3">
          <Routes>
            <Route exact path="/" element={<MainPage />} />
            <Route exact path="/home" element={<MainPage />} />
            <Route exact path="/login" element={<Login />} />
            <Route exact path="/register" element={<Register />} />
            <Route exact path="/profile" element={<Profile />} />
            <Route path="/addAdv" element={<BoardUser />} />
            <Route path="/mod" element={<BoardModerator />} />
            <Route path="/admin" element={<BoardAdmin />} />
            <Route path="/offerDetails" element={<OfferDetails />}/>
            <Route path="/myAds" element={<MyAds />}/>
            <Route path="/editOffer" element={<EditOffer />}/>
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;
