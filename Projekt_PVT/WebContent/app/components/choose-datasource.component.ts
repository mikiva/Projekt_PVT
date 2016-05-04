import {Component, Output, Input, EventEmitter, OnInit, OnChanges} from 'angular2/core';
import {HTTP_PROVIDERS} from 'angular2/http';
import {DatasourceService} from '../service/datasource.service';
import {Menu} from '../interface/menu';

@Component({
    selector: 'choose-source',
    templateUrl: 'app/html/choose-datasource.html',
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class ChooseSource implements OnInit {
    @Output() sourceOutput: EventEmitter<Object>= new EventEmitter<Object>();
    source: Object = {database: "", dataset: ""};
        
    menu: Menu[];
    underMenu: String[];
    errorMessage: string;
    
    constructor(private datasourceService: DatasourceService) {
    }
    
    private ngOnInit(): void {
         this.datasourceService.getMenu()
             .subscribe(
                menu => this.menu = menu,
                error =>  this.errorMessage = <any>error);

    }

    private fillUnderMenu(index): void {
        this.underMenu = this.menu[index].values;
    }
    
    public onUnderMenuClick(index): void {
        this.source["dataset"] = this.underMenu[index][0];
        this.sourceOutput.emit(this.source);
    }
    
    public onMenuClick(value, index): void {
        this.source["database"] = this.menu[index].database_link;
        this.fillUnderMenu(index);
    }
}