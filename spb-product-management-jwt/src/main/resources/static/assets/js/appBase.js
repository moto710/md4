class AppBase {
    static DOMAIN = location.origin;

    static API_CITY = this.DOMAIN  + "/api/cities";
    static API_DELETE_CITY = this.API_CITY + "/delete";

    static API_PRODUCT = this.DOMAIN  + "/api/products";
    static API_SUSPENDED_PRODUCT = this.API_PRODUCT + "/suspendedProducts"

    static SERVER_CLOUDINARY = "https://res.cloudinary.com";
    static CLOUDINARY_NAME = "/dsmffep5o";
    static CLOUDINARY_SCALE_120_100 = "c_limit,w_120,h_100,q_100";
    static CLOUDINARY_SCALE_280_200 = "c_limit,w_280,h_200,q_100";

    static API_LOGIN = this.DOMAIN  + "/api/auth/login";
    static API_SIGNUP = this.DOMAIN  + "/api/auth/signup";

    static API_CUSTOMER = this.DOMAIN  + "/api/customers";
    static API_DEPOSIT = this.API_CUSTOMER + "/deposit";
    static API_WITHDRAW = this.API_CUSTOMER + "/withdraw";
    static API_TRANSFER = this.API_CUSTOMER + "/transfer";

    static API_TRANSFERS = this.DOMAIN + "/api/transfers";

    static API_SUSPENDED_CUSTOMER = this.API_CUSTOMER  + "/suspendedCustomers";

    static CLOUDINARY_URL = this.SERVER_CLOUDINARY + this.CLOUDINARY_NAME + '/image/upload';

    static successAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static errorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        })
    }
    //
    // static renderProduct(item) {
    //     return `
    //         <tr id="tr_${item.id}">
    //             <td>${item.id}</td>
    //             <td>
    //                 <img src="${AppBase.CLOUDINARY_URL + '/' + this.CLOUDINARY_SCALE_120_100 + '/' + item.fileFolder + '/' + item.fileName}" alt="" />
    //             </td>
    //             <td>${item.name}</td>
    //             <td>${item.price}</td>
    //             <td>${item.description}</td>
    //             <td class="text-center">
    //                 <a class="btn btn-outline-secondary edit" title="Edit" data-toggle="tooltip" data-id="${item.id}">
    //                     <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
    //                 </a>
    //             </td>
    //             <td class="text-center">
    //                 <a class="btn btn-outline-danger delete" title="Delete" data-toggle="tooltip" data-id="${item.id}" >
    //                     <i class="fa fa-ban" aria-hidden="true"></i>
    //                 </a>
    //             </td>
    //         </tr>
    //     `;
    // }
}