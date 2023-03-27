import React from "react";
import {NavLink} from 'react-router-dom';
import './nav.scss';
class Nav extends React.Component {
    render() {
        return (
            <>
                <nav className="navbar navbar-expand-sm bg-primary navbar-dark">
                    <div className="container-fluid d-flex justify-content-between">
                        <div>
                            <ul className="navbar-nav">
                                <li className="nav-item">
                                    <NavLink to='/' end>
                                        <p className="nav-link">Home</p>
                                    </NavLink>
                                </li>
                            </ul>
                        </div>
                        <div>
                            <ul className="navbar-nav">
                                <li className="nav-item">
                                    <NavLink to='/login'>
                                        <p className="nav-link">Login</p>
                                    </NavLink>
                                </li>
                                <li className="nav-item">
                                    <NavLink to='/signup'>
                                        <p className="nav-link">Signup</p>
                                    </NavLink>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>

            </>
        )
    }
}

export default Nav;