import {Component, Input, OnChanges, SimpleChange} from 'angular2/core';
import {CHART_DIRECTIVES, Highcharts} from 'angular2-highcharts';
import {ChooseSource} from './choose-datasource.component';
import {DatasourceService} from '../service/datasource.service';
import {IDatasource} from '../interface/datasource';
import {DataSourceJson} from '../interface/datasource-json';
import {HTTP_PROVIDERS} from 'angular2/http';

Highcharts

@Component({
    selector: 'graph-correlation',
    directives: [CHART_DIRECTIVES, ChooseSource],
    templateUrl: 'app/html/graph-correlation.html',
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class GraphCorrelationComponent implements OnChanges {

    chart: HighchartsChartObject;
    options: HighchartsOptions;
    errorMessage: string;
    datasource: DataSourceJson;
    @Input() sourceOne: string;
    @Input() sourceTwo: string;

    constructor(private dataSourceService: DatasourceService) {
    }
    
    saveInstance(chart: HighchartsChartObject) {
        this.chart = chart;
        console.log(this.datasource);
    }
    
    plot() {
        this.dataSourceService.getData(this.sourceOne, this.sourceTwo)
            .subscribe(
                datasource => this.datasource = datasource,
                error => this.errorMessage = <any>error,
                () => this.plotGraph());
    }
    
    
    
    private plotGraph() : void {
        var xName = this.datasource.xName;
        
        this.options = {
            title: { text: this.sourceOne },
            plotOptions: {
              area: { turboThreshold: 0 }  
            },
            xAxis: {
              title: { text: this.datasource.xName }  
            },
            yAxis: {
              title: { text: this.datasource.yName }  
            },
            tooltip: {
                formatter: function() {
                    var yName = this.series.yAxis.userOptions.title.text;
                    return 'Date: ' + this.point.date + '<br>' + 
                            xName + ': ' + this.point.x + '<br>' + 
                            yName + ': ' + this.point.y;
                }
            },
            series: [{
                data: this.datasource.data,
                type: 'scatter',
            }]
        };
    }
    ngOnChanges(changes: {[source: string]: SimpleChange}) {
        if(this.sourceOne != null && this.sourceTwo != null) 
            this.plot();
    }
}