import {Component, Output, EventEmitter} from 'angular2/core';
import {CHART_DIRECTIVES} from 'angular2-highcharts';
import {ChooseSource} from './choose-datasource.component';
import {DatasourceService} from '../service/datasource.service';
import {IDatasource} from './datasource';
import {HTTP_PROVIDERS} from 'angular2/http';

@Component({
    selector: 'graph',
    directives: [CHART_DIRECTIVES, ChooseSource],
    templateUrl: 'app/html/graph.html',
    stylesUrl: 'css/graph.css',
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class Graph {

    options: Object;
    errorMessage: string;
    datasource: IDatasource[];
     source: string;
     @Output source: EventEmitter<string> = new EventEmitter<string>();

    constructor(private dataSourceService: DatasourceService) {
    }
    
    plot(source : string) : void {
        this.source = source;
        this.dataSourceService.getData(this.source, null)
            .subscribe(
                datasource => this.datasource = datasource,
                error => this.errorMessage = <any>error,
                () => this.plotGraph() );
    }
    
    private plotGraph() : void {
        this.options = {
            title: { text: 'hej' },
            series: [{
                data: this.datasource,
                type: 'line',
                turboThreshold: 0
            }]
        };
    }
    
    emitSource() : void {
        this.source.emit(source);
    }
}