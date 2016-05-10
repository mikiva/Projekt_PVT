System.register(['angular2/core', 'angular2/http', '../service/datasource.service'], function(exports_1, context_1) {
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
    var core_1, http_1, datasource_service_1;
    var ChooseSource;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (datasource_service_1_1) {
                datasource_service_1 = datasource_service_1_1;
            }],
        execute: function() {
            ChooseSource = (function () {
                function ChooseSource(datasourceService) {
                    this.datasourceService = datasourceService;
                    this.sourceOutput = new core_1.EventEmitter();
                    this.source = null;
                }
                ChooseSource.prototype.ngOnInit = function () {
                    var _this = this;
                    this.datasourceService.getMenu()
                        .subscribe(function (menu) { return _this.menu = menu; }, function (error) { return _this.errorMessage = error; });
                };
                ChooseSource.prototype.fillUnderMenu = function (index) {
                    this.underMenu = this.menu[index].values;
                };
                ChooseSource.prototype.onUnderMenuClick = function (index) {
                    this.source = { database: this.database, dataset: this.underMenu[index][0] };
                    this.sourceOutput.emit(this.source);
                };
                ChooseSource.prototype.onMenuClick = function (value, index) {
                    this.database = this.menu[index].database_link;
                    this.fillUnderMenu(index);
                };
                __decorate([
                    core_1.Output(), 
                    __metadata('design:type', core_1.EventEmitter)
                ], ChooseSource.prototype, "sourceOutput", void 0);
                ChooseSource = __decorate([
                    core_1.Component({
                        selector: 'choose-source',
                        templateUrl: 'app/html/choose-datasource.html',
                        providers: [datasource_service_1.DatasourceService, http_1.HTTP_PROVIDERS]
                    }), 
                    __metadata('design:paramtypes', [datasource_service_1.DatasourceService])
                ], ChooseSource);
                return ChooseSource;
            }());
            exports_1("ChooseSource", ChooseSource);
        }
    }
});
//# sourceMappingURL=choose-datasource.component.js.map