import {Component} from 'angular2/core';


@Component({
    selector: 'choose-source',
    templateUrl: 'app/choose-datasource.html'
})
export class ChooseSource {
        
    dataSource : string []=["gold", "spectators", "goals", "temperature", "static"];
    
    constructor() {
        
    }
}