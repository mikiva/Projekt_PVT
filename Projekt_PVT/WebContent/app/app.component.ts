import {Component, OnInit}  from 'angular2/core';
import {HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/Rx';
import {IDatasource} from './datasource";
import {DatasourceService} from "app/datasource.service";

@Component( {
    selector: "app",
    templateUrl: "app/app.html",
    providers: [DatasourceService, HTTP_PROVIDERS]
})
export class App implements OnInit {
    errorMessage: string;
    datasource: IDatasource[];

    constructor(private datasourceService: DatasourceService) {

    }

    ngOnInit(): void {
        this.datasourceService.getData()
            .subscribe(
                datasource => this.datasource = datasource,
                error =>  this.errorMessage = <any>error);
    }
}