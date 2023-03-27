import React from "react";
import {domainGetAllProducts} from "../../domain";

class ProductList extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            products: []
        }
    }

    componentDidMount (){
        fetch(`${domainGetAllProducts}`)
            .then(response => response.json())
            .then(data => this.setState({
                products: data
            }))
    }
    render() {
        const {products} = this.state;
        const productList = products.map((product) => {
            return (
                <tr>
                    <td>product.id</td>
                    <td>product.name</td>
                    <td>product.name</td>
                    <td>product.price</td>
                    <td>product.description</td>
                </tr>
            )
        })
        return(
            <div className="container mt-3">
                <h2>Product List</h2>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Image</th>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    {productList}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default ProductList;