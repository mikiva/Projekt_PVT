import { Injectable } from 'angular2/core';
import { Http, Response } from 'angular2/http';
import { Observable } from 'rxjs/Observable';
import { IDatasource } from './datasource';

@Injectable()
export class DatasourceService {
    private url = 'http://localhost:8080/Proj/ServletTest?datasource=goals';

    constructor(private http: Http) { }

    getData(): Observable<IDatasource[]> {
        return this.http.get(this.url)
            .map((response: Response) => <IDatasource[]> response.json().data)
            .do(data => console.log('All: ' +  JSON.stringify(data)))
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
