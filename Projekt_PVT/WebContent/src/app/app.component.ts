import {Component}  from '@angular/core';

import {GraphContainerComponent} from './graph-container/graph-container.component';
import 'rxjs/Rx';

@Component( {
    selector: "my-app",
    templateUrl: "src/app/app.html",
    directives: [GraphContainerComponent]
})
export class App {
    
}