package com.model2.filter;

import com.model2.action.SaveProductAction;
import com.model2.model.Product;
import com.model2.model.ProductForm;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;

@WebFilter(filterName = "DispatcherFilter", urlPatterns = {"/*"})
public class DispatcherFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        // action processing
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        if ("input-product".equals(action)) {
            // do nothing
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
            product.setDescription(product.getDescription());
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

        // forward to a view
        if (dispatchUrl != null) {
            RequestDispatcher rd = request
                    .getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        } else {
            // let static contents pass
            filterChain.doFilter(request, response);
        }
    }
}
