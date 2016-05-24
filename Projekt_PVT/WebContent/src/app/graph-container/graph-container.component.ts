import {Component, Output} from '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';

import {Graph} from './graph/graph.component';
import {GraphCorrelationComponent} from './graph-correlation/graph-correlation.component';
import {ChooseSource} from './choose-datasource/choose-datasource.component';
import {ChooseResolution} from './choose-resolution/choose-resolution.component';
import {SampleDatePicker} from './datepicker/sampleapp';
import {MyDatePicker} from './mydatepicker';
import {DatabaseService} from './shared/database.service';


@Component({
    selector: 'graph-container',
    directives: [Graph, GraphCorrelationComponent, ChooseSource, ChooseResolution, SampleDatePicker],
    templateUrl: 'src/app/graph-container/graph-container.html',
    providers: [DatabaseService, HTTP_PROVIDERS]
})
export class GraphContainerComponent {

    sourceOne: Object;
    sourceTwo: Object;
    resolution: string;
    dateBefore: string;
    dateAfter: string;
    selectedData: string[];
    
    constructor(private databaseService: DatabaseService) {}
    
    setDateBefore(dateBefore: string) : void {
        this.dateBefore = dateBefore;
        console.log(dateBefore);
    }
    
    setDateAfter(dateAfter: string) : void {
        this.dateAfter = dateAfter;
    }

    setSourceOne(sourceOne: Object): void {
        this.sourceOne = sourceOne;
    }

    setSourceTwo(sourceTwo: Object): void {
        this.sourceTwo = sourceTwo;
    }

    setResolution(resolution: string): void {
        this.resolution = resolution;
    }
 
 saveAnalysis(){
     this.databaseService.saveAnalysis(this.dateBefore, this.dateAfter, this.resolution, this.sourceOne, this.sourceTwo, this.getTitle());
 }
 getTitle(){
     
     return document.getElementById("title").innerHTML;
     
 }
}