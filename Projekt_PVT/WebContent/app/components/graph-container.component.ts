import {Component} from 'angular2/core';
import {Graph} from './graph.component';
import {GraphCorrelationComponent} from './graph-correlation.component';

@Component({
    selector: 'graph-container',
    directives: [Graph, GraphCorrelationComponent],
    templateUrl: 'app/html/graph-container.html',
})

export class GraphContainerComponent {
    
    constructor(){}
    
    sourceOne: string = 'goals';
    sourceTwo: string = 'spectators';
    
    
    public setSourceOne(sourceOne : string) {
        this.sourceOne = sourceOne;
        console.log(this.sourceOne + ' set source one');
    }
    
    public setSourceTwo(sourceTwo : string) {
        console.log(sourceTwo + ' set source two');
        this.sourceTwo = sourceTwo;
    }
    
    public print(text: any){
        
        console.log(text.toString() + ' soruce set');
    }
    
    
}