import {Component, OnChanges, Input, SimpleChange, OnInit} from 'angular2/core';
import {CHART_DIRECTIVES} from 'angular2-highcharts';
import {DatasourceService} from '../service/datasource.service';
import {IDatasource} from '../interface/datasource';
import {DataSourceJson} from '../interface/datasource-json';
import {HTTP_PROVIDERS} from 'angular2/http';
import {GraphCorrelationComponent} from'./graph-correlation.component';
import {DataSourceSingleJson} from '../interface/datasource-single-json';
import 'rxjs/Rx';

@Component({
    selector: 'graph',
    directives: [CHART_DIRECTIVES],
    templateUrl: 'app/html/graph.html',
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class Graph implements OnChanges, OnInit {

    options: Object;
    errorMessage: string;
    datasource: DataSourceSingleJson;
    @Input() sourceInput: Object;

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
        this.dataSourceService.getData(this.sourceInput)
            .subscribe(
            datasource => this.datasource = datasource,
            error => this.errorMessage = <any>error,
            () => this.plotGraph());

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

    ngOnChanges(): void {
        if (this.sourceInput != null) {
            console.log(this.sourceInput);
            console.log(this.sourceInput);
            console.log(this.sourceInput);
            this.plot();
        }
    }
}