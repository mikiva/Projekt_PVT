import {Component, OnInit} from 'angular2/core';
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
export class Graph implements OnInit {

    options: Object;

    constructor(private datasourceService: DatasourceService) {
    }

    errorMessage: string;
    datasource: IDatasource[];

    ngOnInit(): void {
        this.datasourceService.getData(null)
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
    
    private plotGraph() {
        this.options = {
            title: { text: 'hej' },
            series: [{
                data: this.datasource,
                type: this.datasource[0].x? 'scatter' : 'line',
                turboThreshold: 0
            }]
        };
    }
    
    print(text : string) : void {
        console.log(text);
    }
}