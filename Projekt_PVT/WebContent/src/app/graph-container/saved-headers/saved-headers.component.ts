import {Component, Output, EventEmitter, OnInit} from '@angular/core';
import {DatasourceService} from '../shared/datasource.service';
import {Heading} from './saved-headers/saved-headers-interface';

@Component({
    selector: 'saved-headers',
    templateUrl: 'src/app/graph-container/saved-headers/saved-headers.html',
    providers: [DatasourceService]
})

export class SavedHeaders implements OnInit {
    
    heading: Heading[];
    errorMessage: string;
    @Output() output: EventEmitter<string> = new EventEmitter<string>();
    
    constructor(private datasourceService: DatasourceService) {}

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

