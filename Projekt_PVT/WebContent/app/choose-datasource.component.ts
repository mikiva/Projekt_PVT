import {Component, Input, OnChanges} from 'angular2/core';

@Component({
    selector: 'choose-source',
    templateUrl: 'app/choose-datasource.html'
})
export class ChooseSource implements OnChanges{
        
    dataSources : string []=["gold", "spectators", "goals", "temperature", "static"];
    @Input() datasource: string;
    ds: string;
    
    constructor() {
    }
    
    ngnOnChanges(): void {
        this.ds = this.datasource; 
    }
    
    getDatasource(): string {
        return this.ds;
    }
}