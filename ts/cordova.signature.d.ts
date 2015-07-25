declare module Csterm {

    export interface ISignatureCordova {
        myQ: ng.IQService;
        init: (buttonNames: String[]) => ng.IPromise<any>;
        getSignature: (text: String[]) => ng.IPromise<any>;
        getTransparentSignature: (text: String[]) => ng.IPromise<any>;

    }
}
