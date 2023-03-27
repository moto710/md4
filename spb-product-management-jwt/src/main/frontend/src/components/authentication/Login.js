import React from "react";
import {toast} from "react-toastify";
import {domainLogin} from "../../domain";
import withNavigateHook from "../../withNavigateHook";
import axios from "axios";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user: this.emptyUser
        }
        this.handleOnchangeUsername = this.handleOnchangeUsername.bind(this);
        this.handleOnchangePassword = this.handleOnchangePassword.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
    }

    emptyUser = {
        username: '',
        password: ''
    }

    handleOnchangeUsername = (event) => {
        this.setState({
            username: event.target.value
        })
    }

    handleOnchangePassword = (event) => {
        this.setState({
            password: event.target.value
        })
    }

    async handleLogin () {
        const user = {
            username: this.state.username,
            password: this.state.password
        }
        let data = await axios.post("http://localhost:8080/api/auth/login", user)
        if(data.data != undefined){
            console.log(data);
        }
        // await fetch(`${domainLogin}`, {
        //     method: 'post',
        //     headers: {
        //         'accept': 'application/json',
        //         'content-type': 'application/json'
        //     },
        //     body: JSON.stringify(user)
        // }).then((response) => {
        //     console.log(response)
        //     toast.success('Login success!');
        //     this.props.navigation('/products')
        // })
    }

    render() {
        return (
            <div className="container-sm mt-3">
                <h2>Login</h2>
                <form>
                    <div className="mb-3 mt-3">
                        <label htmlFor="username">Username:</label>
                        <input type="text" className="form-control" id="username" placeholder="Enter username"
                               onChange={event => this.handleOnchangeUsername(event)}/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="pwd">Password:</label>
                        <input type="password" className="form-control" id="pwd" placeholder="Enter password"
                               onChange={event => this.handleOnchangePassword(event)}/>
                    </div>
                    <div className='d-flex justify-content-end'>
                        <button type="button" className="btn btn-secondary"
                                onClick={() => this.handleLogin()}>Login
                        </button>

                        <button type="button" className="btn btn-primary ms-2">Signup</button>
                    </div>
                </form>
            </div>
        )
    }
}

export default withNavigateHook(Login);