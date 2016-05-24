import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import {Observable} from 'rxjs/Observable';
import {IDatasource} from './datasource';


@Injectable()
export class DatabaseService {

    private saveUrl = 'http://rigel.hig.se:8080/Bulle-dev/SaveAnalyzeServlet?';
    //private saveUrl = 'http://rigel.hig.se:8080/Bulle-dev/ServletTest?';

    private url: string;

    constructor(private http: Http) { }

    public saveAnalysis(dateBefore: string, dateAfter: string, resolution: string, sourceOne: Object, sourceTwo: Object, title: string): Observable<string> {

        this.url = this.getSaveUrl(sourceOne, sourceTwo, resolution, dateBefore, dateAfter, title);
        console.log(this.url);


        //this.http(this.url);


       return this.http.get(this.url)
            .do(data => console.log('Something works'))
            .catch(new Error());
    }
    public getSaveUrl(sourceOne: Object, sourceTwo: Object, resolution: string, dateBefore: string, dateAfter: string, title: string) {
        return (this.saveUrl + 'title=' + (title ? title : 'NoTitle') + '&res=' + (resolution ? resolution : 'day') + '&database1=' + sourceOne["database"] + '&value1=' + sourceOne["dataset"] +
            (sourceTwo ? '&database2=' + sourceTwo["database"] + '&value2=' + sourceTwo["dataset"] : "")
            + '&startDate=' + (dateBefore || '0001-01-01') + '&endDate=' + (dateAfter || '9998-12-30'));

    }
}