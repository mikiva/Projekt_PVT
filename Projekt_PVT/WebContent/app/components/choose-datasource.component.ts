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
    @Input() input: string = "hej";
    @Output() output: EventEmitter<string> = new EventEmitter<string>();
    @Output() index: EventEmitter<number>= new EventEmitter<number>();
    menu: Menu;
    underMenu: String[];
    errorMessage: string;
    
    constructor(private datasourceService: DatasourceService) {
    }
    
    ngOnInit(): void {
         this.datasourceService.getMenu()
             .subscribe(
                menu => this.menu = menu,
                error =>  this.errorMessage = <any>error);

    }

    public ind(value): void {
        console.log();
    }
    
    public onClick(value, i) {
        this.output.emit(value);
        console.log(value);
        console.log(i);
        //console.log(value);
    }
}