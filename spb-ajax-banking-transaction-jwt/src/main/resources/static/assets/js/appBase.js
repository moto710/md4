class AppBase {
    static DOMAIN = location.origin;

    static BASE_URL = this.DOMAIN + "/api";

    static BASE_CUSTOMER = this.BASE_URL + "/customers";
    static CUSTOMER_DEPOSIT = this.BASE_CUSTOMER + "/deposit";
    static CUSTOMER_WITHDRAW = this.BASE_CUSTOMER + "/withdraw";
    static CUSTOMER_TRANSFER = this.BASE_CUSTOMER + "/transfer";

    static BASE_TRANSFERS = this.BASE_URL + "/transfers";

    static BASE_AUTH = this.BASE_URL + "/auth";
    static AUTH_REGISTER = this.BASE_AUTH + "/register";
    static AUTH_LOGIN = this.BASE_AUTH + "/login";

    static API_CUSTOMER = this.DOMAIN  + "/api/customers";
    static API_DEPOSIT = this.API_CUSTOMER + "/deposit";
    static API_WITHDRAW = this.API_CUSTOMER + "/withdraw";
    static API_TRANSFER = this.API_CUSTOMER + "/transfer";

    static API_TRANSFERS = this.DOMAIN + "/api/transfers";

    static Notify = class {
        static alertSuccess(m) {
            $.notify(m, "success");
        }

        static alertError(m) {
            $.notify(m, "error");
        }
    }

    static alertError(message) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: `${message}`
        })
    }

    static alertSuccess(message) {
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: `${message}`,
            showConfirmButton: false,
            timer: 1500
        })
    }
}