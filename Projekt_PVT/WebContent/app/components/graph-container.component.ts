import {Component, Output} from 'angular2/core';

import {Graph} from './graph.component';
import {GraphCorrelationComponent} from './graph-correlation.component';
import {ChooseSource} from './choose-datasource.component';

@Component({
    selector: 'graph-container',
    directives: [Graph, GraphCorrelationComponent, ChooseSource],
    templateUrl: 'app/html/graph-container.html',
})

export class GraphContainerComponent {    
    
    sourceOne: Object;
    sourceTwo: Object;
    
    constructor(){}
    
    public setSourceOne(sourceOne: Object) : void {
        this.sourceOne = sourceOne;
    }
    
    public setSourceTwo(sourceTwo: Object) : void {
        this.sourceTwo = sourceTwo;
    }     
}