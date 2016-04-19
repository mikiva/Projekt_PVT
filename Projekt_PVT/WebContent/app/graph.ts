import { Component } from 'angular2/core';
import {CHART_DIRECTIVES} from 'angular2-highcharts';
import {ChooseSource} from './choose-datasource.component';

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
    		`]
})
export class Graph {
    constructor() {
        this.options = {
            title: { text: 'hej' },
            series: [{
                data: [[29.9, 23], [71.5, 32], [106.4, 142], [129.2, 32]],
                type: 'scatter'
            }]

        };

    }
    options: Object;
}