import {Component, Output} from 'angular2/core';
import {Graph} from './graph.component';
import {GraphCorrelationComponent} from './graph-correlation.component';
import {ChooseSource} from './choose-datasource.component';
import {ChooseResolution} from './choose-resolution.component';
import {SampleDatePicker} from './sampleapp';
import {MyDatePicker} from './mydatepicker';


@Component({
    selector: 'graph-container',
    directives: [Graph, GraphCorrelationComponent, ChooseSource, ChooseResolution, SampleDatePicker],
    templateUrl: 'app/html/graph-container.html',
})

export class GraphContainerComponent {

    sourceOne: Object;
    sourceTwo: Object;
    resolution: string;
    selectedDate: string;
    
    
    public selectedDateBefore(selectedDate: string) : void{
        this.selectedDate = selectedDate;
    }
    
    public selectedDateAfter(selectedDate: string) : void{
        this.selectedDate = selectedDate;
    }

    constructor() { }

    public setSourceOne(sourceOne: Object): void {
        this.sourceOne = sourceOne;
    }

    public setSourceTwo(sourceTwo: Object): void {
        this.sourceTwo = sourceTwo;
    }

    public setResolution(resolution: string): void {
        this.resolution = resolution;
    }
}