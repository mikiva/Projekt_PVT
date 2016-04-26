import {Component, OnChanges, Input, SimpleChange} from 'angular2/core';
import {CHART_DIRECTIVES} from 'angular2-highcharts';
import {ChooseSource} from './choose-datasource.component';
import {DatasourceService} from '../service/datasource.service';
import {IDatasource} from '../interface/datasource';
import {HTTP_PROVIDERS} from 'angular2/http';
import {GraphCorrelationComponent} from'./graph-correlation.component';

@Component({
    selector: 'graph',
    directives: [CHART_DIRECTIVES, ChooseSource],
    templateUrl: 'app/html/graph.html',
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class Graph implements OnChanges {

    options: Object;
    errorMessage: string;
    datasource: IDatasource[];
    @Input() sourceInput: string;

    constructor(private dataSourceService: DatasourceService) {
    }

    plot(): void {
        this.dataSourceService.getData(this.sourceInput, null)
            .subscribe(
                datasource => this.datasource = datasource,
                error => this.errorMessage = <any>error,
                () => this.plotGraph());
          
    }
    
     private plotGraph():  void {
        this.options = {
            title: { text: this.sourceInput },
             yAxis: {
                 title: {
                     text: this.sourceInput
             }
            },
            series: [{
                data: this.datasource,
                type: 'line',
                turboThreshold: 0
            }]
        };
    }
    
  ngOnChanges(changes: {[propName: string]: SimpleChange}) {
        if(this.sourceInput != null) 
            this.plot();
  }
}