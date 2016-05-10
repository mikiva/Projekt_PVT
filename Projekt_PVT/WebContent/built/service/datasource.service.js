System.register(['angular2/core', 'angular2/http', 'rxjs/Observable'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, Observable_1;
    var DatasourceService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (Observable_1_1) {
                Observable_1 = Observable_1_1;
            }],
        execute: function() {
            DatasourceService = (function () {
                function DatasourceService(http) {
                    this.http = http;
                    this.url = 'http://localhost:8080/Proj/ServletTest?';
                    //private url = 'http://rigel.se:8080/Bulle/ServletTest?';
                    this.menuUrl = 'http://localhost:8080/Proj/GraphChoiceJsonServlet';
                }
                DatasourceService.prototype.getData = function (sourceOne, sourceTwo) {
                    var _this = this;
                    console.log(this.getUrl(sourceOne, sourceTwo));
                    return this.http.get(this.getUrl(sourceOne, sourceTwo))
                        .map(function (response) { return response.json(); })
                        .do(function (data) { return console.log('All: ' + _this.url + JSON.stringify(data)); })
                        .catch(this.handleError);
                };
                DatasourceService.prototype.getMenu = function () {
                    return this.http.get(this.menuUrl)
                        .map(function (response) { return response.json().data; })
                        .do(function (data) { return console.log('All: ' + JSON.stringify(data)); })
                        .catch(this.handleError);
                };
                DatasourceService.prototype.handleError = function (error) {
                    console.log(error);
                    return Observable_1.Observable.throw(error.json().error || 'Server error');
                };
                DatasourceService.prototype.getUrl = function (sourceOne, sourceTwo) {
                    return (this.url + 'database1=' + sourceOne["database"] + '&value1=' + sourceOne["dataset"] +
                        (sourceTwo ? '&database2=' + sourceTwo["database"] + '&value2=' + sourceTwo["dataset"] : ""));
                };
                DatasourceService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], DatasourceService);
                return DatasourceService;
            }());
            exports_1("DatasourceService", DatasourceService);
        }
    }
});
//# sourceMappingURL=datasource.service.js.map