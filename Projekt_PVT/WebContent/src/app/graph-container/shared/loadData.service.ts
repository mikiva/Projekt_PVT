import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import {Observable} from 'rxjs/Observable';
import {IDatasource} from './datasource';

@Injectable()
export class LoadDataService{
    
    constructor(private http: Http){}
    
    private title : string;
    
    private url = "http://rigel.hig.se:8080/GetAnalyzeServlet?title="
    
    
    
   public loadAnalysis(title:string): Observable<any>{
        
        
       
   return this.http.get(this.getLoadUrl(title))
            .map((response: Response) => <any> response.json())
            .catch(this.handleError);
  
  
  
        
        
    }
       private handleError(error: Response) {
        return Observable.throw(error.json().error || 'Server error');
    }
    
      
    
  private  getLoadUrl(title: string){
        
        return (this.url + title);
        
        
    }
    
    
    
}