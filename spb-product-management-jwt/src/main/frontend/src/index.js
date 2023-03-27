import React from 'react';
import ReactDOM from 'react-dom/client';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Login from "./components/authentication/Login";
import Signup from "./components/authentication/Signup";
import Homepage from "./components/homepage/Homepage";
import ProductList from "./components/product/ProductList";

const router = createBrowserRouter([
    {
        path: "/",
        element:<Homepage />
    },
    {
        path: "/login",
        element:<Login />
    },
    {
        path: "/signup",
        element:<Signup />
    },
    {
        path: "/products",
        element:<ProductList />
    }
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <RouterProvider router={router}>
            <App/>
        </RouterProvider >
    </React.StrictMode>
);

reportWebVitals();
