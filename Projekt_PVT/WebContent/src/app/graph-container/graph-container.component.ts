import {Component, Output, ViewChild} from '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {Graph} from './graph/graph.component';
import {GraphCorrelationComponent} from './graph-correlation/graph-correlation.component';
import {ChooseSource} from './choose-datasource/choose-datasource.component';
import {ChooseResolution} from './choose-resolution/choose-resolution.component';
import {SampleDatePicker} from './datepicker/sampleapp';
import {MyDatePicker} from './mydatepicker';
import {DatabaseService} from './shared/database.service';
import {LoadDataService} from './shared/loadData.service';
import {ChooseSaved} from './choose-saved-analysis/choose-saved.component';



@Component({
    selector: 'graph-container',
    directives: [Graph, GraphCorrelationComponent, ChooseSource, ChooseResolution, SampleDatePicker, ChooseSaved],
    templateUrl: 'src/app/graph-container/graph-container.html',
    providers: [DatabaseService, HTTP_PROVIDERS, LoadDataService]
})
export class GraphContainerComponent {

    sourceOne: Object = { database: null, dataset: null };
    sourceTwo: Object = { database: null, dataset: null };
    resolution: string = null;
    dateBefore: string = null;
    dateAfter: string = null;
    selectedData: string[] = null;
    
    savedDataMessage: string = "";
    
    @ViewChild(ChooseSaved) savedChild:ChooseSaved;

    constructor(private databaseService: DatabaseService, private loadService: LoadDataService) { }

    setDateBefore(dateBefore: string): void {
        this.dateBefore = dateBefore;
        console.log(dateBefore);
    }

    setDateAfter(dateAfter: string): void {
        this.dateAfter = dateAfter;
    }

    setSourceOne(sourceOne: Object): void {
        this.sourceOne = sourceOne;
    }

    setSourceTwo(sourceTwo: Object): void {
        this.sourceTwo = sourceTwo;
    }

    setResolution(resolution: string): void {
        this.resolution = resolution;
    }


    saveAnalysis() : void {
        var observable: Observable<string> = this.databaseService.saveAnalysis(this.dateBefore, this.dateAfter, this.resolution, this.sourceOne, this.sourceTwo, this.getTitle());
        observable.subscribe(
            response => this.savedDataMessage = response,
            err => console.error(err) ,
            () => this.savedChild.updateList());
    }
    getTitle() {
        return document.getElementById("heading").value;
    }
    getSavedAnalysis(title: string) {
        var analysis = this.loadService.loadAnalysis(title);

    }

}