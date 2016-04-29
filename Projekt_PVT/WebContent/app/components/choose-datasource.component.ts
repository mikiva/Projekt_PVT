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
    menu: Menu[];
    underMenu: String[];
    underMenuState: boolean = false;
    errorMessage: string;
    
    constructor(private datasourceService: DatasourceService) {
    }
    
    ngOnInit(): void {
         this.datasourceService.getMenu()
             .subscribe(
                menu => this.menu = menu,
                error =>  this.errorMessage = <any>error);

    }

    private fillUnderMenu(index): void {
        this.underMenu = this.menu[index].values;
        this.underMenuState = true;
        console.log(this.menu);
    }
    
    public onClick(value, index) {
        this.output.emit(value);
        this.fillUnderMenu(index);
        console.log(value);
        console.log(index);
    }
}