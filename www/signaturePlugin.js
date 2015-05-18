var exec = require('cordova/exec');

exports.getSignature = function(success, error, arg0) {
    exec(success, error, "signaturePlugin", "getSignature", [arg0]);
};

exports.getTransparentSignature = function(success, error, arg0) {
    exec(success, error, "signaturePlugin", "getTransparentSignature", [arg0]);
};
