class AppBase {
    static DOMAIN = location.origin;

    static API_CUSTOMER = this.DOMAIN  + "/api/customers";
    static API_DEPOSIT = this.DOMAIN + "/api/customers/deposit";
    static API_WITHDRAW = this.DOMAIN + "/api/customers/withdraw";
    static API_TRANSFER = this.DOMAIN + "/api/customers/transfer";

    static API_TRANSFERS = this.DOMAIN + "/api/transfers";
}