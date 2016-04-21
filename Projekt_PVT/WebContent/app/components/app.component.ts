import {Component}  from 'angular2/core';
import {GraphContainerComponent} from './graph-container.component';
import 'rxjs/Rx';

@Component( {
    selector: "app",
    templateUrl: "app/html/app.html",
    directives: [GraphContainerComponent]
})
export class App {
    
}