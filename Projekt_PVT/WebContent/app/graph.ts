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
    constructor(private datasourceService: DatasourceService) {
       
    }
    
        errorMessage: string;
    datasource: IDatasource[];
    ds: IDatasource[] = [
		{
			"x":3.0,
			"y":6711.0,
			"date":"2014-04-06"
		},
		 {
			"x":3.0,
			"y":3173.0,
			"date":"2014-04-17"
		},
		 {
			"x":2.0,
			"y":2837.0,
			"date":"2014-04-28"
		},
		 {
			"x":2.0,
			"y":3091.0,
			"date":"2014-05-08"
		},
		 {
			"x":1.0,
			"y":3060.0,
			"date":"2014-05-11"
		},
		 {
			"x":2.0,
			"y":4199.0,
			"date":"2014-05-26"
		}];



    ngOnInit(): void {
        this.datasourceService.getData()
            .subscribe(
                datasource => this.datasource = datasource,
                error =>  this.errorMessage = <any>error,
                () => this.go() );
                
                
                        
}
    
    
    go(){
        
        this.options = {
            title: { text: 'hej' },
            series: [{
                data: this.datasource,
                type: 'scatter'
            }]

        };
        
    }
    
    
    
    
    
    options: Object;
}