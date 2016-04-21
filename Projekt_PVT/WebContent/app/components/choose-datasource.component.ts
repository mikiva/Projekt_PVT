import {Component, Input, OnChanges, Output, EventEmitter} from 'angular2/core';

@Component({
    selector: 'choose-source',
    templateUrl: 'app/html/choose-datasource.html'
})
export class ChooseSource {
    
    @Input() input: string = "hej";
    @Output() output: EventEmitter<string> = new EventEmitter<string>();
    
    dataSources : string []=["gold", "spectators", "goals", "temperature", "static"];
    
    ds: string;
    
    constructor() {
    }
    
    public onClick(value : string) {
        this.output.emit(value);
    }
}