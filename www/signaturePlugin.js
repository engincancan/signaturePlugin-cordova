var exec = require('cordova/exec');

exports.getSignature = function(success, error, arg0) {
    exec(success, error, "signaturePlugin", "getSignature", arg0 || []);
};

exports.getTransparentSignature = function(success, error, arg0) {
    exec(success, error, "signaturePlugin", "getTransparentSignature", arg0 || []);
};


exports.init = function(success, error, arg0) {
    exec(success, error, "signaturePlugin", "init", arg0);
};
