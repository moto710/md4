const request = require('request');

const callExternalApiUsingRequest = (callback) => {
    request("https://vapi.vnappmob.com/api/province/", {json: true}, (err, res, body) => {
        if(err){
            return callback(err);
        }
        return callback(body);
    });
}
module.exports.callApi = callExternalApiUsingRequest;