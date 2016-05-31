import {Component, Output, Input, EventEmitter , OnInit, OnChanges} from '@angular/core';
import {HTTP_PROVIDERS } from '@angular/http';

import {LoadDataService} from '../shared/loadData.service';

@Component({
    selector: 'choose-saved',
    templateUrl: 'src/app/graph-container/choose-saved-analysis/choose-saved.html',
    providers: [LoadDataService, HTTP_PROVIDERS ]
})
export class ChooseSaved implements OnInit{
    @Output() savedOutput: EventEmitter  = new EventEmitter();
    savedAnalysis: Object = null;
    saved: String[];
    errorMessage: string;
        
    constructor(private loadDataService : LoadDataService){}
    
    ngOnInit(): void{
        this.updateList();        
    }
    
    updateList(){
        this.loadDataService.getSaved()
            .subscribe(
                saved => this.saved = saved,
                error => this.errorMessage = <any>error);
    }
    
   public loadSaved(title: string): void{
        this.savedOutput.emit(title);
    }
}