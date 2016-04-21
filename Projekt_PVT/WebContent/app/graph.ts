import { Component, OnInit } from 'angular2/core';
import {CHART_DIRECTIVES} from 'angular2-highcharts';
import {ChooseSource} from './choose-datasource.component';
import {DatasourceService} from './datasource.service';
import {IDatasource} from './datasource';
import {HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/Rx';

@Component({
    selector: 'graph',
    directives: [CHART_DIRECTIVES, ChooseSource],
    templateUrl: 'app/graph.html'
    ,
    styles: [`
    	chart{
    			display: block;
                width: 45%;
                height: 35%;

    		}
    		`],
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class Graph implements OnInit {

    options: Object;

    constructor(private datasourceService: DatasourceService) {
    }

    errorMessage: string;
    datasource: IDatasource[];

    ngOnInit(): void {
        this.datasourceService.getData('goals')
            .subscribe(
                datasource => this.datasource = datasource,
                error => this.errorMessage = <any>error,
                () => this.plotGraph() );
    }
    
    plot(source : string) {
        this.datasourceService.getData(source)
            .subscribe(
                datasource => this.datasource = datasource,
                error => this.errorMessage = <any>error,
                () => this.plotGraph() );
    }
    
    plotGraph() {
        this.options = {
            title: { text: 'hej' },
            series: [{
                data: this.datasource,
                type: this.datasource[0].x? 'scatter' : 'line',
            }]
        };
    }
}