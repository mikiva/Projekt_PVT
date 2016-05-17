import {Component, OnChanges, Input, SimpleChange, OnInit, selectedDate} from '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';

import {CHART_DIRECTIVES} from 'angular2-highcharts';
import {DatasourceService} from '../shared/datasource.service';
import {GraphCorrelationComponent} from'./graph-correlation.component';
import {DataSourceSingleJson} from './datasource-single-json';
import {IDatasource} from '../shared/datasource';
import {DataSourceJson} from '../shared/datasource-json';
import 'rxjs/Rx';

@Component({
    selector: 'graph',
    directives: [CHART_DIRECTIVES],
    templateUrl: 'src/app/graph-container/graph/graph.html',
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class Graph implements OnChanges, OnInit {

    options: Object;
    errorMessage: string;
    datasource: DataSourceSingleJson;
    @Input() sourceInput: Object;
    @Input() resolution: string;
    @Input() dateBefore: string;
    @Input() dateAfter: string;
    

    constructor(private dataSourceService: DatasourceService) {
    }
    ngOnInit() {
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
    }
    plot(): void {
        this.dataSourceService.getData(this.dateBefore, this.dateAfter, this.resolution, this.sourceInput)
            .subscribe(
            datasource => this.datasource = datasource,
            error => this.errorMessage = <any>error,
            () => this.plotGraph());

    }

    ngOnChanges(): void {
        if (this.sourceInput != null) {
            this.plot();
        }
    }
    
    private plotGraph(): void {
        var hasData : boolean = this.datasource.data.length != 0;

        this.options = {
            title: { text: hasData? this.datasource.name: 'No data in range' },
            yAxis: {
                title: {
                    text: this.datasource.unit
                }
            },
            xAxis: {
                categories: this.datasource.data.map(date => date.date)
            },
            series: [{
                name: this.datasource.unit,
                data: this.datasource.data,
                type: 'line',
                turboThreshold: 0
            }]
        };
    }
}