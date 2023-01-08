import React, { useState } from "react";
import { NavLink, withRouter } from "react-router-dom";
import '../components/Navbar.css'
import {Nav} from "react-bootstrap";

const Navbar = ({ history }) => {
  const [isOpen, setOpen] = useState(false);

  const isAuth = !!localStorage.getItem("token");

  const loginUser = () => {
    localStorage.setItem("token", "some-login-token");
    history.push("/profile/Vijit");
  };

  const logoutUser = () => {
    localStorage.removeItem("token");
    history.push("/");
  };

  return (
      <Nav
          activeKey="/home"
          onSelect={(selectedKey) => alert(`selected ${selectedKey}`)}
      >
        <Nav.Item>
          <Nav.Link href="/home">Active</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey="link-1">Link</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey="link-2">Link</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey="disabled" disabled>
            Disabled
          </Nav.Link>
        </Nav.Item>
      </Nav>


    // <nav
    //   className="navbar is-primary"
    //   role="navigation"
    //   aria-label="main navigation"
    // >
    //   <div className="container">
    //     <div className="navbar-brand">
    //       <a
    //         role="button"
    //         className={`navbar-burger burger ${isOpen && "is-active"}`}
    //         aria-label="menu"
    //         aria-expanded="false"
    //         onClick={() => setOpen(!isOpen)}
    //       >
    //         <span aria-hidden="true"></span>
    //         <span aria-hidden="true"></span>
    //         <span aria-hidden="true"></span>
    //       </a>
    //     </div>
    //
    //     <div className={`navbar-menu ${isOpen && "is-active"}`}>
    //       <div className="navbar-start">
    //         <NavLink
    //           className="navbar-item"
    //           activeClassName="is-active"
    //           to="/"
    //           exact
    //         >
    //           Home
    //         </NavLink>
    //
    //         <NavLink
    //           className="navbar-item"
    //           activeClassName="is-active"
    //           to="/offers"
    //         >
    //           Offers
    //         </NavLink>
    //
    //         <NavLink
    //           className="navbar-item"
    //           activeClassName="is-active"
    //           to="/addOffer"
    //         >
    //           AddOffer
    //         </NavLink>
    //       </div>
    //
    //       <div className="navbar-end">
    //         <div className="navbar-item">
    //           <div className="buttons">
    //             {!isAuth ? (
    //               <button className="button is-white" onClick={loginUser}>
    //                 Log in
    //               </button>
    //             ) : (
    //               <button className="button is-black" onClick={logoutUser}>
    //                 Log out
    //               </button>
    //             )}
    //           </div>
    //         </div>
    //       </div>
    //     </div>
    //   </div>
    // </nav>
  );
};

export default withRouter(Navbar);