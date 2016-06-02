import {Component, Output, Input, EventEmitter, OnInit, OnChanges, selectedDate, SimpleChange} from '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';

import {DatasourceService} from '../shared/datasource.service';
import {Menu} from './menu';

@Component({
    selector: 'choose-source',
    templateUrl: 'src/app/graph-container/choose-datasource/choose-datasource.html',
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class ChooseSource implements OnInit, OnChanges {
    @Output() sourceOutput: EventEmitter = new EventEmitter();
    @Input() sourceInput: Object;
    source: Object = null;
    database: string;

    @Input() sM: string;
    @Input() sUm: string;

    menu: Menu[];
    underMenu: String[][];
    errorMessage: string;

    constructor(private datasourceService: DatasourceService) {
    }

    ngOnInit(): void {
        this.datasourceService.getMenu()
            .subscribe(
                menu => this.menu = menu,
                error => this.errorMessage = <any>error);

    }

    onUnderMenuClick(index: number): void {
        this.source = { database: this.database, dataset: this.underMenu[index][0] };
        this.sourceOutput.emit(this.source);
    }

    onMenuClick(value, index: number): void {
        this.database = this.menu[index].database_link;
        this.fillUnderMenu(index);
    }

    private fillUnderMenu(index: number): void {
        this.underMenu = this.menu[index].values;
    }
    
    ngOnChanges() {

        console.log("choose Source on change");

        var m: HTMLSelectElement = (<HTMLSelectElement> document.getElementById("menu"));
        var uM = (<HTMLSelectElement>document.getElementById("underMenu"));

        for (var i = 0; i < m.options.length; i++) {
            if (m.options[i].text == this.sM)
                m.options[i].selected = true;
        }

        for (var i = 0; i < uM.options.length; i++) {
            if (uM.options[i].text == this.sUm)
                uM.options[i].selected = true;
        }



    }

}