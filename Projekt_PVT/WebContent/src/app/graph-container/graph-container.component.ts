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
    header: string = null;
    
    savedDataMessage: string = "";
    
    @ViewChild(ChooseSaved) savedChild:ChooseSaved;
    @ViewChild (ChooseResolution) resChild:ChooseResolution;
    @ViewChild(ChooseSource) sourceChild:ChooseSource;

    constructor(private databaseService: DatabaseService, private loadService: LoadDataService) { }

    setDateBefore(dateBefore: string): void {
        this.dateBefore = dateBefore;
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
    setHeader(title: string){
       document.getElementById("title").value = title;
    }
    setComment(comment: string){
        document.getElementById("comment").value = comment;
    }


    saveAnalysis() : void {
        var observable: Observable<string> = this.databaseService.saveAnalysis(this.dateBefore, this.dateAfter, this.resolution, this.sourceOne, this.sourceTwo, this.getTitle(), this.getComment());
        observable.subscribe(
            response => this.savedDataMessage = response,
            err => console.error(err),
            () => this.savedChild.updateList());
    }
    getTitle() {
        return document.getElementById("title").value;
    }
    private getComment(){
        return document.getElementById("comment").value;
    }
    
    getSavedAnalysis(title: string) {
        
        console.log(title);
        var analysis = this.loadService.loadAnalysis(title)
        .subscribe(analysis => {
            
        var source1 : Object = {database: analysis.database1, dataset: analysis.datasource1};
        var source2 : Object = {database: analysis.database2, dataset: analysis.datasource2};
        this.setSourceOne(source1);
        this.setSourceTwo(source2);
        this.setDateBefore(analysis.startDate);
        this.setDateAfter(analysis.endDate);
        this.setResolution(analysis.resolution);
        this.setHeader(analysis.title);
        this.setComment(analysis.comment);
        }, err => console.error(err));
        
      

        

    }

}