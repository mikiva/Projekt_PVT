import {Component}  from 'angular2/core';
import {Graph} from './graph.component';
import 'rxjs/Rx';

@Component( {
    selector: "app",
    templateUrl: "app/html/app.html",
    directives: [Graph]
})
export class App {
    
}