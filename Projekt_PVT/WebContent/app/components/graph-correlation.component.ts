import {Component, Input, OnChanges, SimpleChange} from 'angular2/core';
import {CHART_DIRECTIVES} from 'angular2-highcharts';
import {ChooseSource} from './choose-datasource.component';
import {DatasourceService} from '../service/datasource.service';
import {IDatasource} from '../interface/datasource';
import {HTTP_PROVIDERS} from 'angular2/http';

@Component({
    selector: 'graph-correlation',
    directives: [CHART_DIRECTIVES, ChooseSource],
    templateUrl: 'app/html/graph-correlation.html',
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class GraphCorrelationComponent implements OnChanges {

    options: Object;
    errorMessage: string;
    datasource: IDatasource[];
    @Input() sourceOne: string;
    @Input() sourceTwo: string;

    constructor(private dataSourceService: DatasourceService) {
    }
    
    plot() {
        this.dataSourceService.getData(this.sourceOne, this.sourceTwo)
            .subscribe(
                datasource => this.datasource = datasource,
                error => this.errorMessage = <any>error,
                () => this.plotGraph());
    }
    
    private plotGraph() : void {
        this.options = {
            title: { text: this.sourceOne },
            series: [{
                data: this.datasource,
                type: 'scatter',
                turboThreshold: 0
            }]
        };
    }
    ngOnChanges(changes: {[propName: string]: SimpleChange}) {
        console.log('CHANGdE');
    }
}