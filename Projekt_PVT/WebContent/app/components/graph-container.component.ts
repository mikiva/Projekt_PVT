import {Component} from 'angular2/core';
import {Graph} from './graph.component';

@Component({
    selector: 'graph-container',
    directives: [Graph],
    templateUrl: 'app/html/graph-container.html',
})

export class GraphContainerComponent {
    
}