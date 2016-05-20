import {Component, Output, EventEmitter, OnInit} from '@angular/core';
import {DatasourceService} from '../shared/datasource.service';
import {saved-headers} from './saved-headers/saved-headers-interface';

@Component({
    selector: 'saved-headers',
    templateUrl: 'src/app/graph-container/saved-headers/saved-headers.html',
    providers: [DatasourceService]
})

export class SavedHeaders implements OnInit{
      
    constructor() {}
    
    @Output() output: EventEmitter<string> = new EventEmitter<string>();

    onClick(value: string) {
        this.output.emit(value);
    }
    
    
    ngOnInit(): void {
        this.datasourceService.getSavedHeaders()
            .subscribe(
                heading => this.heading = heading,
                error =>  this.errorMessage = <any>error);
    }
        
}

