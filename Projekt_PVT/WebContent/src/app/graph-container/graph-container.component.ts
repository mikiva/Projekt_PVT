import {Component, Output} from '@angular/core';

import {Graph} from './graph/graph.component';
import {GraphCorrelationComponent} from './graph-correlation/graph-correlation.component';
import {ChooseSource} from './choose-datasource/choose-datasource.component';
import {ChooseResolution} from './choose-resolution/choose-resolution.component';
import {SampleDatePicker} from './datepicker/sampleapp';
import {MyDatePicker} from './mydatepicker';
import {SavedHeaders} from './saved-headers/saved-headers.component';


@Component({
    selector: 'graph-container',
    directives: [Graph, GraphCorrelationComponent, ChooseSource, ChooseResolution, SampleDatePicker, SavedHeaders],
    templateUrl: 'src/app/graph-container/graph-container.html',
})
export class GraphContainerComponent {

    sourceOne: Object;
    sourceTwo: Object;
    resolution: string;
    dateBefore: string;
    dateAfter: string;
    
    constructor() {}
    
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
}