System.register(['angular2/core', './graph.component', './graph-correlation.component', './choose-datasource.component'], function(exports_1, context_1) {
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
    var core_1, graph_component_1, graph_correlation_component_1, choose_datasource_component_1;
    var GraphContainerComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (graph_component_1_1) {
                graph_component_1 = graph_component_1_1;
            },
            function (graph_correlation_component_1_1) {
                graph_correlation_component_1 = graph_correlation_component_1_1;
            },
            function (choose_datasource_component_1_1) {
                choose_datasource_component_1 = choose_datasource_component_1_1;
            }],
        execute: function() {
            GraphContainerComponent = (function () {
                function GraphContainerComponent() {
                }
                GraphContainerComponent.prototype.setSourceOne = function (sourceOne) {
                    this.sourceOne = sourceOne;
                };
                GraphContainerComponent.prototype.setSourceTwo = function (sourceTwo) {
                    this.sourceTwo = sourceTwo;
                };
                GraphContainerComponent = __decorate([
                    core_1.Component({
                        selector: 'graph-container',
                        directives: [graph_component_1.Graph, graph_correlation_component_1.GraphCorrelationComponent, choose_datasource_component_1.ChooseSource],
                        templateUrl: 'app/html/graph-container.html',
                    }), 
                    __metadata('design:paramtypes', [])
                ], GraphContainerComponent);
                return GraphContainerComponent;
            }());
            exports_1("GraphContainerComponent", GraphContainerComponent);
        }
    }
});
//# sourceMappingURL=graph-container.component.js.map