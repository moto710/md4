package com.model2.controller;

import com.model2.action.SaveProductAction;
import com.model2.model.Product;
import com.model2.model.ProductForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ControllerServlet", urlPatterns = { "/input-product", "/save-product" })
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1579L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        // execute an action
        String dispatchUrl = null;
        if ("input-product".equals(action)) {
            // no action class, just forward
            dispatchUrl = "/jsp/ProductForm.jsp";
        } else if ("save-product".equals(action)) {
            // create form
            ProductForm productForm = new ProductForm();
            // populate action properties
            productForm.setName(request.getParameter("name"));
            productForm.setDescription(
                    request.getParameter("description"));
            productForm.setPrice(request.getParameter("price"));

            // create model
            Product product = new Product();
            product.setName(productForm.getName());
            product.setDescription(productForm.getDescription());
            try {
                product.setPrice(new BigDecimal(productForm.getPrice()));
            } catch (NumberFormatException e) {
            }
            // execute action method
            SaveProductAction saveProductAction = new SaveProductAction();
            saveProductAction.save(product);

            // store model in a scope variable for the view
            request.setAttribute("product", product);
            dispatchUrl = "/jsp/ProductDetails.jsp";
        }

        if (dispatchUrl != null) {
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}
