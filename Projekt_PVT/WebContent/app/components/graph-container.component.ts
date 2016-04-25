import {Component} from 'angular2/core';
import {Graph} from './graph.component';
import {GraphCorrelationComponent} from './graph-correlation.component';
import {ChooseSource} from './choose-datasource.component';

@Component({
    selector: 'graph-container',
    directives: [Graph, GraphCorrelationComponent, ChooseSource],
    templateUrl: 'app/html/graph-container.html',
})

export class GraphContainerComponent {    
    
    sourceOne: string;
    sourceTwo: string;
    
    constructor(){}
    
    public setSourceOne(sourceOne : string) : void {
        this.sourceOne = sourceOne;
    }
    
    public setSourceTwo(sourceTwo : string) : void {
        this.sourceTwo = sourceTwo;
    }     
}