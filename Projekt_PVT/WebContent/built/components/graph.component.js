System.register(['angular2/core', 'angular2-highcharts', '../service/datasource.service', 'angular2/http', 'rxjs/Rx'], function(exports_1, context_1) {
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
    var core_1, angular2_highcharts_1, datasource_service_1, http_1;
    var Graph;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (angular2_highcharts_1_1) {
                angular2_highcharts_1 = angular2_highcharts_1_1;
            },
            function (datasource_service_1_1) {
                datasource_service_1 = datasource_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {}],
        execute: function() {
            Graph = (function () {
                function Graph(dataSourceService) {
                    this.dataSourceService = dataSourceService;
                }
                Graph.prototype.ngOnInit = function () {
                    this.options = {
                        title: {
                            text: 'No data'
                        },
                        series: [{}],
                        noData: {
                            style: {
                                fontSize: '20px'
                            }
                        }
                    };
                };
                Graph.prototype.plot = function () {
                    var _this = this;
                    this.dataSourceService.getData(this.sourceInput)
                        .subscribe(function (datasource) { return _this.datasource = datasource; }, function (error) { return _this.errorMessage = error; }, function () { return _this.plotGraph(); });
                };
                Graph.prototype.plotGraph = function () {
                    var dates = [];
                    this.datasource.data.forEach(function (data) { return dates.push(data.date); });
                    this.options = {
                        title: { text: this.datasource.name },
                        yAxis: {
                            title: {
                                text: this.datasource.unit
                            }
                        },
                        xAxis: {
                            categories: dates
                        },
                        series: [{
                                name: this.datasource.unit,
                                data: this.datasource.data,
                                type: 'line',
                                turboThreshold: 0
                            }]
                    };
                };
                Graph.prototype.ngOnChanges = function () {
                    if (this.sourceInput != null) {
                        console.log(this.sourceInput);
                        console.log(this.sourceInput);
                        console.log(this.sourceInput);
                        this.plot();
                    }
                };
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', Object)
                ], Graph.prototype, "sourceInput", void 0);
                Graph = __decorate([
                    core_1.Component({
                        selector: 'graph',
                        directives: [angular2_highcharts_1.CHART_DIRECTIVES],
                        templateUrl: 'app/html/graph.html',
                        providers: [datasource_service_1.DatasourceService, http_1.HTTP_PROVIDERS]
                    }), 
                    __metadata('design:paramtypes', [datasource_service_1.DatasourceService])
                ], Graph);
                return Graph;
            }());
            exports_1("Graph", Graph);
        }
    }
});
//# sourceMappingURL=graph.component.js.map