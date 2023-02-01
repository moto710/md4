const page = {
    urls: {
        getAllProduct: AppBase.API_PRODUCT,
        createProduct: AppBase.API_CREATE
    },
    elements: {},
    commands: {}
}

page.elements.btnCreateProduct = $("#btnCreateProduct");
page.elements.btnShowCreateModal = $("#btnShowCreateModal");

page.elements.createProductModal = $("#createProductModal");

page.elements.frmCreateProduct = $("#frmCreateProduct");

page.elements.proNameCre = $("#proNameCre");
page.elements.proPriceCre = $("#proPriceCre");
page.elements.proQuanCre = $("#proQuanCre");
page.elements.supplierCre = $("#supplierCre");

page.elements.btnShowCreateModal.on("click", () => {
    page.elements.createProductModal.modal("show");
})

page.elements.createProductModal.on("hidden.bs.modal", () => {
    page.elements.frmCreateProduct.reset();
    page.elements.frmCreateProduct.validate().resetForm();
})

page.elements.btnCreateProduct.on("click", () => {
    page.elements.frmCreateProduct.trigger("submit");
})

// page.elements.btnCreateProduct.on("click", () => {
$("#btnCreateProduct").on("click", () => {
    console.log(1)
    const productDTO = {
        name: page.elements.proNameCre.val(),
        price: page.elements.proPriceCre.val(),
        quantity: page.elements.proQuanCre.val(),
        supplierDTO : {
            name: page.elements.supplierCre.val()
        }
    }
    console.log(productDTO)
    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: page.urls.createProduct,
        data: JSON.stringify(productDTO)
    })
        .done((data) => {
            AppBase.Notify.successAlert("Create product success!");
            console.log(data)
        })
        .fail(() => {
            AppBase.Notify.errorAlert("Create product fail!");
        })

})