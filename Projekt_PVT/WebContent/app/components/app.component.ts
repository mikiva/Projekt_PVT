import {Component}  from '@angular/core';
import {GraphContainerComponent} from './graph-container.component';
import 'rxjs/Rx';

@Component( {
    selector: "my-app",
    templateUrl: "app/html/app.html",
    directives: [GraphContainerComponent]
})
export class App {
    
}