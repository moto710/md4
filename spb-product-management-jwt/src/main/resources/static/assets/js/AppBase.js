class AppBase {
    static DOMAIN = location.origin;

    static API_PRODUCT = this.DOMAIN  + "/api/products";
    static API_CREATE = this.API_PRODUCT + "/create";

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

    static Notify = class {
        static successAlert(m) {
            $.notify(m, "success");
        }

        static errorAlert(m) {
            $.notify(m, "error");
        }
    }
}