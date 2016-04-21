import {Component} from 'angular2/core';
import {Graph} from './graph.component';
import {GraphCorrelationComponent} from './graph-correlation.component';

@Component({
    selector: 'graph-container',
    directives: [Graph, GraphCorrelationComponent],
    templateUrl: 'app/html/graph-container.html',
})

export class GraphContainerComponent {
    
    sourceOne: string = "";
    sourceTwo: string = "";
    
    
    public setSourceOne(sourceOne : string) {
        this.sourceOne = sourceOne;
    }
    
    public setSourceTwo(sourceTwo : string) {
        this.sourceTwo = sourceTwo;
    }
    
    
}