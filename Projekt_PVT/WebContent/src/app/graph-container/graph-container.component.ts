import {Component, Output, ViewChild, ViewChildren } from '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {Graph} from './graph/graph.component';
import {GraphCorrelationComponent} from './graph-correlation/graph-correlation.component';
import {ChooseSource} from './choose-datasource/choose-datasource.component';
import {ChooseResolution} from './choose-resolution/choose-resolution.component';
import {MainDatePicker} from './datepicker/maindatepicker';
import {MyDatePicker} from './mydatepicker';
import {DatabaseService} from './shared/database.service';
import {LoadDataService} from './shared/loadData.service';
import {ChooseSaved} from './choose-saved-analysis/choose-saved.component';

@Component({
    selector: 'graph-container',
    directives: [Graph, GraphCorrelationComponent, ChooseSource, ChooseResolution, MainDatePicker, ChooseSaved],
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
    
    sM : string;
    sUm :string;
    
    savedDataMessage: string = "";
    
    analysis: Observable;
    
    
    @ViewChild (ChooseSaved) savedChild:ChooseSaved;
    @ViewChild (ChooseResolution) resChild:ChooseResolution;
    @ViewChild (ChooseSource) sourceChild:ChooseSource;
    @ViewChildren (Graph) graphChildren:Graph;
    @ViewChild(GraphCorrelationComponent) graphCorrChild:GraphCorrelationComponent;

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
    
    updateDropdowns(){
        
        
        var s1 = document.getElementById("sOne");
        var s2 = document.getElementById("sTwo");
        
       
        
        var sM1 = this.analysis.database1.toString();
        var sUm1 = this.analysis.datasource1.toString();
        
        var sM2 = this.analysis.database2.toString();
        var sUm2 = this.analysis.datasource2.toString();
        
        
        console.log(sM1);
        console.log(sUm1);
        

     s1.click();
     s2.click();
     
        
        
    }
    
    getSavedAnalysis(title: string) {
        console.log(title);
        this.analysis = this.loadService.loadAnalysis(title)
            .subscribe(analysis => {
                console.log(analysis.comment);
                var source1 : Object = {database: analysis.database1, dataset: analysis.datasource1};
                var source2 : Object = {database: analysis.database2, dataset: analysis.datasource2};
                this.setSourceOne(source1);
                this.setSourceTwo(source2);
                this.setDateBefore(analysis.startDate);
                this.setDateAfter(analysis.endDate);
                this.setResolution(analysis.resolution);
                this.setHeader(analysis.title);
                this.setComment(analysis.comment);
        }, err => console.error(err),
        () => this.updateDropdowns());
    }
    
    deleteAnalysis() {
        //console.log(title);
        
         var deleteAnalysis = this.databaseService.deleteAnalysis(this.getTitle())
            .subscribe(response => this.savedDataMessage = response,
            err => console.error(err),
            () => this.savedChild.updateList(),
            () => this.graphChildren.clear(),
            () => this.graphCorrChild.clear());
            
    }
        
        
       clear(){
           
          this.setComment("");
          this.setDateAfter("");
          this.setDateBefore("");
          this.setResolution("");
          this.setResolution("");
          this.setSourceOne({database: null, dataset: null});
          this.setSourceTwo({database: null, dataset: null});
          this.setHeader("");
           
       } 
       /* var deleteAnalysis = this.databaseService.deleteAnalysis(title)
            .subscribe(deleteAnalysis => {
                console.log(deleteAnalysis);
            }, err => console.log(err));*/
    }
}