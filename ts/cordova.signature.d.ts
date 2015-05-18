declare module Csterm {

    export interface ISignatureCordova {
        myQ: ng.IQService;
        getSignature: () => ng.IPromise<any>;
        getTransparentSignature: () => ng.IPromise<any>;
    }
}
