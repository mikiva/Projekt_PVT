import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {IDatasource} from './datasource';


@Injectable()
export class DatabaseService{
    
    
    
    //private saveUrl = 'http://localhost:8080/Proj/SaveAnalysisServlet?';
    private saveUrl = 'http://localhost:8080/Proj/ServletTest?';
    private url: string;
    
    
    constructor(private http: Http){}
    
    public saveAnalysis(dateBefore: string, dateAfter: string, resolution: string, sourceOne: Object, sourceTwo: Object): Observable<string>{
     
     this.url = this.getSaveUrl(sourceOne, sourceTwo, resolution, dateBefore, dateAfter);
     console.log(this.url);
     
     this.http.get(this.url)
     .map((response: Response) => <string> response.toString())
     .do(data => console.log('Something works'))
     .catch(new Error());
         
     
     

    }
    
    
    
    public getSaveUrl(sourceOne: Object, sourceTwo: Object, resolution: string, dateBefore: string, dateAfter: string){
        return (this.saveUrl + 'res=' + (resolution? resolution : 'day') + '&database1=' + sourceOne["database"] + '&value1=' + sourceOne["dataset"] +
        (sourceTwo? '&database2=' + sourceTwo["database"] + '&value2=' + sourceTwo["dataset"] : "")
        + '&startDate=' + (dateBefore || '0000-01-01') + '&endDate=' + (dateAfter || '9999-12-30'));
        
    }
    
    
    
    
    
}