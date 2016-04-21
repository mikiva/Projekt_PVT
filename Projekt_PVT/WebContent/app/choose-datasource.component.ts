import {Component, Input, OnChanges, Output, EventEmitter} from 'angular2/core';

@Component({
    selector: 'choose-source',
    templateUrl: 'app/choose-datasource.html'
})
export class ChooseSource implements OnChanges{
        
    dataSources : string []=["goldd", "spectators", "goals", "temperature", "static"];
    @Input() datasource: string;
    ds: string;
    @Output() source : EventEmitter<string> = new EventEmitter<string>();
    
    ngnOnChanges() {
        console.log('changed');
        this.ds = this.datasource;
    }
    
    getDataSource() : void {
        console.log('hej');
        this.source.emit(this.ds);
    }
}