# signaturePlugin-cordova

#Cordova Plugin for Native Signature Base64 Image

Get signature Base64 image with a white background.
 ```js
 cordova.plugins.signaturePlugin.getSignature(
 function(base64Image){
    console.log(base64Image);
 }, 
 function(error){
    console.log(error);
 });
 ```


## Android Library

Thanks to [gcacace](https://github.com/gcacace), there is a Android Library this Plugin uses.
Please refer to https://github.com/gcacace/android-signaturepad.
