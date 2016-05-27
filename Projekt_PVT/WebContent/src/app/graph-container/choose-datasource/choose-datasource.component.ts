import {Component, Output, Input, EventEmitter, OnInit, OnChanges, selectedDate} from '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';

import {DatasourceService} from '../shared/datasource.service';
import {Menu} from './menu';

@Component({
    selector: 'choose-source',
    templateUrl: 'src/app/graph-container/choose-datasource/choose-datasource.html',
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class ChooseSource implements OnInit {
    @Output() sourceOutput: EventEmitter = new EventEmitter();
    source: Object = null;
    database: string;

    menu: Menu[];
    underMenu: String[][];
    errorMessage: string;
    
    constructor(private datasourceService: DatasourceService) {
    }
    
    ngOnInit(): void {
        this.datasourceService.getMenu()
            .subscribe(
                menu => this.menu = menu,
                error =>  this.errorMessage = <any>error);

    }
    
    onUnderMenuClick(index: number): void {
        this.source = {database: this.database, dataset: this.underMenu[index][0]};
        this.sourceOutput.emit(this.source);
    }
    
    onMenuClick(value, index: number): void {
        this.database = this.menu[index].database_link;
        this.fillUnderMenu(index);
    }

    private fillUnderMenu(index: number): void {
        this.underMenu = this.menu[index].values;
    }
    update(source1:Object, source2:Object){
        
    }
}