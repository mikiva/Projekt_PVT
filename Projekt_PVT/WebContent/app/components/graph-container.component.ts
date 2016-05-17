import {Component, Output} from '@angular/core';
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
    dateBefore: string;
    dateAfter: string;
    
    
    public setDateBefore(dateBefore: string) : void {
        this.dateBefore = dateBefore;
        console.log(dateBefore);
    }
    
    public setDateAfter(dateAfter: string) : void {
        this.dateAfter = dateAfter;
        console.log(dateAfter);
        console.log(dateAfter);
        console.log(dateAfter);
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